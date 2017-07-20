package com.example.hamid_pc.campusrecruitmentsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentProfileCreationFragment extends Fragment {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseUserReference;
    private EditText mEditTextName;
    private EditText mEditTextContact;
    private EditText mEditTextProgram;
    private EditText mEditTextMajors;
    private EditText mEditTextCGPA;

    private  String mName;
    private  String mContactNum;
    private  String mAcademicProgram;
    private  String mMajors;
    private  String mCGPA;
    private  Button mSubmit;
    private  String mUuid;
    private String mEmail;


    private  Student mStudent;
    private  User mUser;


    public static StudentProfileCreationFragment NewInstance(){
        StudentProfileCreationFragment studentProfileCreationFragment = new StudentProfileCreationFragment();
        return studentProfileCreationFragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("students");
        mDatabaseUserReference = mFirebaseDatabase.getReference().child("users");

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile_creation, container, false);
        mEditTextName = (EditText) view.findViewById(R.id.view_edittext_name);
        mEditTextContact = (EditText) view.findViewById(R.id.view_edittext_contact_num);
        mEditTextProgram = (EditText) view.findViewById(R.id.view_edittext_program);
        mEditTextMajors = (EditText) view.findViewById(R.id.view_edittext_majors);
        mEditTextCGPA = (EditText) view.findViewById(R.id.view_edittext_cgpa);

        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mName = mEditTextName.getText().toString();
                mAcademicProgram = mEditTextProgram.getText().toString();
                mMajors = mEditTextMajors.getText().toString();
                mContactNum = mEditTextContact.getText().toString();
                mCGPA = mEditTextCGPA.getText().toString();
                mUuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                mStudent = new Student(mUuid, mName,mContactNum,mEmail,mAcademicProgram,mMajors,mCGPA);
                mDatabaseReference.push().setValue(mStudent);
                mUser = new User(mUuid,"student");
                mDatabaseUserReference.push().setValue(mUser);
                Intent intent = StudentVacancyListActivity.NewIntent(getActivity());
                startActivity(intent);

            }
        });
        return view;

    }

}
