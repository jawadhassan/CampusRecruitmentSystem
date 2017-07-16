package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;



public class StudentListActivity extends SingleFragmentActivity {


    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, StudentListActivity.class);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return StudentListFragment.NewInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
