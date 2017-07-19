package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicantListFragment extends Fragment {

    private static String sDataSnapShotKey;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private FirebaseRecyclerAdapter<JobApplicant, JobViewHolder> mAdapter;
    private RecyclerView mRecyclerView;

    public static ApplicantListFragment NewInstance(String snapShot) {
        ApplicantListFragment applicantListFragment = new ApplicantListFragment();
        sDataSnapShotKey = snapShot;
        return applicantListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Check", "In Application List Fragment");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies/" + sDataSnapShotKey + "/applicants");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.student_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;
    }

    public void UpdateUI() {
        mAdapter = new FirebaseRecyclerAdapter<JobApplicant, JobViewHolder>(
                JobApplicant.class,
                R.layout.list_student,
                JobViewHolder.class,
                mDatabaseReference
        ) {

            @Override
            protected void populateViewHolder(JobViewHolder viewHolder, JobApplicant model, int position) {

                viewHolder.StudentText.setText(model.getmName());
                JobApplicant jobApplicant = getItem(position);
                viewHolder.bindView(jobApplicant);
            }

        };

        mRecyclerView.setAdapter(mAdapter);

    }

    public static class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        JobApplicant mJobApplicant;
        TextView StudentText;
        TextView ProgramEnrolled;

        public JobViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            StudentText = (TextView) itemView.findViewById(R.id.list_item_student_name);
            ProgramEnrolled = (TextView) itemView.findViewById(R.id.program_enrolled);
        }

        @Override
        public void onClick(View v) {


            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof ApplicantListActivity) {
                ApplicantListActivity applicantListActivity = (ApplicantListActivity) appCompatActivity;
                Intent intent = StudentProfileActivity.newIntent(applicantListActivity, mJobApplicant.getmUUID());
                applicantListActivity.startActivity(intent);
            }
        }

        public void bindView(JobApplicant jobApplicant) {
            mJobApplicant = jobApplicant;
        }
    }

}

