package com.example.medicalreminder.authentication.signin.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.authentication.signin.presenter.SignInPresenter;
import com.example.medicalreminder.authentication.signin.presenter.SignInPresenterInterface;
import com.example.medicalreminder.authentication.signup.view.SignupActivity;
import com.example.medicalreminder.databinding.ActivitySigninBinding;
import com.example.medicalreminder.firebase.auth.FirebaseClient;
import com.example.medicalreminder.firebase.auth.FirebaseSource;
import com.example.medicalreminder.model.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements SignInViewInterface {

    private ActivitySigninBinding binding;
    ProgressDialog progressDialog;
    SignInPresenterInterface presenterInt;
    onSignInClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        presenterInt = new SignInPresenter(
                this, Repository.getInstance(this, FirebaseClient.getInstance(this) ), this);
        listener = new onSignInClickListener() {
            @Override
            public void onClick(String email, String password) {
                saveUserData(email, password);
            }

        };

        binding.txtClickSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignupActivity.class));
            }
        });

        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(binding.editEmailSignin.getText().toString(),
                        binding.editPasswordSignin.getText().toString());
                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
            }
        });


    }

    @Override
    public void saveUserData(String email, String password) {
            presenterInt.checkDataReg(email, password);
    }
}
