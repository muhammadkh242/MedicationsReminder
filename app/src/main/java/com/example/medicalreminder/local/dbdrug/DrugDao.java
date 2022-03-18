package com.example.medicalreminder.local.dbdrug;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


@Dao
public interface DrugDao {

    @Insert
    void insertDrugDetails(Drug drug );

    @Query("SELECT * FROM drug WHERE name LIKE :name ")
    LiveData<Drug>getDrugDetails(String name);

    @Query("SELECT * FROM drug ")
    LiveData<List<Drug>>getAllDrugDetails();

}
