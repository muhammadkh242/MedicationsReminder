package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.medicalreminder.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}