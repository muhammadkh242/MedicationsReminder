package com.example.medicalreminder.local.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource{

    private  Context context;
    private   static ConcreteLocalSource concreteLocalSource;
    MedicationDao medDao;

    private ConcreteLocalSource(Context context){
        this.context = context;
        MedicationDataBase medDB = MedicationDataBase.getInstance(context.getApplicationContext());
        medDao = medDB.medicationDao();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(concreteLocalSource ==null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return  concreteLocalSource;
    }

    @Override
    public void addDrug(MedicationList medDose) {
        medDao.insertDrug(medDose)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "success: add");

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("TAG", "onError: add");
                    }
                });

    }

    @Override
    public void deleteDrug(MedicationList medDose) {

        medDao.deleteDrug(medDose)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.i("TAG", "onComplete:delete ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "onComplete:delete ");

                    }
                });
    }

    @Override
    public Single<MedicationList> getDrugs(MedicationList medList) {
        medDao.getDrugs(medList.getDate())
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<MedicationList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onSuccess(List<MedicationList> list) {
                        if(list.size()!=0){
                            for(int i =0 ;i<medList.getList().size();i++){
                                Log.i("TAG", "medListold: "+medList.getList().get(i).getName());
                                Log.i("TAG", "medDateold: "+medList.getDate());
                            }
                            for(int i =0;i<=list.size();i++){
                                List<MedicationDose> dose = list.get(i).getList();
                                Log.i("TAG", "runnnn: "+dose.size());
                                Log.i("TAG", "run: "+dose.get(i).getName());
                                medList.getList().add(dose.get(i));
                            }
                            for(int i =0 ;i<medList.getList().size();i++){
                                Log.i("TAG", "medListnew: "+medList.getList().get(i).getName());
                                Log.i("TAG", "medDatenew: "+medList.getDate());
                            }
                            concreteLocalSource.deleteDrug(list.get(0));
                        }
                        concreteLocalSource.addDrug(medList);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("TAG", "onError: ");
                    }
                });
        return null;
    }

    @Override
    public void deleteDate(String date) {

    }


   /* @Override
    public void addDrug(MedicationList medList ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MedicationList> list = concreteLocalSource.getDrugs(medList.getDate());
                if(list.size()!=0){
                    for(int i =0;i<list.size();i++){
                        List<MedicationDose> dose = list.get(i).getList();
                        Log.i("TAG", "runnnn: "+dose.size());
                        Log.i("TAG", "run: "+dose.get(i).getName());
                        medList.getList().add(dose.get(i));
                    }
                    Log.i("TAG", "onChanged: "+medList.getList().size());
                    concreteLocalSource.deleteDrug(list.get(0));
                }
                medDao.insertDrug(medList);
            }
        }).start();
    }

   /* @Override
    public void deleteDrug(MedicationList medList ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                medDao.deleteDrug(medList);
            }
        }).start();
    }


    @Override
    public List<MedicationList > getDrugs(String datee) {
        return medDao.getDrugs(datee); }

    @Override
    public void deleteDate(String date) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                medDao.deleteDate(date);
            }
        }).start();

    }*/
}