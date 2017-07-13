package com.example.hamid_pc.campusrecruitmentsystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;



public class StudentListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return StudentListFragment.NewInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
