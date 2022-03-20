package com.example.medicalreminder.medicationsmanaging.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationsViewInterface {
    public void showMeds(MutableLiveData<List<Drug>> meds);
    public void getMeds();
    public void showAllMeds(LiveData<List<Drug>> list);
    public void getAllMeds();
}
