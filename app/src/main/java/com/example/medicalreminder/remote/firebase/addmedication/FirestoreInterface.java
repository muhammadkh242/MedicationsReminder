package com.example.medicalreminder.remote.firebase.addmedication;


import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface FirestoreInterface {

    void insertDrugsOnline(MedicationList list);

    MutableLiveData<List<MedicationList>> getDrugsOnline(String date);

    void deleteDrugOnline(List<String> days, Medication medication);
}
