package com.example.medicalreminder.refillreminder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalreminder.databinding.ActivityHomeBinding;
import com.example.medicalreminder.databinding.ActivityRefillDialogBinding;

public class RefillDialog  extends AppCompatActivity {

    ActivityRefillDialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRefillDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
