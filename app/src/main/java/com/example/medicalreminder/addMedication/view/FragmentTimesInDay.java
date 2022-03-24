package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.R;
import com.example.medicalreminder.constant.Constant;
import com.example.medicalreminder.databinding.NameDrugQuestionScreenBinding;
import com.example.medicalreminder.databinding.NumberOfTakedDayQuestionScreenBinding;
import com.example.medicalreminder.model.addmedication.Medication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentTimesInDay extends Fragment  implements OnAddMedClickListner {

    NumberOfTakedDayQuestionScreenBinding binding;
    AddMedicationAdapter addMedicationAdapter;
    LinearLayoutManager layoutManager;
    Medication medication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = NumberOfTakedDayQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        getInti();
        medication = (Medication) getArguments().getSerializable("object");

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(addMedicationAdapter);

        addMedicationAdapter.setList(Constant.TIMES_IN_DAYS);
        addMedicationAdapter.notifyDataSetChanged();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.startdateDrugAct,bundle);
            }
        });
        return root;

    }


    @Override
    public void onClick(String txt) {
        medication.setTimesInday(txt);
    }
    public void getInti(){
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        layoutManager = new LinearLayoutManager(getContext());

    }
}