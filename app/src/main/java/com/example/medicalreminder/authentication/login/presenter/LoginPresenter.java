package com.example.medicalreminder.authentication.login.presenter;

  import android.content.Context;
  import android.content.Intent;
  import android.util.Log;
import android.widget.Toast;

  import com.example.medicalreminder.HomeActivity;
  import com.example.medicalreminder.authentication.login.view.LoginViewInterface;
import com.example.medicalreminder.remote.firestore.auth.FirebaseDelegate;
import com.example.medicalreminder.model.authentication.repository.RepositoryInterface;
import com.example.medicalreminder.model.authentication.User;

public class LoginPresenter implements LoginPresenterInterface, FirebaseDelegate {

    private LoginViewInterface view;
    private RepositoryInterface repo;
    private User user;
    private Context context;

    public LoginPresenter(LoginViewInterface view, RepositoryInterface repo, Context context){
        this.view = view;
        this.repo = repo;
        user  = new User();
        this.context = context;
    }

    @Override
    public void checkDataLogin(String email, String password) {
        user.setEmail(email);
        user.setPassword(password);
        repo.perForLogin(user, this);
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
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
