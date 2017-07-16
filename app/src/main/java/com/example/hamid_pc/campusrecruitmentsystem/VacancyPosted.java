package com.example.hamid_pc.campusrecruitmentsystem;


public class VacancyPosted {
    private String mRandomID;
    private String mTitle;

    public VacancyPosted(String mRandomID, String mTitle) {
        this.mRandomID = mRandomID;
        this.mTitle = mTitle;
    }

    public VacancyPosted() {
    }

    public String getmRandomID() {
        return mRandomID;
    }

    public void setmRandomID(String mRandomID) {
        this.mRandomID = mRandomID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}

