package com.example.medicalreminder.remote.firestore.seconduser;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface SecondUserFirebaseInterface {

    //fire store
    public  MutableLiveData<List<MedicationList>> getMeds(String date);

    //real time
    public void storeMed(Drug drug);
}
