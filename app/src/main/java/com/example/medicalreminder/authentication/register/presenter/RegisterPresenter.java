package com.example.medicalreminder.authentication.register.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.medicalreminder.authentication.register.view.RegisterViewInterface;
import com.example.medicalreminder.firebase.auth.FirebaseDelegate;
import com.example.medicalreminder.model.RepositoryInterface;
import com.example.medicalreminder.model.User;

public class RegisterPresenter implements RegisterPresenterInterface, FirebaseDelegate{


    private RegisterViewInterface view;
    private RepositoryInterface repo;
    private User user;
    private Context context;

    public RegisterPresenter(RegisterViewInterface view, RepositoryInterface repo, Context context){
        this.view = view;
        this.repo = repo;
        user = new User();
        this.context = context;
    }


    @Override
    public void checkDataReg(String email, String password) {
        user.setEmail(email);
        user.setPassword(password);
        repo.perForAuth(user);
    }

    @Override
    public void onFailureResult(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessResult(String success) {
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();

    }
}