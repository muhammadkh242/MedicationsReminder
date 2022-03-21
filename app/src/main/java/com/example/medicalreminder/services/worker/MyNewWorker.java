package com.example.medicalreminder.services.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.local.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoAdd;
import com.example.medicalreminder.model.meddialog.RepoDialog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

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
        CalculationMedication.increament = 1;
        String newDate = CalculationMedication.incrementCalenderDate(
                (CalculationMedication.formatCalenderDate(formatter.format(date))));
        Log.i("TAG", "doWork: " + newDate);
        LiveData<MedicationList> list = RepoAdd.getInstance
                (getApplicationContext(),
                        ConcreteLocalSource.getInstance(getApplicationContext())).getDrugsOffline(newDate);
        if(list.getValue() != null) {
            Log.i("TAG", "doWork: if");
           Observable.fromIterable(Arrays.asList(list))
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<LiveData<MedicationList>>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {

                       }

                       @Override
                       public void onNext(@NonNull LiveData<MedicationList> listLiveData) {
                           if(listLiveData != null){
                               Log.i("TAG", "onNext: " + listLiveData.getValue().getList().size());
                               RepoDialog.getInstance(getApplicationContext()).calcWork(list);
                           }

                       }

                       @Override
                       public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                       }

                       @Override
                       public void onComplete() {

                       }
                   });
       }else{
           Log.i("TAG", "doWork: else" );
       }
        return Result.success();
    }

}
