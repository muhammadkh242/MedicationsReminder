package com.example.medicalreminder.authentication.signup.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.authentication.signup.presenter.SignupPresenter;
import com.example.medicalreminder.authentication.signup.presenter.SignupPresenterInterface;
import com.example.medicalreminder.databinding.ActivitySignupBinding;
import com.example.medicalreminder.firebase.auth.FirebaseClient;
import com.example.medicalreminder.firebase.auth.FirebaseSource;
import com.example.medicalreminder.model.Repository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignupActivity extends AppCompatActivity implements SignupViewInterface {

    private ActivitySignupBinding binding;
    private GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN = 1;
    private onSignupClickListener listener;
    FirebaseAuth auth;
    SignupPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        presenter = new SignupPresenter(this,
                Repository.getInstance(this, FirebaseClient.getInstance(this)), this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString())
                .requestEmail()
                .build();
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perForLogin();
                Log.i("TAG", "onClick: ");
                listener = new onSignupClickListener() {
                    @Override
                    public void onClick(String email, String password) {
                        Log.i("TAG", "onClick: ");
                        saveUser(email, password);
                        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                    }
                };
            }
        });

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        binding.googleSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    public void saveUser(String email, String password) {
        presenter.checkDataLogin(email, password);
        Log.i("TAG", "saveUser: ");
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(this, "SignIn Successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(account);
        } catch (ApiException e) {
            Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                } else {
                    Toast.makeText(SignupActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String name = account.getDisplayName();
            String email = account.getEmail();
            Uri photoUrl = account.getPhotoUrl();
            Toast.makeText(this, name + email, Toast.LENGTH_SHORT).show();
        }
    }

}
