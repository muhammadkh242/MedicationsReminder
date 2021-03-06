package com.example.medicalreminder.model.authentication.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.model.authentication.User;
import com.example.medicalreminder.remote.firestore.auth.FirebaseDelegate;
import com.example.medicalreminder.remote.firestore.auth.FirebaseSource;


public class Repository implements RepositoryInterface {

    private static Repository repo = null;
    private FirebaseSource firebaseSource;
    private SharedPrefsInterface prefsInterface;
    private Context context;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private User user;


    public Repository(Context context, FirebaseSource firebaseSource) {
        this.context = context;
        this.firebaseSource = firebaseSource;
        prefsInterface = new SharedPref(context);
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        user = new User();
    }

    public static Repository getInstance(Context context, FirebaseSource firebaseSource) {
        if (repo == null) {
            repo = new Repository(context, firebaseSource);
        }
        return repo;
    }

    @Override
    public void perForLogin(User userLogin, FirebaseDelegate delegate) {
       if(activeNetwork != null) {
           String email = userLogin.getEmail();
           String password = userLogin.getPassword();
           user = prefsInterface.getFromPrefs();
           if (user.getEmail() == null) {
               if (email.isEmpty()) {
                   delegate.onFailureResult("Enter correct email.");
               } else if (password.isEmpty() || password.length() < 6) {
                   delegate.onFailureResult("Enter correct password.");
               } else{
                   Log.i("TAG", "perForLogin: " + userLogin.getEmail());
                   firebaseSource.perForLogin(userLogin, delegate);
                   prefsInterface.savePrefs(userLogin);
               }
               Log.i("TAG", "perForLogin: prefs");
           }else {
               Log.i("TAG", "perForLogin: "+ prefsInterface.getFromPrefs().getEmail());
           }
       }else {
            delegate.onFailureResult("No Internet.");
       }
    }

    @Override
    public void perForAuth(User user, FirebaseDelegate delegate) {
        if(activeNetwork != null){
            String email = user.getEmail();
            String password = user.getPassword();
            if(email.isEmpty()){
                delegate.onFailureResult("Enter correct email.");
            }else if(password.isEmpty() || password.length()<6){
                delegate.onFailureResult("Enter correct password.");
            }else
                firebaseSource.perForAuth(user, delegate);
                prefsInterface.savePrefs(user);

        }else {
            delegate.onFailureResult("No Internet....");
        }
    }

    @Override
    public void signOut() {
        firebaseSource.logout();
    }

}
