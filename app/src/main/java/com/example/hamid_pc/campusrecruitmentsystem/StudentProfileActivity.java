package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;



public class StudentProfileActivity extends SingleFragmentActivity {


    public static String sUUID;

    public static Intent newIntent(Context PackageContext, String UUID) {
        Intent intent = new Intent(PackageContext,StudentProfileActivity.class);
        sUUID = UUID;
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return StudentProfileFragment.NewInstance(sUUID);
    }


}
