package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Hamid-PC on 7/15/2017.
 */

public class VacancyDetailActivity extends SingleFragmentActivity {

    private static String sRandomUuid;


    public static Intent NewIntent(Context packageContext, String RandomUuid) {
        Intent intent = new Intent(packageContext, VacancyDetailActivity.class);
        sRandomUuid = RandomUuid;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Log.d("Check", "In Detail Activity " + sRandomUuid);
        return VacancyDetailFragment.NewInstance(sRandomUuid);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
