package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class OrganizationOptionChooserFragment extends Fragment {

    Button studentListButton;
    Button jobsListButton;


    public static OrganizationOptionChooserFragment NewInstance() {
        OrganizationOptionChooserFragment OP = new OrganizationOptionChooserFragment();
        return OP;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_organization_options, container, false);

        studentListButton = (Button) view.findViewById(R.id.button_student);
        jobsListButton = (Button) view.findViewById(R.id.button_company);

        studentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = StudentListActivity.NewIntent(getActivity());
                startActivity(intent);
            }
        });

        jobsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = VacancyListActivity.NewIntent(getActivity());
                startActivity(intent);
            }
        });

        setHasOptionsMenu(true);

        return view;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miProfile:
                AuthUI.getInstance().signOut(getActivity()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = AuthenticationActivity.newIntent(getActivity());
                            startActivity(intent);

                        }
                    }
                });


        }


        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.sign_out_menu, menu);
    }
}
