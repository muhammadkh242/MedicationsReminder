package com.example.medicalreminder.model.addmedication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Entity(tableName = "drug")

public class Drug  implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name;
    private  String form;
    private int totalPills;
    private int refill;
    private String timesInDays;
    private String timesInWeeks;
    private String durationDrug;
    private String statusDrug;
    @TypeConverters(DataBaseConvert.class)
    private List<String> days;


    public Drug() {
    }

    public String getStatusDrug() {
        return statusDrug;
    }

    public void setStatusDrug(String statusDrug) {
        this.statusDrug = statusDrug;
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

    public int getTotalPills() {
        return totalPills;
    }

    public String getTimesInWeeks() {
        return timesInWeeks;
    }

    public void setTimesInWeeks(String timesInWeeks) {
        this.timesInWeeks = timesInWeeks;
    }

    public void setTotalPills(int totalPills) {
        this.totalPills = totalPills;
    }

    public int getRefill() {
        return refill;
    }

    public void setRefill(int refill) {
        this.refill = refill;
    }

    public String getTimesInDays() {
        return timesInDays;
    }

    public void setTimesInDays(String timesInDays) {
        this.timesInDays = timesInDays;
    }

    public String getDurationDrug() {
        return durationDrug;
    }

    public void setDurationDrug(String durationDrug) {
        this.durationDrug = durationDrug;
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



}
