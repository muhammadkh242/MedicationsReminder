package com.example.medicalreminder.model.meddialog;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationList;

public interface RepoDialogInterface {

    public void calcWork(LiveData<MedicationList> list);
    public int calcDelay(String date);
}
