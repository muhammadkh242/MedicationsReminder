package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;

import com.example.medicalreminder.R;
import com.example.medicalreminder.firebase.seconduser.SecondUserFirebaseClient;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;

import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.Repo;


import java.util.ArrayList;
import java.util.List;

public class FragmentDurationDrug  extends Fragment  implements OnAddMedClickListner {

    RecyclerView recyclerView;
    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;
    Medication medication;
    AddMedicationPresenterInterface addMedPreI;
    Drug drug;

    //_______KHOLIF REFERENCEs TO STORE MED DATA IN REALTIMA DB FIREBASE____
    SecondUserFirebaseClient userFirebaseClient = new SecondUserFirebaseClient();
    UserMed userMed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.duration_of_drug_question_screen, container, false);
        getInti();
        medication = (Medication) getArguments().getSerializable("object");

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addMedicationAdapter);

        list.add("30 days");
        list.add("1 week");
        list.add("10 days");
        list.add("5 days");

        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drug.setName(medication.getName());
                drug.setForm(medication.getForm());
                drug.setDurationDrug(medication.getDurationDrug());
                drug.setTimesInDays(medication.getTimesInday());
                addMedPreI.calListDay(medication);
                drug.setDays(medication.getDays());
                addMedPreI.insertDrugDetails(drug);
                //_______KHOLIF CMETHOD CALLING TO STORE MED DATA IN REALTIMA DB FIREBASE____
                storeMed(drug);
            }
        });

        return view;

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
    //_______KHOLIF METHOD TO STORE MED DATA IN REALTIMA DB FIREBASE____
    public void storeMed(Drug drug){
        userFirebaseClient.storeMed(drug);
    }
}

