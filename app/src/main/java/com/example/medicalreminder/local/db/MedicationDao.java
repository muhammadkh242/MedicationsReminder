package com.example.medicalreminder.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MedicationDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertDrug(MedicationList medList);

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //void insertDrug(MedicationList medList );

    @Query("SELECT * FROM drug WHERE date LIKE :datee")
    Single<List<MedicationList>> getDrugs(String datee);

    @Delete
    Completable deleteDrug(MedicationList medList) ;

    //@Query("SELECT * FROM drug WHERE date LIKE :datee ")
   //List<MedicationList>getDrugs(String datee);

    @Query("DELETE FROM drug WHERE date LIKE :datee")
    void deleteDate(String datee);

}
