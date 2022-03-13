package com.example.medicalreminder.model.invitation;

public interface InvitationRepoInterface {
    public void accept(String email);
    public void deny(String email);
}
