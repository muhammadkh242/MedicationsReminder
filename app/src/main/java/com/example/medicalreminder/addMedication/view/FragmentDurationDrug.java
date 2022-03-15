package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;

import com.example.medicalreminder.R;
import com.example.medicalreminder.local.db.ConcreteLocalSource;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.Repo;


import java.util.ArrayList;
import java.util.List;

public class FragmentDurationDrug  extends Fragment  implements OnAddMedClickListner,AddMedicationViewInterface{

    RecyclerView recyclerView;
    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;
    Medication medication;
    AddMedicationPresenterInterface addMedPreI;

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
        Log.i("TAG", "onCreateView: "+medication.getTimesInday()+medication.getHours().get(0)+medication.getFirstTimeDose());

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
                addMedPreI.calListDay(medication);
                addInfoMed(medication);
            }
        });

        return view;
    }

    @Override
    public void onClick(String txt) {
     medication.setDurationDrug(txt);
    }

    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();
        addMedPreI = AddMedicationPresenter.getInstance(getContext(),
                Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));

    }

    @Override
    public void addInfoMed(Medication med) {
        /*LiveData<List<MedicationList>> l = addMedPreI.getInfoMed();
        l.observe( this, new Observer<List<MedicationList>>() {
            @Override
            public void onChanged(List<MedicationList> medicationLists) {
                Log.i("TAG", "onChanged: "+medicationLists.size());

            }
        });*/
    }
}

