package com.example.medicalreminder.home.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.home.view.HomeFragmentViewInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoInterface;
import com.example.medicalreminder.model.home.RepoHomeInterface;
import com.example.medicalreminder.model.meddialog.RepoDialogInterface;


public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {

    private HomeFragmentViewInterface view;
    private RepoInterface repo;
    private Context context;
    private RepoDialogInterface repoDialog;
    private RepoHomeInterface repoHomeInterface;

    public HomeFragmentPresenter(Context context, HomeFragmentViewInterface view,
                                 RepoInterface repo, RepoDialogInterface repoDialog, RepoHomeInterface repoHomeInterface) {
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
            LiveData<MedicationList> list = repo.getDrugs(date);
            repoDialog.calcWork(list);
        } else {
            LiveData<MedicationList> list = repo.getDrugs(date);
            view.showMed(list);
            repoDialog.calcWork(list);
            Log.i("TAG", "getMedHome: offline");
        }

    }

    @Override
    public void getDrugRealTime(String name) {
        Log.i("TAG", "getDrugRealTime: "+ name);
        repoHomeInterface.getDrugRealTime(name);
        updateDrugRealTime(repoHomeInterface.getDrugRealTime(name));
    }

    @Override
    public void updateDrugRealTime(Drug drug) {
        Log.i("TAG", "updateDrugRealTime1111111111: " + drug.getTotalPills());
        drug.setTotalPills(drug.getTotalPills() - 1);
        Log.i("TAG", "updateDrugRealTime: " + drug.getTotalPills());
        repoHomeInterface.updateDrugRealTime(drug);
    }


}
