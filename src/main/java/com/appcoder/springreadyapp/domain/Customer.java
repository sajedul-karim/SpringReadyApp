package com.appcoder.springreadyapp.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
//@Table(name = "CUSTOMER", schema = "testdb")
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String presentAddress;
    private String permanentAddress;
    private String gender;

    public Customer() {
    }

    public Customer(Object[] col) {
        this.id = (col[0] != null)? ((BigInteger) col[0]).longValue() :0;
        this.firstName = (String) col[1];
        this.lastName = (String) col[2];
        this.mobileNumber = (String) col[3];

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Column(name = "presentAddress")
    public String getPermanentAddress() {
        return presentAddress;
    }

    public void setPermanentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    @Basic
    @Column(name = "permanentAddress")
    public String getPresentAddress() {
        return permanentAddress;
    }

    public void setPresentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}