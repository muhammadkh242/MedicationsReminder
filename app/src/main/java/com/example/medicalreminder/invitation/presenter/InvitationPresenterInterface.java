package com.example.medicalreminder.invitation.presenter;

import com.example.medicalreminder.invitation.view.InvitationViewInterface;
import com.example.medicalreminder.model.invitation.InvitationRepoInterface;

public interface InvitationPresenterInterface {


    public void accept(String id);
    public void deny();
}
