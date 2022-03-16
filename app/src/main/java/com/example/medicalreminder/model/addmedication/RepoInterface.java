package com.example.medicalreminder.model.addmedication;


import androidx.lifecycle.LiveData;
import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface RepoInterface {

    //insert in room
    public void addDrug(MedicationList medDose );

    //remove from room
    public void deleteDate(String date );

    // get from room
    public MedicationList getDrugs(String date);

    //firestore
    public void sendDrug(MedicationList list);

    //connection Network
    public boolean connection();

    //firestore
    public MedicationList getDurgs(MedicationList list);

}
