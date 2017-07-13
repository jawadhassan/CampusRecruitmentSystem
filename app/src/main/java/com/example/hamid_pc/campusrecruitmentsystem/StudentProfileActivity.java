package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;



public class StudentProfileActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context PackageContext){
        Intent intent = new Intent(PackageContext,StudentProfileActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return StudentProfileFragment.NewInstance();
    }
}
