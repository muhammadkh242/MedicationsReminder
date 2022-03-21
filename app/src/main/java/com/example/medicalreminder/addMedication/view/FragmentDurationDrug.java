package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;

import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.DurationOfDrugQuestionScreenBinding;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.reposatiry.Repo;
import com.example.medicalreminder.remote.firebase.seconduser.SecondUserFirebaseClient;


import java.util.ArrayList;
import java.util.List;

public class FragmentDurationDrug  extends Fragment  implements OnAddMedClickListner {

    DurationOfDrugQuestionScreenBinding binding;
    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;
    Medication medication;
    AddMedicationPresenterInterface addMedPreI;
    Drug drug;

    //_______KHOLIF REFERENCEs TO STORE MED DATA IN REALTIMA DB FIREBASE____
    SecondUserFirebaseClient userFirebaseClient = new SecondUserFirebaseClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DurationOfDrugQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        getInti();
        medication = (Medication) getArguments().getSerializable("object");

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(addMedicationAdapter);

        list.add("30 days");
        list.add("1 week");
        list.add("10 days");
        list.add("5 days");

        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug.setName(medication.getName());
                drug.setForm(medication.getForm());
                drug.setDurationDrug(medication.getDurationDrug());
                drug.setTimesInDays(medication.getTimesInday());
                drug.setStatusDrug("no");
                CalculationMedication.calDuration();
                drug.setDays(medication.getDays());
                //room drug
                addMedPreI.insertDrugOffline(drug);
                // room medication
                addMedPreI.insertMedicationOffline(CalculationMedication.medDose);
                // firestore
                addMedPreI.insertMedicationFirestore(CalculationMedication.medDose);
                //realtime
                storeMed(drug);
            }
        });
        return root;
    }
    @Override
    public void onClick(String txt) {
        medication.setDurationDrug(txt);
    }
    public void getInti(){
        drug = new Drug();
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();
        addMedPreI = AddMedicationPresenter.getInstance(getContext(),
                Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
    }
    public void storeMed(Drug drug){
        userFirebaseClient.storeMed(drug);
    }
}

