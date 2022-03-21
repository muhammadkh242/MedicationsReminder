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
import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.StarttimeDoseQuestionScreenBinding;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.reposatiry.Repo;

import java.io.Serializable;

public class FragmentStartTime extends Fragment{

    Medication medication;
    AddMedicationPresenterInterface addMedPreI;
    StarttimeDoseQuestionScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = StarttimeDoseQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        addMedPreI = AddMedicationPresenter.getInstance(getContext(), Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
        binding.date.setIs24HourView(true);
        medication = (Medication) getArguments().getSerializable("object");

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour, minute;
                hour = binding.date.getHour();
                minute = binding.date.getMinute();

                medication.setFirstTimeDose(hour+":"+minute);
                CalculationMedication.calListHour();
                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.durationDrugAct,bundle);
            }
        });

        return root;
    }

}
