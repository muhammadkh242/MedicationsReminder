package com.example.medicalreminder.local.dbmedication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicalreminder.model.addmedication.MedicationList;


@Dao
public interface MedicationDao {

    @Insert
    void insertDrug(MedicationList medList );

    @Query("SELECT * FROM medication WHERE date LIKE :datee ")
    LiveData<MedicationList>getDrugs(String datee);

    @Query("SELECT * FROM medication WHERE date LIKE :datee ")
    MedicationList getDrugsObj(String datee);

    @Query("DELETE FROM medication WHERE date LIKE :datee")
    void deleteDate(String datee);



}
