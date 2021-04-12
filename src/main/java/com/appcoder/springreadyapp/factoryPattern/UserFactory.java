package com.appcoder.springreadyapp.factoryPattern;

public class UserFactory {
    private static final String USER_TYPE_CUSTOMER = "1";
    private static final String USER_TYPE_MERCHANT = "2";
    private static final String USER_TYPE_ADMIN = "3";

    public static BaseUser buildUser(UserCreationRequest userCreationRequest) {
        BaseUser baseUser;
        switch (userCreationRequest.getUserType()) {
            case USER_TYPE_CUSTOMER:
                baseUser = new Customer(userCreationRequest);
                break;
            case USER_TYPE_MERCHANT:
                baseUser = new Merchant(userCreationRequest);
                break;
            case USER_TYPE_ADMIN:
                baseUser = new Admin(userCreationRequest);
                break;
            default:
                throw new RuntimeException("Invalid user type provided");
        }
        return baseUser;
    }
}
