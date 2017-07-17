package com.example.hamid_pc.campusrecruitmentsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class OrganizationListFragment extends Fragment {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;

    public static OrganizationListFragment NewInstance() {
        OrganizationListFragment organizationListFragment = new OrganizationListFragment();
        return organizationListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("organizations");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization_list, container, false);
        Log.d("Check", "In Organization List Fragment:");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.organization_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
//        EnableDeleteMenu();
        return view;
    }


    public void UpdateUI() {
        mAdapter = new FirebaseRecyclerAdapter<Organization, OrganizationViewHolder>(
                Organization.class,
                R.layout.list_organization,
                OrganizationViewHolder.class,
                mDatabaseReference
        ) {
            @Override
            protected void populateViewHolder(OrganizationViewHolder viewHolder, Organization model, int position) {
                viewHolder.OrganizationTitle.setText(model.getmOrganizationName());
                Organization organization = getItem(position);
                viewHolder.bindOrganization(organization);
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }


/*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miDelete:
                Log.d("Check", "Vacancy Detail Activity: Deleted Menu Item Selected");
                mStudentRef.removeValue();
                mUserRef.removeValue();
                getActivity().finish();


        }


        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.delete_menu, menu);
    }


    public void EnableDeleteMenu() {

        mVacaniesPostedReference.orderByChild("mUUID").equalTo(sRandomUuid).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user = dataSnapshot.getValue(User.class);
                if (user.getmRole().compareToIgnoreCase(ADMIN) == 0)
                    setHasOptionsMenu(true);

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


        });*/


    public static class OrganizationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Organization mOrganization;
        TextView OrganizationTitle;

        public OrganizationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            OrganizationTitle = (TextView) itemView.findViewById(R.id.list_item_organization_name);

        }

        public void bindOrganization(Organization organization) {
            mOrganization = organization;
        }

        @Override
        public void onClick(View v) {
            Log.d("Check", "OrganizationListFragment: OrganizationListFragment ListItem Clicked");

        }
    }


}


