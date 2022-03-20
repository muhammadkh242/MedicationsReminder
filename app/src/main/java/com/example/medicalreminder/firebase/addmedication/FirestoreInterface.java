package com.example.medicalreminder.firebase.addmedication;


import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;
import java.util.Map;

public interface FirestoreInterface {

    public void addDrugs(MedicationList list);
    public MutableLiveData<List<MedicationList>> getDrugs(String date);
    public void deleteDrugFireStore(List<String> days, Medication medication);

    public List<String> getDrugsDaysRealtime(String name);
    public void deleteDrugRealtime(String date);

}
