package com.example.medicalreminder.addhealthtracker.presenter;


import com.example.medicalreminder.addhealthtracker.view.TrackerViewInterface;
import com.example.medicalreminder.model.healthtracker.TrackerRepoInterface;

public class TrackerPresenter implements TrackerPresenterInterface{
    TrackerRepoInterface trackerRepo;
    TrackerViewInterface viewInterface;


    public TrackerPresenter(TrackerRepoInterface trackerRepo, TrackerViewInterface viewInterface){
        this.trackerRepo = trackerRepo;
        this.viewInterface = viewInterface;
    }


    @Override
    public void sendInvitation(String email) {
        trackerRepo.sendInvitation(email);
    }
}
