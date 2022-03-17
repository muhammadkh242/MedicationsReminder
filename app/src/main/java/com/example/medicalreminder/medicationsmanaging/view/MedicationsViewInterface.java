package com.example.medicalreminder.medicationsmanaging.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface MedicationsViewInterface {
    public void showMeds(MutableLiveData<List<UserMed>> meds);
    public void getMeds();
    public void showAllMeds(LiveData<List<MedicationList>> list);
    public void getAllMeds();
}
