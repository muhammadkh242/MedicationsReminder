package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;

import com.example.medicalreminder.R;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.Repo;

import java.io.Serializable;

public class FragmentStartTime extends Fragment{

    Medication medication;
    AddMedicationPresenterInterface addMedPreI;
    TimePicker timePicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.starttime_dose_question_screen, container, false);
        medication = Medication.getInstance();
        addMedPreI = AddMedicationPresenter.getInstance(getContext(), Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
        timePicker = view.findViewById(R.id.date);
        timePicker.setIs24HourView(true);


        medication = (Medication) getArguments().getSerializable("object");
        Log.i("TAG", "onCreateView: "+medication.getName()+ medication.getForm()+medication.getTimesInday()+medication.getFirstDateDose());


        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour, minute;
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                medication.setFirstTimeDose(hour+":"+minute);
                addMedPreI.calListHour(medication);
                NavController navController= Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.durationDrugAct,bundle);
            }
        });

        return view;
    }

}
