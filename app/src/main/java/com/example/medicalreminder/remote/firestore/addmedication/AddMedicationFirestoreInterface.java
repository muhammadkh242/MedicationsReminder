package com.example.medicalreminder.remote.firestore.addmedication;


import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface AddMedicationFirestoreInterface {

    void insertDrugsFirestore(MedicationList list);

    MutableLiveData<List<MedicationList>> getDrugsFireStore(String date);

    void deleteDrugFirestore( List<String> days,Medication medication);

    void getDrugDaysRealtime(Medication medication);

}