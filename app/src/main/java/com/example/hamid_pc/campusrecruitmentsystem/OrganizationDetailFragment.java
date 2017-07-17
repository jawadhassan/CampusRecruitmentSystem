package com.example.hamid_pc.campusrecruitmentsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OrganizationDetailFragment extends Fragment {

    private static String sOrganizationName;
    private static String sOrganizationAddress;
    private static String sOrganizationContact;
    private static String sOrganizationEmail;
    private static String sOrganizationType;
    private static String sOrganizationUUID;


    private TextView mOrganizationName;
    private TextView mOrganizationContact;
    private TextView mOrganizationAddress;
    private TextView mOrganizationEmail;
    private TextView mOrganizationType;


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
        Log.d("Check", "In Organization Detail Fragment");


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

        return view;


    }
}
