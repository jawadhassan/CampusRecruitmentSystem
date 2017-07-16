package com.example.hamid_pc.campusrecruitmentsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class VacancyCreationFragment extends Fragment {


    private EditText mJobTitle;
    private EditText mJobDetail;
    private EditText mPositions;
    private EditText mArea;
    private EditText mAge;

    private String mTitle;
    private String mDetail;
    private String mPositionNumber;
    private String mPlacementArea;
    private String mAgeLimit;
    private String mUuid;
    private String mPushKey;

    private VacancyPosted vacancyPosted;

    private Button mSubmit;

    private Vacancy mVacancy;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mVacancyPostedReference;

    private String mRandomId;


    public static VacancyCreationFragment NewInstance() {
        VacancyCreationFragment vacancyCreationFragment = new VacancyCreationFragment();
        return vacancyCreationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");
        mRandomId = UUID.randomUUID().toString();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vacancy_creation, container, false);
        mJobTitle = (EditText) view.findViewById(R.id.view_edittext_job_title);
        mJobDetail = (EditText) view.findViewById(R.id.view_edittext_detail);
        mPositions = (EditText) view.findViewById(R.id.view_edittext_positions);
        mArea = (EditText) view.findViewById(R.id.view_edittext_area);
        mAge = (EditText) view.findViewById(R.id.view_edittext_age);

        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle = mJobTitle.getText().toString();
                mDetail = mJobDetail.getText().toString();
                mPositionNumber = mPositions.getText().toString();
                mPlacementArea = mArea.getText().toString();
                mAgeLimit = mAge.getText().toString();
                mUuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                mVacancyPostedReference = mFirebaseDatabase.getReference("vacanciesposted/" + mUuid);


                vacancyPosted = new VacancyPosted(mRandomId, mTitle);
                mVacancy = new Vacancy(mRandomId, mTitle, mDetail, mPlacementArea, mPositionNumber, mAgeLimit);


                mDatabaseReference.push().setValue(mVacancy);
                mVacancyPostedReference.push().setValue(vacancyPosted);


                // Intent intent = CourseListStudentPanelActivity.newIntent(getActivity());
                // startActivity(intent);
            }
        });
        return view;


    }
}
