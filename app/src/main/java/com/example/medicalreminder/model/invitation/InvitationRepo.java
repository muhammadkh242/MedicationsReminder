package com.example.medicalreminder.model.invitation;

import android.content.Context;

import com.example.medicalreminder.firebase.addhealthtracker.TrackerFirebaseSource;
import com.example.medicalreminder.firebase.invitation.InvitationFirebaseSource;
import com.example.medicalreminder.model.healthtracker.TrackerRepo;

public class InvitationRepo implements InvitationRepoInterface{

    InvitationFirebaseSource invitationFirebaseSource;
    private static InvitationRepo invitationRepo = null;
    private Context context;


    private InvitationRepo(InvitationFirebaseSource invitationFirebaseSource, Context context) {
        this.invitationFirebaseSource = invitationFirebaseSource;
        this.context = context;
    }

    public static InvitationRepo getInstance(InvitationFirebaseSource invitationFirebaseSource, Context context){
        if(invitationRepo == null){
            invitationRepo = new InvitationRepo(invitationFirebaseSource, context);
        }
        return invitationRepo;
    }

    @Override
    public void accept() {
        invitationFirebaseSource.accept();
    }

    @Override
    public void deny() {
        invitationFirebaseSource.deny();

    }
}
