package com.example.medicalreminder.local.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
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
    public void deleteDate(String date) {
     new Thread(new Runnable() {
         @Override
         public void run() {
            medDao.deleteDate(date);
         }
     });
    }

    @Override
    public LiveData<List<MedicationList>>  getAllDrugs() {
        return medDao.getAllDrugs();
    }

    @Override
    public void addDrug(MedicationList medList ) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                LiveData<MedicationList> list = medDao.getDrugs(medList.getDate());
                MedicationList obj = medDao.getDrugsObj(medList.getDate());
                if(obj != null){
                    List<MedicationDose> listQuery = obj.getList();
                    List<MedicationDose> listUser = medList.getList();
                    List<MedicationDose> listMed= new ArrayList<>();

                    for(int i =0;i<listQuery.size();i++){
                        listMed.add(listQuery.get(i));
                    }
                    for(int i =0;i<listUser.size();i++){
                        listMed.add(listUser.get(i));
                    }
                    medList.setList(listMed);
                    medDao.deleteDate(obj.getDate());
                }
                medDao.insertDrug(medList);
            }
        }).start();
    }

    @Override
    public LiveData<MedicationList> getDrugs(String date) {
        return medDao.getDrugs(date);
    }

    @Override
    public MedicationList getDrugsObj(String date) {
        return medDao.getDrugsObj(date);
    }

}