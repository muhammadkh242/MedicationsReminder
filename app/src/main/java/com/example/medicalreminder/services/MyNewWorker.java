package com.example.medicalreminder.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.Repo;
import com.example.medicalreminder.model.meddialog.RepoDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyNewWorker extends Worker {
    public MyNewWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("TAG", "doWork: ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String newDate = AddMedicationPresenter.incrementCalenderDate
                (AddMedicationPresenter.formatCalenderDate(formatter.format(date)));
        Log.i("TAG", "doWork: " + newDate);
        LiveData<MedicationList> list = Repo.getInstance
                (getApplicationContext(),
                        ConcreteLocalSource.getInstance(getApplicationContext())).getDrugs(newDate);
        Log.i("TAG", "doWork: " + list.getValue().getDate());
        Log.i("TAG", "doWork: list"  );
        RepoDialog.getInstance(getApplicationContext()).calcWork(list);
        return Result.success();
    }

}
