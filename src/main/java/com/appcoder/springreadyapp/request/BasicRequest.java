package com.appcoder.springreadyapp.request;

public class BasicRequest {
    private String yourName;
    private String yourAge;

    public BasicRequest() {
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourAge() {
        return yourAge;
    }

    public void setYourAge(String yourAge) {
        this.yourAge = yourAge;
    }
}
