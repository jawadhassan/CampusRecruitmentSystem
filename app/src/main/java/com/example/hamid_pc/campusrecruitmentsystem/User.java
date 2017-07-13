package com.example.hamid_pc.campusrecruitmentsystem;


public class User {
    private String mUUID;
    private  String mRole;

    public User(String mUUID, String mRole) {
        this.mUUID = mUUID;
        this.mRole = mRole;
    }

    public User() {
    }

    public String getmUUID() {
        return mUUID;
    }


    public String getmRole() {
        return mRole;
    }

    public void setmRole(String mRole) {
        this.mRole = mRole;
    }
}
