package com.example.medicalreminder.model.addmedication;


import androidx.lifecycle.LiveData;
import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface RepoInterface {

    //room
    public void addDrug(MedicationList medDose );
    public void deleteDate(String date );
    public LiveData<MedicationList> getDrugs(String date);
    public LiveData<List<MedicationList>>  getAllDrugs();



    //firestore
    public void sendDrug(MedicationList list);
    public MedicationList getDurgs(MedicationList list);
    //connection Network
    public boolean connection();



}
