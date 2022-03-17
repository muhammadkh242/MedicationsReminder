package com.example.medicalreminder.medicationsmanaging.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface MedicationsPresenterInterface {
    public void getMeds();
    public void getAllMeds();
}

