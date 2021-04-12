package com.appcoder.springreadyapp.factoryPattern;

public abstract class BaseUser {
    private String fullName;
    private String mobileNumber;
    private String gender;
    private String userType;

    public BaseUser() {
    }

    public void setCommonData(String fullName, String mobileNumber, String gender, String userType){
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.userType = userType;

    }

    public String getFullName() {
        return fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getUserType() {
        return userType;
    }
}
