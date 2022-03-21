package com.example.medicalreminder.firebase.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.Invitation;
import com.example.medicalreminder.model.authentication.User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseClient implements FirebaseSource {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private static FirebaseClient client;
    private static GoogleSignInClient googleSignInClient;
    private Context context;
    private ProgressDialog progressDialog;


    public FirebaseClient(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    public static FirebaseClient getInstance(Context context){
        if(client == null){
            client = new FirebaseClient(context);
        }
        return client;
    }

    @Override
    public void perForAuth(User user, FirebaseDelegate delegate) {
        String email = user.getEmail();
        String password = user.getPassword();

        progressDialog.setMessage("Wait for Register.");
        progressDialog.setTitle("Register.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Log.i("TAG", "onComplete: success");
                    delegate.onSuccessResult("success");


                    Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                            null, false, null);
                    CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
                    reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(invitation);

                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
                    db.child(FirebaseAuth.getInstance().getUid()).child("attached").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                } else {
                    progressDialog.dismiss();
                    Log.i("TAG", "onComplete: fail");
                    delegate.onFailureResult("fail");
                }
            }
        });
    }


    @Override
    public void perForLogin(User user, FirebaseDelegate delegate) {
        String email = user.getEmail();
        String password = user.getPassword();

        progressDialog.setMessage("Wait for Login.");
        progressDialog.setTitle("Login.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i("TAG", "onComplete: success");
                    delegate.onSuccessResult("success");
                } else {
                    Log.i("TAG", "onComplete:  fail");
                    delegate.onFailureResult("fail");
                }
            }
        });
    }

    @Override
    public void logout() {
        auth.signOut();
    }
}
