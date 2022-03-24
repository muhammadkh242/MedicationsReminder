package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.FragmentHomeBinding;
import com.example.medicalreminder.databinding.NameDrugQuestionScreenBinding;
import com.example.medicalreminder.model.addmedication.Medication;
import java.io.Serializable;


public class FragmentNameDrug  extends Fragment {

    Medication medication;
    NameDrugQuestionScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        medication = Medication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = NameDrugQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        binding.btnNext.setVisibility(View.VISIBLE);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medication.setName(binding.edtNameDrug.getText().toString());
                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.formDrugAct,bundle);
            }
        });
        return root;
    }
}
