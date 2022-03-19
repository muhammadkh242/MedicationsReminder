package com.example.medicalreminder.seconduser.presenter;

import com.example.medicalreminder.model.seconduser.SecondUserRepoInterface;
import com.example.medicalreminder.seconduser.view.SecondUserViewInterface;

public class SecondUserPresenter implements SecondUserPresenterInterface{

    SecondUserViewInterface viewInterface;
    SecondUserRepoInterface repoInterface;


    public SecondUserPresenter(SecondUserViewInterface viewInterface, SecondUserRepoInterface repoInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void getMeds(String date) {
        viewInterface.showData(repoInterface.getMeds(date));

    }
}
