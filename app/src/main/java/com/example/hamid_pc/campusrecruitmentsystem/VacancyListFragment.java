package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class VacancyListFragment extends Fragment {


    private final String ADMIN = "administrator";
    CoordinatorLayout.LayoutParams p;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAdminReference;
    private FloatingActionButton mFAB;
    private Query mQuery;
    private String mUUID;

    //TODO: Resolve the issue, if user is student then list of all vacancies will be available

    public static VacancyListFragment NewInstance() {
        VacancyListFragment vacancyListFragment = new VacancyListFragment();
        return vacancyListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mDatabaseReference = mFirebaseDatabase.getReference("vacanciesposted/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        mDatabaseReference = mFirebaseDatabase.getReference("vacancies");
        mUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vacancy_list_recycler_view);
        mFAB = (FloatingActionButton) view.findViewById(R.id.vacancy_floating_button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        p = (CoordinatorLayout.LayoutParams) mFAB.getLayoutParams();
        Check();


        p.setBehavior(null); //should disable default animations
        p.setAnchorId(View.NO_ID); //should let you set visibility
        mFAB.setLayoutParams(p);
        mFAB.setVisibility(View.GONE);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vacancyIntent = VacancyCreationActivity.NewIntent(getActivity());
                startActivity(vacancyIntent);
            }
        });

        return view;
    }

    public void UpdateUI(Query query) {
//        mAdapter = new FirebaseRecyclerAdapter<Vacancy, VacancyViewHolder>(
//                Vacancy.class,
//                R.layout.list_vacancy,
//                VacancyViewHolder.class,
//                mDatabaseReference.orderByChild("mOrganizationID").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
//
//
//        ) {
//            @Override
//            protected void populateViewHolder(VacancyViewHolder vacancyViewHolder, Vacancy model, int position) {
//
//                vacancyViewHolder.vacancyTextView.setText(model.getmTitle());
//                Vacancy vacancyPosted = getItem(position);
//                vacancyViewHolder.bindCourse(vacancyPosted);
//            }
//        };

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

        //to bring things back to normal state

        p.setBehavior(new FloatingActionButton.Behavior());
        p.setAnchorId(R.id.vacancy_list_recycler_view);
        mFAB.setLayoutParams(p);

        mRecyclerView.setAdapter(mAdapter);

    }


    public void Check() {

        mAdminReference.orderByChild("mUUID").equalTo(mUUID).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user = dataSnapshot.getValue(User.class);
                if (user.getmRole().compareToIgnoreCase(ADMIN) == 0) {
                    mQuery = mDatabaseReference.orderByChild("mOrganizationID").equalTo(mUUID);
                    UpdateUI(mQuery);
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
            Log.d("Check", "In Vacancy List:" + mVacancyPosted.getmOrganizationID());

            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof VacancyListActivity) {
                VacancyListActivity vacancyListActivity = (VacancyListActivity) appCompatActivity;
                Intent intent = VacancyDetailActivity.NewIntent(vacancyListActivity, mVacancyPosted.getmOrganizationID());
                vacancyListActivity.startActivity(intent);

            }
        }
    }

}

