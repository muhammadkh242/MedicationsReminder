package com.example.medicalreminder.invitation.presenter;

import com.example.medicalreminder.invitation.view.InvitationViewInterface;
import com.example.medicalreminder.model.invitation.InvitationRepoInterface;

public class InvitationPresenter implements InvitationPresenterInterface{

    InvitationRepoInterface invitationRepo;
    InvitationViewInterface viewInterface;

    public InvitationPresenter(InvitationRepoInterface invitationRepo, InvitationViewInterface viewInterface) {
        this.invitationRepo = invitationRepo;
        this.viewInterface = viewInterface;
    }

    @Override
    public void accept(String id) {
        invitationRepo.accept(id);

    }

    @Override
    public void deny(String email) {

    }
}
