package com.example.medicalreminder.model.addmedication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@TypeConverters(DataBaseConvert.class)
@Entity(tableName = "drug")
public class MedicationList {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(defaultValue = "date")
    String date;
    @TypeConverters(DataBaseConvert.class)
    @ColumnInfo(defaultValue = "list")
    List<MedicationDose> list;

    public MedicationList() {
    }

    public MedicationList(String date , List<MedicationDose> medDose) {
        this.date = date;
        this.list = medDose;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @TypeConverters(DataBaseConvert.class)
    public List<MedicationDose> getList() {
        return list;
    }

    @TypeConverters(DataBaseConvert.class)
    public void setList(List<MedicationDose> list) {
        this.list = list;
    }
}
