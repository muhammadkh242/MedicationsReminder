package com.example.medicalreminder.medicationsmanaging.presenter;

import com.example.medicalreminder.medicationsmanaging.view.MedicationsViewInterface;
import com.example.medicalreminder.model.medicationsmanaging.MedicationsRepoInterface;

public class MedicationsPresenter implements MedicationsPresenterInterface{

    MedicationsRepoInterface repo;
    MedicationsViewInterface viewInterface;

    public MedicationsPresenter(MedicationsRepoInterface repo, MedicationsViewInterface viewInterface) {
        this.repo = repo;
        this.viewInterface = viewInterface;
    }

    //remote
    @Override
    public void getMedsRealtime() {
        viewInterface.showMedsRealTime(repo.getMedsRealtime());
    }

    //room
    @Override
    public void getMedsOffline() {
        viewInterface.showMedsOffline(repo.getMedsOffline());
    }
}
