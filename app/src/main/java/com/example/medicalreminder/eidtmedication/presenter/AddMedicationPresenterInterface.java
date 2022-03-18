package com.example.medicalreminder.eidtmedication.presenter;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;

public interface AddMedicationPresenterInterface {


    public int getAnswer(Medication medication);
    public void calListHour(Medication medication);
    public void calListDay(Medication medication);
    public void insertDrugDetails(Drug drug);

}
