package com.example.medicalreminder.model.addmedication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Entity(tableName = "dru")

public class Drug {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name;
    private  String form;
    @TypeConverters(DataBaseConvert.class)
    private List<String> hours;
    @TypeConverters(DataBaseConvert.class)
    private List<String> days;


    public Drug() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    @TypeConverter
    public List<String> getDays() {
        return days;
    }

    @TypeConverter
    public void setDays(List<String> days) {
        this.days = days;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @TypeConverter
    public List<String> getHours() {
        return hours;
    }

    @TypeConverter
    public void setHours(List<String> hours) {
        this.hours = hours;
    }

}
