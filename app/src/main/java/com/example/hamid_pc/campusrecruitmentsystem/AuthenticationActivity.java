package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class AuthenticationActivity extends AppCompatActivity {


        private boolean signed_in;
        private FirebaseAuth mFireAuth;
        private FirebaseDatabase mFirebaseDatabase;
        private DatabaseReference mDatabaseReference;
        private DatabaseReference mUserReference;
        private FirebaseAuth.AuthStateListener mAuthListener;
        private Query query;
        private final String ADMIN =  "administrator";
        private final String STUDENT =  "student";
        private final String COMPANY = "company";

        public static Intent newIntent(Context packageContext) {
            Intent intent = new Intent(packageContext, AuthenticationActivity.class);
            return intent;

        }



        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            mFireAuth = FirebaseAuth.getInstance();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mUserReference = mFirebaseDatabase.getReference().child("users");

            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


                    if (firebaseUser != null) {
                        query = mUserReference.orderByChild("mUUID").equalTo(firebaseUser.getUid());
                        
                        mUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(!dataSnapshot.hasChildren()){
                                  Log.d("Check","In Authentication Directing to Admin Panel");
                                   User user = new User(firebaseUser.getUid(),"administrator");
                                   mUserReference.push().setValue(user);
                                }else {
                                    startProfileActivity();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



                        query.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                User userData = dataSnapshot.getValue(User.class);
                                if (userData.getmRole().equals(STUDENT)) {
                                   // Intent intent = CourseListActivity.newIntent(AuthenticationActivity.this);
                                   // startActivity(intent);
                                   Log.d("Check","In Authentication: User is Student");

                                } else if (userData.getmRole().equals(COMPANY)) {

                                   // Intent intent = CourseListStudentPanelActivity.newIntent(AuthenticationActivity.this);
                                   // startActivity(intent);
                                    Log.d("Check","In Authentication: User is Company");


                                }else if(userData.getmRole().equals(ADMIN)){
                                    Log.d("Check","In Authentication: User is Admin");
                                }
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



                    } else {
                        startActivity(
                                AuthUI.getInstance()
                                        .createSignInIntentBuilder()
                                        .setIsSmartLockEnabled(false)
                                        .setProviders(AuthUI.GOOGLE_PROVIDER,
                                                AuthUI.EMAIL_PROVIDER)
                                        .build());
                    }
                }
            };
        }



        public void startProfileActivity(){
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        Intent intent = ProfileDataActivity.newIntent(AuthenticationActivity.this);
                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }



        @Override
        public void onResume() {
            super.onResume();
            mFireAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        public void onPause() {
            super.onPause();
            if (mAuthListener != null) {
                mFireAuth.removeAuthStateListener(mAuthListener);
            }
        }

    }

