package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class StudentVacancyListFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mOrganizationReference;

    private RecyclerView mRecyclerView;
    private Query mQuery;
    private FirebaseRecyclerAdapter<Vacancy, VacancyViewHolder> mAdapter;

    public static StudentVacancyListFragment NewInstance() {
        StudentVacancyListFragment studentVacancyListFragment = new StudentVacancyListFragment();
        return studentVacancyListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");
        mOrganizationReference = mFirebaseDatabase.getReference("users");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vacancy_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Check();
        setHasOptionsMenu(true);
        return view;

    }


    public void UpdateUI(Query query) {


        mQuery = query;

        mAdapter = new FirebaseRecyclerAdapter<Vacancy, VacancyViewHolder>(
                Vacancy.class,
                R.layout.list_vacancy,
                VacancyViewHolder.class,
                mQuery
        ) {
            @Override
            protected void populateViewHolder(VacancyViewHolder viewHolder, Vacancy model, int position) {

                viewHolder.vacancyTextView.setText(model.getmTitle());
                Vacancy vacancyPosted = getItem(position);
                viewHolder.bindCourse(vacancyPosted);
            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miProfile:
                AuthUI.getInstance().signOut(getActivity()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = AuthenticationActivity.newIntent(getActivity());
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });

        }

        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.sign_out_menu, menu);

    }


    public void Check() {

        mQuery = mDatabaseReference;
        UpdateUI(mQuery);
    }


    public static class VacancyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vacancyTextView;
        Vacancy mVacancyPosted;


        public VacancyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            vacancyTextView = (TextView) itemView.findViewById(R.id.list_item_vacancy);

        }

        public void bindCourse(Vacancy vacancyPosted) {
            mVacancyPosted = vacancyPosted;
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof StudentVacancyListActivity) {
                StudentVacancyListActivity studentVacancyListActivity = (StudentVacancyListActivity) appCompatActivity;
                Intent intent = VacancyDetailActivity.NewIntent(studentVacancyListActivity, mVacancyPosted.getmOrganizationID(), mVacancyPosted.getmVacancyID());
                studentVacancyListActivity.startActivity(intent);

            }
        }
    }
}
