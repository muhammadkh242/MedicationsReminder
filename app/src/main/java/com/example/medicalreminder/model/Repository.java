package com.example.medicalreminder.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.example.medicalreminder.firebase.auth.FirebaseDelegate;
import com.example.medicalreminder.firebase.auth.FirebaseSource;
import com.example.medicalreminder.local.SharedPref;
import com.example.medicalreminder.local.SharedPrefsInterface;


public class Repository implements RepositoryInterface {

    private static Repository repo = null;
    private FirebaseDelegate firebaseDelegate;
    private FirebaseSource firebaseSource;
    private SharedPrefsInterface prefsInterface;
    private Context context;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private User user;


    public Repository(Context context, FirebaseSource firebaseSource) {
        this.context = context;
        this.firebaseSource = firebaseSource;
        this.firebaseDelegate = firebaseDelegate;
        prefsInterface = new SharedPref(context);
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        user = new User();
    }

    public static Repository getInstance(Context context,  FirebaseSource firebaseSource) {
        if (repo == null) {
            repo = new Repository(context, firebaseSource);
        }
        return repo;
    }

    @Override
    public void perForLogin(User userLogin) {
       if(activeNetwork != null) {
           user = prefsInterface.getFromPrefs();
           if (user.getEmail() == null) {
               String email = user.getEmail();
               String password = user.getPassword();
               if (email.isEmpty()) {
                   firebaseDelegate.onFailureResult("Enter correct email.");
               } else if (password.isEmpty() || password.length() < 6) {
                   firebaseDelegate.onFailureResult("Enter correct password.");
               } else
                   firebaseSource.perForLogin(user);
               Log.i("TAG", "perForLogin: prefs");
           }else {
               Log.i("TAG", "perForLogin: "+ prefsInterface.getFromPrefs().getEmail());
           }
       }else {
            firebaseDelegate.onFailureResult("No Internet.");
       }
    }

    @Override
    public void perForAuth(User user) {
        if(activeNetwork != null){
            String email = user.getEmail();
            String password = user.getPassword();
            if(email.isEmpty()){
                firebaseDelegate.onFailureResult("Enter correct email.");
            }else if(password.isEmpty() || password.length()<6){
                firebaseDelegate.onFailureResult("Enter correct password.");
            }else
                firebaseSource.perForAuth(user);
                prefsInterface.savePrefs(user);
        }else {
            firebaseDelegate.onFailureResult("No Internet.");
        }
    }

    @Override
    public void signOut() {
        firebaseSource.logout();
    }

}
