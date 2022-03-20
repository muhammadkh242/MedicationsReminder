package com.example.medicalreminder.home.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

import com.example.medicalreminder.home.view.HomeFragmentViewInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.RepoInterface;
import com.example.medicalreminder.model.home.RepoHomeInterface;
import com.example.medicalreminder.model.meddialog.RepoDialog;
import com.example.medicalreminder.model.meddialog.RepoDialogInterface;
import com.example.medicalreminder.services.MyNewWorker;
import com.example.medicalreminder.services.MyWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
            view.showMedOnline(repo.getDurgs(date));
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
