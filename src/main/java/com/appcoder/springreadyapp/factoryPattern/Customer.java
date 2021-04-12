package com.appcoder.springreadyapp.factoryPattern;

public class Customer extends BaseUser {
    private String pin;

    public Customer(UserCreationRequest userCreationRequest) {
        System.out.println();

        System.out.println("Building customer");
        if(userCreationRequest.getPin() == null || userCreationRequest.getPin().trim().length()!=4){
            System.out.println("Please provide valid PIN");
            throw new RuntimeException("Please provide valid PIN");
        }
        setCommonData(userCreationRequest.getFullName(),userCreationRequest.getMobileNumber(), userCreationRequest.getGender(), userCreationRequest.getUserType());
        this.pin = userCreationRequest.getPin();
        System.out.println("Customer building done");

    }

    public String getPin() {
        return pin;
    }
}
