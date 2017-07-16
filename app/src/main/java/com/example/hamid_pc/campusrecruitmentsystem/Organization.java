package com.example.hamid_pc.campusrecruitmentsystem;


public class Organization {

    private String mOrganizationName;
    private String mOrganizationAddress;
    private String mOrganizationContact;
    private String mOrganizationType;
    private String mUUID;
    private String mEmail;

    public Organization(String UUID, String OrganizationName, String OrganizationAddress, String OrganizationContact, String Email, String OrganizationType) {
        this.mOrganizationName = OrganizationName;
        this.mOrganizationAddress = OrganizationAddress;
        this.mOrganizationContact = OrganizationContact;
        this.mOrganizationType = OrganizationType;
        this.mEmail = Email;
        this.mUUID = UUID;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmUUID() {
        return mUUID;
    }

    public String getmOrganizationName() {
        return mOrganizationName;
    }

    public void setmOrganizationName(String mOrganizationName) {
        this.mOrganizationName = mOrganizationName;
    }

    public String getmOrganizationAddress() {
        return mOrganizationAddress;
    }

    public void setmOrganizationAddress(String mOrganizationAddress) {
        this.mOrganizationAddress = mOrganizationAddress;
    }

    public String getmOrganizationContact() {
        return mOrganizationContact;
    }

    public void setmOrganizationContact(String mOrganizationContact) {
        this.mOrganizationContact = mOrganizationContact;
    }

    public String getmOrganizationType() {
        return mOrganizationType;
    }

    public void setmOrganizationType(String mOrganizationType) {
        this.mOrganizationType = mOrganizationType;
    }
}
