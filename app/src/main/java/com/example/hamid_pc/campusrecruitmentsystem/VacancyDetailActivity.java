package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Hamid-PC on 7/15/2017.
 */

public class VacancyDetailActivity extends SingleFragmentActivity {

    private static String sOrganizationID;


    public static Intent NewIntent(Context packageContext, String OrganizationID) {
        Intent intent = new Intent(packageContext, VacancyDetailActivity.class);
        sOrganizationID = OrganizationID;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Log.d("Check", "In Detail Activity " + sOrganizationID);
        return VacancyDetailFragment.NewInstance(sOrganizationID);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
