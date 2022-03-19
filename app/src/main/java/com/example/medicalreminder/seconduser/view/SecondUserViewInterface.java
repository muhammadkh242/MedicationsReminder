package com.example.medicalreminder.seconduser.view;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface SecondUserViewInterface {
    public void showData(MutableLiveData<List<MedicationList>> medList);
    public void getMeds(String date);

}
