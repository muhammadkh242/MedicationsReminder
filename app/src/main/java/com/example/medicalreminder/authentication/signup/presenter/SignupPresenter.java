package com.example.medicalreminder.authentication.signup.presenter;

  import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.medicalreminder.authentication.signup.view.SignupViewInterface;
import com.example.medicalreminder.firebase.auth.FirebaseDelegate;
import com.example.medicalreminder.model.RepositoryInterface;
import com.example.medicalreminder.model.User;

public class SignupPresenter implements SignupPresenterInterface, FirebaseDelegate {

    private SignupViewInterface view;
    private RepositoryInterface repo;
    private User user;
    private Context context;

    public SignupPresenter(SignupViewInterface view, RepositoryInterface repo, Context context){
        this.view = view;
        this.repo = repo;
        user  = new User();
        this.context = context;
    }

    @Override
    public void checkDataLogin(String email, String password) {
        user.setEmail(email);
        user.setPassword(password);
        repo.perForLogin(user);
    }

    @Override
    public void onFailureResult(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onFailureResult: " + error);

    }

    @Override
    public void onSuccessResult(String success) {
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onFailureResult: " + success);
    }
}
