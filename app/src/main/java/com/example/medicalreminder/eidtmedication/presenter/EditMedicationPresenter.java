package com.example.medicalreminder.eidtmedication.presenter;

import android.content.Context;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.editmedication.RepoEditInterface;

public class EditMedicationPresenter implements EditMedicationPresenterInterface{

    Context context;
    private static EditMedicationPresenterInterface editMedpI;
    Medication medication;
    private RepoEditInterface repoEditInterface;

    private EditMedicationPresenter(Context context, RepoEditInterface repoEditInterface) {
        this.context = context;
        this.repoEditInterface = repoEditInterface;
    }
    public static EditMedicationPresenterInterface getInstance(Context context, RepoEditInterface repoInterfaceI) {
        if (editMedpI == null) {
            editMedpI = new EditMedicationPresenter(context, repoInterfaceI);
        }
        return editMedpI;
    }

    @Override
    public void deleteDrugFirestore(Medication medication) {
        this.medication = medication;
        repoEditInterface.deleteDrugFirestore(
                repoEditInterface.getDrugsDaysRealtime(medication.getName()),
                medication);
    }
}
