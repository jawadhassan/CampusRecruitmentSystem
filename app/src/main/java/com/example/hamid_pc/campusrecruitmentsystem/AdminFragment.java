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

public class AdminFragment extends Fragment {

    private Button mCompanyButton;
    private Button mStudentButton;


    public static AdminFragment NewInstance() {
        AdminFragment adminFragment = new AdminFragment();
        return adminFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        mCompanyButton = (Button) view.findViewById(R.id.button_company);
        mStudentButton = (Button) view.findViewById(R.id.button_student);


        mCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = StudentListActivity.NewIntent(getActivity());
                startActivity(intent);


            }
        });

        setHasOptionsMenu(true);

        return view;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: Fix the behavior: Application minimizes when task gets successful, The expected behavior is starting authentication activity
        switch (item.getItemId()) {
            case R.id.miProfile:
                AuthUI.getInstance().signOut(getActivity()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AuthenticationActivity.newIntent(getActivity());
                            getActivity().finish();
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
