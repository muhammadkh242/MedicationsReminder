package com.example.medicalreminder.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

@Dao
public interface MedicationDao {

     @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDrug(MedicationList medList );

    @Delete
    void deleteDrug(MedicationList medList) ;


    //@Query("SELECT * FROM me WHERE date LIKE :datee LIMIT 1")

    //@Query("SELECT * FROM me WHERE date")
    @Query("SELECT * FROM me WHERE date LIKE '15/3/2022' LIMIT 1")
    LiveData<List<MedicationList>> getDrugs();

}
