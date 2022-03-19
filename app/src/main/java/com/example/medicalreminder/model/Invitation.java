package com.example.medicalreminder.model;

public class Invitation {
    String email;
    String id;
    boolean invitaion;

    public Invitation() {
    }

    public Invitation(String email, String id, boolean invitaion) {
        this.email = email;
        this.id = id;
        this.invitaion = invitaion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInvitaion(boolean invitaion) {
        this.invitaion = invitaion;
    }

    public boolean isInvitaion() {
        return invitaion;
    }
}
