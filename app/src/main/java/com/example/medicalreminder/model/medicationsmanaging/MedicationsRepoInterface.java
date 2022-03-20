package com.example.medicalreminder.model.medicationsmanaging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationsRepoInterface {

    public MutableLiveData<List<Drug>> getMeds();
    public LiveData<List<Drug>> getAllMeds();
}
