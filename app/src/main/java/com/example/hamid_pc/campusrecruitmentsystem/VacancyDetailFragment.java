package com.example.hamid_pc.campusrecruitmentsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VacancyDetailFragment extends Fragment {

    private static String sRandomUuid;
    TextView mTitleView;
    TextView mDetailView;
    TextView mPositionView;
    TextView mAreaView;
    TextView mAgeView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Vacancy vacancy;

    public static VacancyDetailFragment NewInstance(String PushID) {
        sRandomUuid = PushID;

        VacancyDetailFragment vacancyDetailFragment = new VacancyDetailFragment();
        return vacancyDetailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");

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

        mDatabaseReference.orderByChild("mUUID").equalTo(sRandomUuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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


        return view;
    }
}
