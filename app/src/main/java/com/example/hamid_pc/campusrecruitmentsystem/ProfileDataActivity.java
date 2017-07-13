package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ProfileDataActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context PackageContext){
        Intent intent = new Intent(PackageContext,ProfileDataActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Check","In Profile Data Activity");
    }

    @Override
    protected Fragment createFragment() {
        return new ProfileDataFragment();
    }
}
