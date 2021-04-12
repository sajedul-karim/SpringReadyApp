package com.appcoder.springreadyapp.factoryPattern;

public class Admin extends BaseUser {
    private String email;
    private String password;

    public Admin(UserCreationRequest userCreationRequest) {
        System.out.println();

        System.out.println("Building Admin");

        if(userCreationRequest.getPassword() == null || userCreationRequest.getPassword().trim().length()<8){
            System.out.println("Please provide valid Password");
            throw new RuntimeException("Please provide valid Password");
        }

        if(userCreationRequest.getEmail() == null ){
            System.out.println("Please provide valid email address");
            throw new RuntimeException("Please provide valid email address");
        }

        setCommonData(userCreationRequest.getFullName(),userCreationRequest.getMobileNumber(), userCreationRequest.getGender(), userCreationRequest.getUserType());
        this.password = userCreationRequest.getPassword();
        this.email = userCreationRequest.getEmail();

        System.out.println("Admin building done");

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
