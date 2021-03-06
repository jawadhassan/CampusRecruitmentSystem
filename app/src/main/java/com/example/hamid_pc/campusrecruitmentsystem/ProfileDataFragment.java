package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class ProfileDataFragment  extends Fragment{

    Button company;
    Button student;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_data, container, false);
        company = (Button) view.findViewById(R.id.button_company);
        student = (Button) view.findViewById(R.id.button_student);
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = TeacherProfileActivity.newIntent(getContext());
               // startActivity(intent);

                Intent organizationProfileIntent = OrganizationProfileCreationActivity.NewIntent(getActivity());
                startActivity(organizationProfileIntent);

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = StudentProfileActivity.newIntent(getContext());
                //startActivity(intent);

                Intent studentProfileIntent = StudentProfileCreationActivity.NewIntent(getActivity());
                startActivity(studentProfileIntent);

            }
        });
        return view;
    }
}
