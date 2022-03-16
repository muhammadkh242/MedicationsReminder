package com.example.medicalreminder.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


@Dao
public interface MedicationDao {

    @Insert
    void insertDrug(MedicationList medList );

    @Query("SELECT * FROM drug WHERE date LIKE :datee ")
    LiveData<MedicationList>getDrugs(String datee);


    @Query("SELECT DISTINCT * FROM drug ")
    LiveData<List<MedicationList>> getAllDrugs();

    @Query("DELETE FROM drug WHERE date LIKE :datee")
    void deleteDate(String datee);

}
