package com.example.medicalreminder.local.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.medicalreminder.model.addmedication.MedicationList;



@Dao
public interface MedicationDao {

    @Insert
    void insertDrug(MedicationList medList );

    @Query("SELECT * FROM drug WHERE date LIKE :datee ")
   MedicationList getDrugs(String datee);

    @Query("DELETE FROM drug WHERE date LIKE :datee")
    void deleteDate(String datee);

}
