package com.example.medicalreminder.home.view;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface HomeFragmentViewInterface {

    public void showMed(LiveData<MedicationList> medList);
    public void getMed(String date);
}
