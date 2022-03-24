package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.NameDrugQuestionScreenBinding;
import com.example.medicalreminder.databinding.StartdateDrugQuestionScreenBinding;
import com.example.medicalreminder.model.addmedication.Medication;

import java.io.Serializable;

public class FragmentStartDate extends Fragment {

    Medication medication;
    StartdateDrugQuestionScreenBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = StartdateDrugQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        medication = (Medication) getArguments().getSerializable("object");
        binding.btnNext.setVisibility(View.VISIBLE);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = binding.date.getDayOfMonth();
                int month =   (binding.date.getMonth() + 1);
                int year =  binding.date.getYear();
                medication.setFirstDateDose(day+"-"+month+"-"+year);

                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.startTimeAct,bundle);

            }
        });
        return root;
    }
}
