package com.appcoder.springreadyapp.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class MongoUser {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private String gender;
    private String mobileNumber;


    public MongoUser() {
    }

    public MongoUser(String id, String userName, String fullName, String gender, String mobileNumber) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
    }

    public MongoUser(String userName, String fullName, String gender, String mobileNumber) {
        this.userName = userName;
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
