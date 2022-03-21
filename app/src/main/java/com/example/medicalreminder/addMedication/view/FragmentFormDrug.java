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
import com.example.medicalreminder.databinding.FormMedQuestionScreenBinding;
import com.example.medicalreminder.model.addmedication.Medication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentFormDrug  extends Fragment implements OnAddMedClickListner {

    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;
    Medication medication;
    FormMedQuestionScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FormMedQuestionScreenBinding.inflate(inflater,container, false);
        View root = binding.getRoot();
        getInti();
        medication = (Medication) getArguments().getSerializable("object");

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(addMedicationAdapter);

        list.add("pill");
        list.add("injection");
        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

       binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.everydayOrAct,bundle);
            }
        });
        return root;

    }

    @Override
    public void onClick(String txt) {
        medication.setForm(txt);
    }
    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        layoutManager = new LinearLayoutManager(getContext());

    }
}
