package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class OrganizationDetailActivity extends SingleFragmentActivity {

    private static String sOrganizationName;
    private static String sOrganizationAddress;
    private static String sOrganizationContact;
    private static String sOrganizationEmail;
    private static String sOrganizationType;
    private static String sOrganizationUUID;


    public static Intent NewIntent(Context packageContext, String OrganizationUUID, String OrganizationName, String OrganizationAddress, String OrganizationContact, String OrganizationEmail, String OrganizationType) {
        Intent intent = new Intent(packageContext, OrganizationDetailActivity.class);
        sOrganizationName = OrganizationName;
        sOrganizationAddress = OrganizationAddress;
        sOrganizationContact = OrganizationContact;
        sOrganizationEmail = OrganizationEmail;
        sOrganizationType = OrganizationType;
        sOrganizationUUID = OrganizationUUID;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return OrganizationDetailFragment.NewInstance(sOrganizationUUID, sOrganizationName, sOrganizationAddress, sOrganizationContact, sOrganizationEmail, sOrganizationType);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
