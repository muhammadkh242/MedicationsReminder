package com.example.medicalreminder.ui.addmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medicalreminder.databinding.FragmentAddMedicationBinding;


public class AddMedFragment extends Fragment {

    private FragmentAddMedicationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddMedViewModel addMedViewModel =
                new ViewModelProvider(this).get(AddMedViewModel.class);

        binding = FragmentAddMedicationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}