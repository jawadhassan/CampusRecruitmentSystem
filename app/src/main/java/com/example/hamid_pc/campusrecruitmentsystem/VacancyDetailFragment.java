package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VacancyDetailFragment extends Fragment {

    private static String sOrganizationID;
    private static String sVacancyID;
    private final String STUDENT = "student";
    private final String ORGANIZATION = "organization";
    TextView mTitleView;
    TextView mDetailView;
    TextView mPositionView;
    TextView mAreaView;
    TextView mAgeView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mUserReference;
    private DatabaseReference mStudentReference;
    private DatabaseReference mVacancyReference;
    private Button mApplyButton;
    private Button mApplicantButton;
    private String mUUID;
    private String mSnapshotKey;
    private Vacancy vacancy;

    public static VacancyDetailFragment NewInstance(String OrganizationID, String VacancyID) {
        sOrganizationID = OrganizationID;
        sVacancyID = VacancyID;
        VacancyDetailFragment vacancyDetailFragment = new VacancyDetailFragment();
        return vacancyDetailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");
        mUserReference = mFirebaseDatabase.getReference("users");
        mStudentReference = mFirebaseDatabase.getReference("students");
        mUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_detail, container, false);
        mTitleView = (TextView) view.findViewById(R.id.job_title);
        mDetailView = (TextView) view.findViewById(R.id.job_detail);
        mPositionView = (TextView) view.findViewById(R.id.position);
        mAreaView = (TextView) view.findViewById(R.id.area);
        mAgeView = (TextView) view.findViewById(R.id.age_limit);
        mApplyButton = (Button) view.findViewById(R.id.button_apply);
        mApplicantButton = (Button) view.findViewById(R.id.button_applicants);

        mDatabaseReference.orderByChild("mVacancyID").equalTo(sVacancyID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mSnapshotKey = dataSnapshot.getKey();
                vacancy = dataSnapshot.getValue(Vacancy.class);


                mTitleView.setText(vacancy.getmTitle());
                mDetailView.setText(vacancy.getmDetail());
                mPositionView.setText(vacancy.getmPosition());
                mAgeView.setText(vacancy.getmAgeLimit());
                mAreaView.setText(vacancy.getmArea());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mUserReference.orderByChild("mUUID").equalTo(mUUID).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user = dataSnapshot.getValue(User.class);

                if (user.getmRole().compareToIgnoreCase(STUDENT) == 0)
                    mApplyButton.setVisibility(View.VISIBLE);
                else if (user.getmRole().compareToIgnoreCase(ORGANIZATION) == 0)
                    mApplicantButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mStudentReference.orderByChild("mUuid").equalTo(mUUID).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Student student = dataSnapshot.getValue(Student.class);
                        String name = student.getmName();
                        JobApplicant jobApplicant = new JobApplicant(mUUID, name);
                        mVacancyReference = mFirebaseDatabase.getReference("vacancies/" + mSnapshotKey + "/applicants");
                        mVacancyReference.push().setValue(jobApplicant);


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        mApplicantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ApplicantListActivity.NewIntent(getActivity(), mSnapshotKey);
                startActivity(intent);
            }
        });



        return view;
    }

}
