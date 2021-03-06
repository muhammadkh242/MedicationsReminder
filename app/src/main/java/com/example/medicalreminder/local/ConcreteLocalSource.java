package com.example.medicalreminder.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.local.dbdrug.DrugDao;
import com.example.medicalreminder.local.dbdrug.DrugDataBase;
import com.example.medicalreminder.local.dbmedication.MedicationDao;
import com.example.medicalreminder.local.dbmedication.MedicationDataBase;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import java.util.ArrayList;
import java.util.List;

public class ConcreteLocalSource implements LocalSource {

    private  Context context;
    private   static ConcreteLocalSource concreteLocalSource;
    MedicationDao medDao;
    DrugDao drugDao;

    private ConcreteLocalSource(Context context){
        this.context = context;
        MedicationDataBase medDB = MedicationDataBase.getInstance(context.getApplicationContext());
        medDao = medDB.medicationDao();

        DrugDataBase drDB = DrugDataBase.getInstance(context.getApplicationContext());
        drugDao = drDB.drugDao();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(concreteLocalSource ==null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return  concreteLocalSource;
    }


    //medication
    @Override
    public void insertMedicationOffline(MedicationList medList ) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MedicationList obj = getMedsObjOffline(medList.getDate());
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
    public LiveData<MedicationList> getMedsOffline(String date) {
        return medDao.getDrugs(date);
    }
    @Override
    public MedicationList getMedsObjOffline(String date) {
        return medDao.getDrugsObj(date);
    }
    @Override
    public void deleteDateOffline(String date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                medDao.deleteDate(date);
            }
        });
    }

    //drug
    @Override
    public void insertDrugOffline(Drug drug) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                drugDao.insertDrugDetails(drug);
            }
        }).start();
    }
    @Override
    public LiveData<Drug> getDrugDetailsOffline(String name) {
        return drugDao.getDrugDetails(name);
    }
    @Override
    public LiveData<List<Drug>> getAllDrugDetailsOffline() {
        return drugDao.getAllDrugDetails();
    }


}