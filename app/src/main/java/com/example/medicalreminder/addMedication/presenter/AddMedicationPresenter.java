package com.example.medicalreminder.addMedication.presenter;

import android.content.Context;
import android.util.Log;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoAddInterface;

import java.util.List;


public class AddMedicationPresenter implements AddMedicationPresenterInterface {

    Context context;
    private static AddMedicationPresenter addMedicationPresenter;
    Medication medication = Medication.getInstance();
    private RepoAddInterface repoInterface;

    private AddMedicationPresenter(Context context, RepoAddInterface repoInterface) {
        this.context = context;
        this.repoInterface = repoInterface;
    }

    public static AddMedicationPresenter getInstance(Context context, RepoAddInterface repoInterfaceI) {
        if (addMedicationPresenter == null) {
            addMedicationPresenter = new AddMedicationPresenter(context, repoInterfaceI);
        }
        return addMedicationPresenter;

    }

    //insert room drug
    @Override
    public void insertDrugOffline(Drug drug) {
        repoInterface.insertDrugOffline(drug);
    }

    //realtime
    @Override
    public void insertDrugRealTime(Drug drug) {
        repoInterface.insertDrugRealTime(drug);
    }

    // insert room medication
    @Override
    public void insertMedicationOffline(List<MedicationDose> medDose) {
        for(int i =0 ;i<medication.getDays().size() ; i++){
            // room medication
            repoInterface.insertMedicatinOffline(new MedicationList(medication.getDays().get(i),medDose));

        }
    }

    //firestore
    @Override
    public void insertMedicationFirestore(List<MedicationDose> medDose) {
        for(int i =0 ;i<medication.getDays().size() ; i++){
            // firestore
            repoInterface.insertMedicationFirestore(new MedicationList(medication.getDays().get(i),medDose));
        }
    }
}
