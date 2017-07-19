package com.example.hamid_pc.campusrecruitmentsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrganizationDetailFragment extends Fragment {

    private static String sOrganizationName;
    private static String sOrganizationAddress;
    private static String sOrganizationContact;
    private static String sOrganizationEmail;
    private static String sOrganizationType;
    private static String sOrganizationUUID;
    private final String ADMIN = "administrator";
    private String mUUID;
    private TextView mOrganizationName;
    private TextView mOrganizationContact;
    private TextView mOrganizationAddress;
    private TextView mOrganizationEmail;
    private TextView mOrganizationType;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAdminReference;
    private DatabaseReference mVacancyReference;


    //TODO: Fix issue the vacancy record remains even after record of Organization is deleted

    public static OrganizationDetailFragment NewInstance(String OrganizationUUID, String OrganizationName, String OrganizationAddress, String OrganizationContact, String OrganizationEmail, String OrganizationType) {
        OrganizationDetailFragment organizationDetailFragment = new OrganizationDetailFragment();
        sOrganizationName = OrganizationName;
        sOrganizationAddress = OrganizationAddress;
        sOrganizationContact = OrganizationContact;
        sOrganizationEmail = OrganizationEmail;
        sOrganizationType = OrganizationType;
        sOrganizationUUID = OrganizationUUID;
        return organizationDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("organizations");
        mAdminReference = mFirebaseDatabase.getReference("users");
        mVacancyReference = mFirebaseDatabase.getReference("vacancies");
        mUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization_detail, container, false);

        mOrganizationName = (TextView) view.findViewById(R.id.organization_title);
        mOrganizationAddress = (TextView) view.findViewById(R.id.orgaization_address);
        mOrganizationContact = (TextView) view.findViewById(R.id.organization_contact);
        mOrganizationEmail = (TextView) view.findViewById(R.id.organization_email);
        mOrganizationType = (TextView) view.findViewById(R.id.organization_type);

        mOrganizationName.setText(sOrganizationName);
        mOrganizationAddress.setText(sOrganizationAddress);
        mOrganizationContact.setText(sOrganizationContact);
        mOrganizationEmail.setText(sOrganizationEmail);
        mOrganizationType.setText(sOrganizationType);

        EnableDeleteMenu();

        return view;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miDelete:
                DeleteOrganization();
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


    public void DeleteOrganization() {
        mDatabaseReference.orderByChild("mUUID").equalTo(sOrganizationUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                dataSnapshot.getRef().removeValue();
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

        mAdminReference.orderByChild("mUUID").equalTo(sOrganizationUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.getRef().removeValue();
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

        mVacancyReference.orderByChild("mOrganizationID").equalTo(sOrganizationUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.getRef().removeValue();
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

    public void EnableDeleteMenu() {

        mAdminReference.orderByChild("mUUID").equalTo(mUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if (user.getmRole().compareToIgnoreCase(ADMIN) == 0) {
                    setHasOptionsMenu(true);
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
}
