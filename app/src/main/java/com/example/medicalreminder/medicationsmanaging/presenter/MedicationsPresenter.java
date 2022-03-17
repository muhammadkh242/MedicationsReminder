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

    @Override
    public void getMeds() {
        viewInterface.showMeds(repo.getMeds());
    }

    @Override
    public void getAllMeds() {
        viewInterface.showAllMeds(repo.getAllMeds());
    }
}
