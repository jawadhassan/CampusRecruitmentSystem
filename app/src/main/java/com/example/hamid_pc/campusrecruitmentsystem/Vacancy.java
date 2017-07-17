package com.example.hamid_pc.campusrecruitmentsystem;


public class Vacancy {
    private String mTitle;
    private String mDetail;
    private String mArea;
    private String mPosition;
    private String mAgeLimit;
    private String mOrganizationID;


    public Vacancy() {
    }

    public Vacancy(String UUID, String mTitle, String mDetail, String mArea, String mPosition, String mAgeLimit) {
        this.mTitle = mTitle;
        this.mDetail = mDetail;
        this.mArea = mArea;
        this.mPosition = mPosition;
        this.mAgeLimit = mAgeLimit;
        this.mOrganizationID = UUID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public String getmArea() {
        return mArea;
    }

    public void setmArea(String mArea) {
        this.mArea = mArea;
    }

    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    public String getmAgeLimit() {
        return mAgeLimit;
    }

    public void setmAgeLimit(String mAgeLimit) {
        this.mAgeLimit = mAgeLimit;
    }

    public String getmOrganizationID() {
        return mOrganizationID;
    }

    public void setmOrganizationID(String mOrganizationID) {
        this.mOrganizationID = mOrganizationID;
    }
}
