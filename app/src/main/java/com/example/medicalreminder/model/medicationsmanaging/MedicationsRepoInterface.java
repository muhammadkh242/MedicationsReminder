package com.example.medicalreminder.model.medicationsmanaging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationsRepoInterface {

    public MutableLiveData<List<Drug>> getMedsRealtime();
    public LiveData<List<Drug>> getMedsOffline();
}
