package com.example.medicalreminder.model.invitation;

public class Invitation {
    String email;
    String id;
    boolean invitaion;
    String reply;

    public Invitation() {
    }

    public Invitation(String email, String id, boolean invitaion, String reply) {
        this.email = email;
        this.id = id;
        this.invitaion = invitaion;
        this.reply = reply;
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

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInvitaion(boolean invitaion) {
        this.invitaion = invitaion;
    }

    public String getReply() {
        return reply;
    }

    public boolean isInvitaion() {
        return invitaion;
    }
}
