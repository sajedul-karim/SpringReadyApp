package com.appcoder.springreadyapp.response;


public class BasicResponse {
    private String response;
    private String dateTime;


    public BasicResponse(String response, String dateTime) {
        this.response = response;
        this.dateTime = dateTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
