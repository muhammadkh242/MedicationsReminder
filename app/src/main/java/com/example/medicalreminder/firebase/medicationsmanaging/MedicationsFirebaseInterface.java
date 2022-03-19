package com.example.medicalreminder.firebase.medicationsmanaging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationsFirebaseInterface {
    public MutableLiveData<List<Drug>> getMeds();

}
