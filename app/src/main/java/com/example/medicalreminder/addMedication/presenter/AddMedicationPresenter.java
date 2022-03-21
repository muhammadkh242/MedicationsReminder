package com.example.medicalreminder.addMedication.presenter;

import android.content.Context;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoInterface;

import java.util.List;


public class AddMedicationPresenter implements AddMedicationPresenterInterface {

    Context context;
    private static AddMedicationPresenter addMedicationPresenter;
    Medication medication = Medication.getInstance();
    private RepoInterface repoInterface;

    private AddMedicationPresenter(Context context, RepoInterface repoInterface) {
        this.context = context;
        this.repoInterface = repoInterface;
    }

    public static AddMedicationPresenter getInstance(Context context, RepoInterface repoInterfaceI) {
        if (addMedicationPresenter == null) {
            addMedicationPresenter = new AddMedicationPresenter(context, repoInterfaceI);
        }
        return addMedicationPresenter;

    }

    //insert room drug
    @Override
    public void insertDrugOffline(Drug drug) {
        repoInterface.insertDrugDetailsOffline(drug);
    }

    @Override
    public void insertMedicationOffline(List<MedicationDose> medDose) {
        for(int i =0 ;i<medication.getDays().size() ; i++){
            // room medication
            repoInterface.insertDrugOffline(new MedicationList(medication.getDays().get(i),medDose));

        }
    }

    @Override
    public void insertMedicationFirestore(List<MedicationDose> medDose) {
        for(int i =0 ;i<medication.getDays().size() ; i++){
            // room medication
            repoInterface.insertDrugOffline(new MedicationList(medication.getDays().get(i),medDose));
        }
    }
}
