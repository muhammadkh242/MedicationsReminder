package com.example.medicalreminder.model.healthtracker;

public class RequestUser {
    private String userID;
    private String userEmail;
    private String requesterID;
    private boolean request;

    public RequestUser() {
    }

    public RequestUser(String userID, String userEmail, String requesterID, boolean request) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.requesterID = requesterID;
        this.request = request;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
}
