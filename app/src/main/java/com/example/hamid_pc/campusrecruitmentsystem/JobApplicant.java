package com.example.hamid_pc.campusrecruitmentsystem;



public class JobApplicant {
    String mUUID;
    String mName;

    public JobApplicant(String mUUID, String mName) {
        this.mUUID = mUUID;
        this.mName = mName;
    }

    public JobApplicant() {
    }

    public String getmUUID() {
        return mUUID;
    }

    public void setmUUID(String mUUID) {
        this.mUUID = mUUID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
