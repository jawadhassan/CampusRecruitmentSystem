package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class VacancyCreationActivity extends SingleFragmentActivity {

    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, VacancyCreationActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return VacancyCreationFragment.NewInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
