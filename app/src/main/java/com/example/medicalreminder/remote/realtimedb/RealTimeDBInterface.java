package com.example.medicalreminder.remote.realtimedb;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface RealTimeDBInterface {

    List<String> getDrugsDaysRealtime(String name);

    void deleteDrugRealtime(String date);

    Drug getDataRealTime(String name);

    void updateRealTime(Drug drug);

    MutableLiveData<List<Drug>> getMedNamesRealTime();
}
