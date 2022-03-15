package com.example.medicalreminder.local.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

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
    public void addDrug(MedicationList medList ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
             medDao.insertDrug(medList);
            }
        }).start();
    }

    @Override
    public void deleteDrug(MedicationList medList ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                medDao.deleteDrug(medList);
            }
        }).start();
    }

    @Override
    public LiveData<List<MedicationList >> getDrugs(String date) {
        return medDao.getDrugs();  }
}