package com.example.hamid_pc.campusrecruitmentsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudentProfileFragment extends Fragment {


    private static String sUUID;
    final private String ADMIN = "administrator";
    private TextView mUserName;
    private TextView mEmail;
    private TextView mContact;
    private TextView mProgram;
    private TextView mMajors;
    private TextView mCgpa;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAdminReference;
    private DatabaseReference mUserReference;
    private String mUUID;
    private Student mStudent;
    private DatabaseReference mStudentRef;
    private DatabaseReference mUserRef;

    public static StudentProfileFragment NewInstance(String UUID) {
        StudentProfileFragment studentProfileFragment = new StudentProfileFragment();
        sUUID = UUID;
        return studentProfileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("students");
        mAdminReference = mFirebaseDatabase.getReference("users");
        mUserReference = mFirebaseDatabase.getReference("users");
        mUUID = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_profile,container,false);

        mUserName = (TextView) view.findViewById(R.id.student_name);
        mEmail = (TextView) view.findViewById(R.id.email);
        mContact = (TextView) view.findViewById(R.id.student_contact);
        mProgram = (TextView) view.findViewById(R.id.program_enrolled);
        mMajors = (TextView) view.findViewById(R.id.majors);
        mCgpa = (TextView) view.findViewById(R.id.cgpa);

        mDatabaseReference.orderByChild("mUuid").equalTo(sUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                mStudentRef = dataSnapshot.getRef();

                mStudent = dataSnapshot.getValue(Student.class);
                mUserName.setText(mStudent.getmName());
                mEmail.setText(mStudent.getmEmail());
                mContact.setText(mStudent.getmContactNumber());
                mProgram.setText(mStudent.getmEnrolledProgram());
                mMajors.setText(mStudent.getmMajors());
                mCgpa.setText(mStudent.getmCgpa());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mUserReference.orderByChild("mUUID").equalTo(sUUID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mUserRef = dataSnapshot.getRef();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        EnableDeleteMenu();


        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miDelete:
                Log.d("Check", "Student Profile Activity: Deleted Menu Item Selected");
                mStudentRef.removeValue();
                mUserRef.removeValue();
                getActivity().finish();


        }


        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.delete_menu, menu);
    }


    public void EnableDeleteMenu() {

        mAdminReference.orderByChild("mUUID").equalTo(mUUID).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user = dataSnapshot.getValue(User.class);
                if (user.getmRole().compareToIgnoreCase(ADMIN) == 0)
                    setHasOptionsMenu(true);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


    }




}
