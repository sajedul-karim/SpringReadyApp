package com.appcoder.springreadyapp.factoryPattern;

public class Merchant extends BaseUser {
    private String password;
    private String bankAccountNumber;
    private String bankName;
    private String routingNumber;

    public Merchant(UserCreationRequest userCreationRequest) {
        System.out.println();
        System.out.println("Building merchant");

        if(userCreationRequest.getPassword() == null || userCreationRequest.getPassword().trim().length()<8){
            System.out.println("Please provide valid Password");
            throw new RuntimeException("Please provide valid Password");
        }

        if(userCreationRequest.getBankAccountNumber() == null ){
            System.out.println("Please provide valid bank account number");
            throw new RuntimeException("Please provide valid bank account number");
        }

        setCommonData(userCreationRequest.getFullName(),userCreationRequest.getMobileNumber(), userCreationRequest.getGender(), userCreationRequest.getUserType());
        this.password = userCreationRequest.getPassword();
        this.bankAccountNumber = userCreationRequest.getBankAccountNumber();
        this.bankName = userCreationRequest.getBankName();
        this.routingNumber = userCreationRequest.getRoutingNumber();
        System.out.println("Merchant building done");

    }

    public String getPassword() {
        return password;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }
}
