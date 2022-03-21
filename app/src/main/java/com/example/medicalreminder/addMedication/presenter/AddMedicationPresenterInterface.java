package com.example.medicalreminder.addMedication.presenter;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import java.util.List;


public interface AddMedicationPresenterInterface {

    public void insertDrugOffline(Drug drug);
    public void insertDrugRealTime(Drug drug);
    public void insertMedicationOffline(List<MedicationDose> medDose);
    public void insertMedicationFirestore(List<MedicationDose> medDose);

}
