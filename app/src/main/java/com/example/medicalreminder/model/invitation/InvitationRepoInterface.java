package com.example.medicalreminder.model.invitation;

public interface InvitationRepoInterface {
    public void accept(String id);
    public void deny(String id);
}
