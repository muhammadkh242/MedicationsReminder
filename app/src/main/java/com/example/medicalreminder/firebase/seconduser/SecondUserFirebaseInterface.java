package com.example.medicalreminder.firebase.seconduser;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface SecondUserFirebaseInterface {

    //fire store
    public  MutableLiveData<List<MedicationList>> getMeds();

    //real time
    public void storeMed(UserMed userMed);
}
