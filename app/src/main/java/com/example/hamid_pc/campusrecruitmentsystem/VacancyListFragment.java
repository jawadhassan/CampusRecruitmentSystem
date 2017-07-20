package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class VacancyListFragment extends Fragment {


    private final String ORGANIZATION = "organization";
    private final String STUDENT = "student";
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mOrganizationReference;

    private Query mQuery;
    private String mUUID;


    public static VacancyListFragment NewInstance() {
        VacancyListFragment vacancyListFragment = new VacancyListFragment();
        return vacancyListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");
        mOrganizationReference = mFirebaseDatabase.getReference("users");
        mUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vacancy_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Check();


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
            case R.id.miAdd:
                Intent vacancyIntent = VacancyCreationActivity.NewIntent(getActivity());
                startActivity(vacancyIntent);
        }


        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.add_menu, menu);

    }


    public void Check() {

        mOrganizationReference.orderByChild("mUUID").equalTo(mUUID).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user = dataSnapshot.getValue(User.class);
                if (user.getmRole().compareToIgnoreCase(ORGANIZATION) == 0) {
                    mQuery = mDatabaseReference.orderByChild("mOrganizationID").equalTo(mUUID);
                    UpdateUI(mQuery);
                    setHasOptionsMenu(true);
                } else {
                    mQuery = mDatabaseReference;
                    UpdateUI(mQuery);
                }


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
            if (appCompatActivity instanceof VacancyListActivity) {
                VacancyListActivity vacancyListActivity = (VacancyListActivity) appCompatActivity;
                Intent intent = VacancyDetailActivity.NewIntent(vacancyListActivity, mVacancyPosted.getmOrganizationID(), mVacancyPosted.getmVacancyID());
                vacancyListActivity.startActivity(intent);

            }
        }
    }

}

