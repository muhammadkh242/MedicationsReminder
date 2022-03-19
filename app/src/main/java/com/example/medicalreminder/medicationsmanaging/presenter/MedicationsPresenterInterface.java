package com.example.medicalreminder.medicationsmanaging.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface MedicationsPresenterInterface {
    //remote
    public void getMeds();

    //local
    public void getAllMeds();
}

