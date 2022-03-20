package com.example.medicalreminder.home.presenter;


import com.example.medicalreminder.model.addmedication.Drug;

public interface HomeFragmentPresenterInterface {

    public void getMedHome(String date);

    //realTime
    public void getDrugRealTime(String name);
    public void updateDrugRealTime(Drug drug);
}
