package com.example.medicalreminder.remote.realtime.medicationsmanaging;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationManagingRealTimeInterface {

    MutableLiveData<List<Drug>> getNamesDrugsRealTime();
}
