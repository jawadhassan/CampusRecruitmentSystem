package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VacancyListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FloatingActionButton mFAB;


    public static VacancyListFragment NewInstance() {
        VacancyListFragment vacancyListFragment = new VacancyListFragment();
        return vacancyListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacanciesposted/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vacancy_list_recycler_view);
        mFAB = (FloatingActionButton) view.findViewById(R.id.vacancy_floating_button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vacancyIntent = VacancyCreationActivity.NewIntent(getActivity());
                startActivity(vacancyIntent);
            }
        });

        return view;
    }

    public void UpdateUI() {
        mAdapter = new FirebaseRecyclerAdapter<VacancyPosted, VacancyViewHolder>(
                VacancyPosted.class,
                R.layout.list_vacancy,
                VacancyViewHolder.class,
                mDatabaseReference

        ) {
            @Override
            protected void populateViewHolder(VacancyViewHolder vacancyViewHolder, VacancyPosted model, int position) {

                vacancyViewHolder.vacancyTextView.setText(model.getmTitle());
                VacancyPosted vacancyPosted = getItem(position);
                vacancyViewHolder.bindCourse(vacancyPosted);
            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }


    public static class VacancyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vacancyTextView;
        VacancyPosted mVacancyPosted;


        public VacancyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            vacancyTextView = (TextView) itemView.findViewById(R.id.list_item_vacancy);

        }

        public void bindCourse(VacancyPosted vacancyPosted) {
            mVacancyPosted = vacancyPosted;
        }

        @Override
        public void onClick(View v) {
            Log.d("Check", "In Vacancy List:" + mVacancyPosted.getmRandomID());

            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof VacancyListActivity) {
                VacancyListActivity vacancyListActivity = (VacancyListActivity) appCompatActivity;
                Intent intent = VacancyDetailActivity.NewIntent(vacancyListActivity, mVacancyPosted.getmRandomID());
                vacancyListActivity.startActivity(intent);

            }
        }
    }

}

