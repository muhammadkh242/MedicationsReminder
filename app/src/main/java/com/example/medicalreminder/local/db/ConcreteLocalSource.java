package com.example.medicalreminder.local.db;

import android.content.Context;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import java.util.ArrayList;
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
    public void deleteDate(String date) {
     medDao.deleteDate(date);
    }

    @Override
    public void addDrug(MedicationList medList ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MedicationList list = medDao.getDrugs(medList.getDate());
                if(list!=null){
                    List<MedicationDose> listQuery = list.getList();
                    List<MedicationDose> listUser = medList.getList();
                    List<MedicationDose> listMed= new ArrayList<>();

                    for(int i =0;i<listQuery.size();i++){
                        listMed.add(listQuery.get(i));
                    }
                    for(int i =0;i<listUser.size();i++){
                        listMed.add(listUser.get(i));
                    }
                    medList.setList(listMed);
                    medDao.deleteDate(list.getDate());
                }
                medDao.insertDrug(medList);
            }
        }).start();
    }

    @Override
    public MedicationList getDrugs(String date) {
        return medDao.getDrugs(date);
    }

}