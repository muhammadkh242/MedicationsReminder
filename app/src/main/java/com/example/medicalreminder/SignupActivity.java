package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.medicalreminder.databinding.ActivitySignupBinding;


public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}