package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class OrganizationListActivity extends SingleFragmentActivity {

    public static Intent NewIntent(Context packageContext) {

        Intent intent = new Intent(packageContext, OrganizationListActivity.class);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return OrganizationListFragment.NewInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
