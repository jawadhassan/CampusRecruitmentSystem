package com.example.hamid_pc.campusrecruitmentsystem;



public class Student {

    private String mName;
    private String mEnrolledProgram;


    public Student() {
    }

    public Student(String mName, String mEnrolledProgram) {
        this.mName = mName;
        this.mEnrolledProgram = mEnrolledProgram;
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
