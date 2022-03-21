package com.example.medicalreminder.medicationsmanaging.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface MedicationsViewInterface {

    public void showMedsRealTime(MutableLiveData<List<Drug>> meds);
    public void getMedsRealTime();
    public void showMedsOffline(LiveData<List<Drug>> list);
    public void getMedsOffline();
}
