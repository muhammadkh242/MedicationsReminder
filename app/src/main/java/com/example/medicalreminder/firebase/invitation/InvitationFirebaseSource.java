package com.example.medicalreminder.firebase.invitation;

public interface InvitationFirebaseSource {
    public void accept(String email);
    public void deny();
}
