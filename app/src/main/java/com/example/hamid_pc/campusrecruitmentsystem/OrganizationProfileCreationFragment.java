package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Intent;
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


public class OrganizationProfileCreationFragment extends Fragment {

    private EditText mEditTextName;
    private EditText mEditTextContact;
    private EditText mEditTextOrganizationType;
    private EditText mEditTextAddress;
    private Button mSubmit;

    private String mName;
    private String mContactNum;
    private String mAddress;
    private String mOrganizationType;
    private String mUuid;
    private String mEmail;
    private Organization mOrganization;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseUserReference;
    private User mUser;


    public static OrganizationProfileCreationFragment NewInstance() {
        OrganizationProfileCreationFragment organizationProfileCreationFragment = new OrganizationProfileCreationFragment();
        return organizationProfileCreationFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("organizations");
        mDatabaseUserReference = mFirebaseDatabase.getReference("users");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization_profile_creation, container, false);


        mEditTextName = (EditText) view.findViewById(R.id.view_edittext_name);
        mEditTextContact = (EditText) view.findViewById(R.id.view_edittext_contact_num);
        mEditTextAddress = (EditText) view.findViewById(R.id.view_edittext_address);
        mEditTextOrganizationType = (EditText) view.findViewById(R.id.view_edittext_industry_type);


        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = mEditTextName.getText().toString();
                mAddress = mEditTextAddress.getText().toString();
                mOrganizationType = mEditTextOrganizationType.getText().toString();
                mContactNum = mEditTextContact.getText().toString();
                mUuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                mOrganization = new Organization(mUuid, mName, mAddress, mContactNum, mEmail, mOrganizationType);

                mDatabaseReference.push().setValue(mOrganization);


                mUser = new User(mUuid, "organization");
                mDatabaseUserReference.push().setValue(mUser);

                Intent intent = StudentListActivity.NewIntent(getActivity());
                startActivity(intent);

                Log.d("Check", "Organization Profile Creation");
            }
        });
        return view;
    }
}
