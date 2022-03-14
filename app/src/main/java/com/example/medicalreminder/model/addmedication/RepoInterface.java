package com.example.medicalreminder.model.addmedication;


import androidx.lifecycle.LiveData;
import java.util.List;

public interface RepoInterface {

    //insert in room
    public void addDrug(MedicationList medDose );
    //remove from room
    public void deleteDrug(MedicationList medDose );
    // get from room
    public LiveData<List<MedicationList >> getDrugs(String date);
    //firestore
    public void sendDrug(MedicationList list);
    //connection Network
    public boolean connection();
    public MedicationList getDurgs();

}
