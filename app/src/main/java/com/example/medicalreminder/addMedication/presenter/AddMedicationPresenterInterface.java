package com.example.medicalreminder.addMedication.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;

import java.util.List;

public interface AddMedicationPresenterInterface {

    public LiveData<List<Drug>> getInfoMed();
    public void addInfoMed(Medication medication);
    public int getAnswer(Medication medication);
    public void calListHour(Medication medication);
    public void calListDay(Medication medication);

}
