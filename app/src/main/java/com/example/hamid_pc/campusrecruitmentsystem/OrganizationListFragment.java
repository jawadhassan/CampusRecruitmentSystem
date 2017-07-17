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
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof OrganizationListActivity) {
                OrganizationListActivity organizationListActivity = (OrganizationListActivity) appCompatActivity;
                Intent intent = OrganizationDetailActivity.NewIntent(organizationListActivity, mOrganization.getmUUID(), mOrganization.getmOrganizationName(), mOrganization.getmOrganizationAddress(), mOrganization.getmOrganizationContact(), mOrganization.getmEmail(), mOrganization.getmOrganizationType());
                organizationListActivity.startActivity(intent);
            }

        }
    }


}


