package com.example.medicalreminder.model.addmedication;


import androidx.lifecycle.LiveData;
import java.util.List;

import io.reactivex.Single;

public interface RepoInterface {



    public void addDrug(MedicationList medDose );
    public void deleteDrug(MedicationList medDose );
    public Single<MedicationList > getDrugs(MedicationList medicationList);
    public void deleteDate(String date);

    //insert in room
    /*public void addDrug(MedicationList medDose );
    //remove from room
    public void deleteDrug(MedicationList medDose );
    //remove from room
    public void deleteDate(String date );
    // get from room
    public List<MedicationList > getDrugs(String date);
    //firestore
    public void sendDrug(MedicationList list);
    //connection Network
    public boolean connection();
    //firestore
    public MedicationList getDurgs(MedicationList list);
    //firestore
    public void createDocument();
*/
}
