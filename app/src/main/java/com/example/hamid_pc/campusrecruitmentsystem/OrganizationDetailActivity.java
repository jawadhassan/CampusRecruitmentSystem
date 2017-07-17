package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class OrganizationDetailActivity extends SingleFragmentActivity {

    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, OrganizationDetailActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
