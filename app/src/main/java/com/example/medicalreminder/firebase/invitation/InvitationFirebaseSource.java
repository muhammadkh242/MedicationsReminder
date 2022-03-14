package com.example.medicalreminder.firebase.invitation;

public interface InvitationFirebaseSource {
    public void accept(String id);
    public void deny();
}
