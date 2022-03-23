package com.example.medicalreminder.home.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.home.view.HomeFragmentViewInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoAddInterface;
import com.example.medicalreminder.model.home.RepoHomeInterface;
import com.example.medicalreminder.model.meddialog.RepoDialogInterface;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {

    private HomeFragmentViewInterface view;
    private RepoAddInterface repo;
    private Context context;
    private RepoDialogInterface repoDialog;
    private RepoHomeInterface repoHomeInterface;

    public HomeFragmentPresenter(Context context, HomeFragmentViewInterface view,
                                 RepoAddInterface repo, RepoDialogInterface repoDialog, RepoHomeInterface repoHomeInterface) {
        this.context = context;
        this.view = view;
        this.repo = repo;
        this.repoDialog = repoDialog;
        this.repoHomeInterface = repoHomeInterface;
    }

    @Override
    public void getMedHome(String date) {
        if (repo.connection()) {
            Log.i("TAG", "getMedHome: online");
            view.showMedOnline(repo.getDrugsOnline(date));
            LiveData<MedicationList> list = repo.getDrugsOffline(date);
            repoDialog.calcWork(list);
        } else {
            LiveData<MedicationList> list = repo.getDrugsOffline(date);
            view.showMed(list);
            repoDialog.calcWork(list);
            Log.i("TAG", "getMedHome: offline");
        }

    }

    @Override
    public void getDrugRealTime(String name) {
        Drug d = repoHomeInterface.getDrugRealTime(name);
//        Log.i("TAG", "updateDrugRealTime:get "+ d.getTotalPills());
//        updateDrugRealTime(d);
    }

    @Override
    public void updateDrugRealTime(Drug drug) {
        Log.i("TAG", "updateDrugRealTime:update " + drug.getTotalPills());
        drug.setTotalPills(drug.getTotalPills() - 1);
        repoHomeInterface.updateDrugRealTime(drug);
    }
}
