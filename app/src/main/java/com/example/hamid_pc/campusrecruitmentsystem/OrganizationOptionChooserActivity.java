package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class OrganizationOptionChooserActivity extends SingleFragmentActivity {

    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, OrganizationOptionChooserActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return OrganizationOptionChooserFragment.NewInstance();
    }


}
