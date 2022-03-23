package com.example.medicalreminder.model.invitation;

public class Invitation {
    String email;
    String id;
    boolean invitaion;
    String reply;
    String take;

    public Invitation() {
    }

    public Invitation(String email, String id, boolean invitaion, String reply, String take) {
        this.email = email;
        this.id = id;
        this.invitaion = invitaion;
        this.reply = reply;
        this.take = take;
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

    public void setTake(String take) {
        this.take = take;
    }

    public String getTake() {
        return take;
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
