package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ApplicantListActivity extends SingleFragmentActivity {

    private static String sDataSnapShotKey;

    public static Intent NewIntent(Context packageContext, String snapShotKey) {
        Intent intent = new Intent(packageContext, ApplicantListActivity.class);
        sDataSnapShotKey = snapShotKey;
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected Fragment createFragment() {

        return ApplicantListFragment.NewInstance(sDataSnapShotKey);
    }
}
