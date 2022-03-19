package com.example.medicalreminder.model.seconduser;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface SecondUserRepoInterface {
    public MutableLiveData<List<MedicationList>> getMeds(String date);
}
