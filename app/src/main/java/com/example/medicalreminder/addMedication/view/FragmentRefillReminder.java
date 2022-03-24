package com.example.medicalreminder.addMedication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.RefillReminderScreenBinding;
import com.example.medicalreminder.local.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoAdd;

import java.util.ArrayList;
import java.util.List;

public class FragmentRefillReminder extends Fragment {


    RefillReminderScreenBinding binding;
    Medication medication;
    List<String> list;
    Drug drug;
    AddMedicationPresenterInterface addMedPreI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RefillReminderScreenBinding.inflate(inflater,container, false);
        View root = binding.getRoot();
        getInti();
        medication = (Medication) getArguments().getSerializable("object");
        binding.btnDone.setVisibility(View.VISIBLE);


        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drug.setRefill(Integer.parseInt(binding.editRefill.getText().toString()));
                drug.setTotalPills(Integer.parseInt(binding.editRefillTotal.getText().toString()));
                drug.setName(medication.getName());
                drug.setForm(medication.getForm());
                drug.setDurationDrug(medication.getDurationDrug());
                if (medication.getEveryDayOr().equals("Yes")) {
                    drug.setTimesInDays(medication.getTimesInday());
                } else {
                    drug.setTimesInWeeks(medication.getTimesInWeeks());
                }

                drug.setStatusDrug("no");
                drug.setDays(medication.getDays());
                //room drug
                addMedPreI.insertDrugOffline(drug);
                // room medication
                addMedPreI.insertMedicationOffline(CalculationMedication.medDose);
                // firestore
                addMedPreI.insertMedicationFirestore(CalculationMedication.medDose);
                //realtime
                addMedPreI.insertDrugRealTime(drug);
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return root;

    }
    public void getInti(){
        drug = new Drug();
        list = new ArrayList<>();
        medication = Medication.getInstance();
        addMedPreI = AddMedicationPresenter.getInstance(getContext(),
                RepoAdd.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
    }
}
