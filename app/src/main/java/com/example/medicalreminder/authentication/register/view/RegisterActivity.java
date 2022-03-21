package com.example.medicalreminder.authentication.register.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.authentication.register.presenter.RegisterPresenter;
import com.example.medicalreminder.authentication.register.presenter.RegisterPresenterInterface;
import com.example.medicalreminder.authentication.login.view.LoginActivity;
import com.example.medicalreminder.databinding.ActivitySigninBinding;
import com.example.medicalreminder.remote.firebase.auth.FirebaseClient;
import com.example.medicalreminder.model.authentication.repository.Repository;

public class RegisterActivity extends AppCompatActivity implements RegisterViewInterface {

    private ActivitySigninBinding binding;
    ProgressDialog progressDialog;
    RegisterPresenterInterface presenterInt;
    onRegisterClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        presenterInt = new RegisterPresenter(
                this, Repository.getInstance(this, FirebaseClient.getInstance(this) ), this);
        listener = new onRegisterClickListener() {
            @Override
            public void onClick(String email, String password) {
                saveUserData(email, password);
            }

        };

        binding.txtClickSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(binding.editEmailSignin.getText().toString(),
                        binding.editPasswordSignin.getText().toString());
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }
        });


    }

    @Override
    public void saveUserData(String email, String password) {
            presenterInt.checkDataReg(email, password);
    }


}
