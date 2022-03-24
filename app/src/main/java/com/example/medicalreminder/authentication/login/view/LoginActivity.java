package com.example.medicalreminder.authentication.login.view;

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
import com.example.medicalreminder.R;
import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.authentication.login.presenter.LoginPresenter;
import com.example.medicalreminder.authentication.login.presenter.LoginPresenterInterface;
import com.example.medicalreminder.databinding.ActivitySignupBinding;
import com.example.medicalreminder.model.authentication.repository.Repository;
import com.example.medicalreminder.remote.firestore.auth.FirebaseClient;
import com.example.medicalreminder.services.service.MyNotification;
import com.example.medicalreminder.services.service.Reply;
import com.example.medicalreminder.services.service.Take;
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

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {

    private ActivitySignupBinding binding;
    private GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN = 1;
    FirebaseAuth auth;
    LoginPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(this,
                Repository.getInstance(this, FirebaseClient.getInstance(this)), this);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perForLogin();

                Log.i("TAG", "onClick: ");
                saveUser(
                        binding.editEmailSignup.getText().toString(),
                        binding.editPasswordSignup.getText().toString());
            }
        });


//        googleSignInClient = GoogleSignIn.getClient(this, gso);
        binding.googleSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signIn();
                startActivity(new Intent(getApplicationContext(), GoogleLoginActivity.class));
            }
        });


    }

    @Override
    public void saveUser(String email, String password) {
        presenter.checkDataLogin(email, password);
        Log.i("TAG", "saveUser: ");
        finish();
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
                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    updateUI(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
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
            Log.i("TAG", "updateUI: " + email);
            Toast.makeText(this, name + email, Toast.LENGTH_SHORT).show();
        }
    }

}
