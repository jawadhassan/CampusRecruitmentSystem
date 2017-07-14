package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class StudentProfileCreationActivity extends SingleFragmentActivity {



    public static Intent NewIntent(Context PackageContext){
        Intent intent = new Intent(PackageContext,StudentProfileCreationActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return StudentProfileCreationFragment.NewInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
