package com.example.medicalreminder.model.addmedication;

public class MedicationDose {

    String name;
    String hour ;


    public MedicationDose() {
    }

    public MedicationDose(String name, String hour) {
        this.name = name;
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
