package com.example.hamid_pc.campusrecruitmentsystem;



public class Student {

    private String mName;
    private String mEnrolledProgram;
    private String mContactNumber;
    private String mEmail;
    private String mMajors;
    private String mCgpa;
    private String mUuid;

    public String getmUuid() {
        return mUuid;
    }

    public String getmMajors() {
        return mMajors;
    }

    public void setmMajors(String mMajors) {
        this.mMajors = mMajors;
    }

    public String getmCgpa() {
        return mCgpa;
    }

    public void setmCgpa(String mCgpa) {
        this.mCgpa = mCgpa;
    }

    public String getmContactNumber() {
        return mContactNumber;
    }

    public void setmContactNumber(String mContactNumber) {
        this.mContactNumber = mContactNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public Student() {
    }

    public Student(String UUID,String Name,String ContactNumber, String Email, String EnrolledProgram, String Majors,String Cgpa) {
        this.mName = Name;
        this.mEnrolledProgram = mEnrolledProgram;
        this.mContactNumber = ContactNumber;
        this.mEmail = Email;
        this.mCgpa = Cgpa;
        this.mMajors = Majors;
        this.mUuid = UUID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEnrolledProgram() {
        return mEnrolledProgram;
    }

    public void setmEnrolledProgram(String mEnrolledProgram) {
        this.mEnrolledProgram = mEnrolledProgram;
    }
}
