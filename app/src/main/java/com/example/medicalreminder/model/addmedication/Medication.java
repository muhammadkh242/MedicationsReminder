package com.example.medicalreminder.model.addmedication;


import java.io.Serializable;
import java.util.List;


public class Medication implements Serializable {

    private String name;
    private String form;
    private List<String> hours;
    private List<String> days;
    private String firstTimeDose;
    private String firstDateDose;
    private String endtDateDose;
    private String everyDayOr;
    private String timesInday;
    private String durationDrug;

    public int getTotalPills() {
        return totalPills;
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

    private String timesInWeeks;
    private int totalPills;
    private int refill;
    private static Medication medication;

    private Medication() {

    }

    public String getTimesInWeeks() {
        return timesInWeeks;
    }

    public void setTimesInWeeks(String timesInWeeks) {
        this.timesInWeeks = timesInWeeks;
    }

    public String getFirstTimeDose() {
        return firstTimeDose;
    }

    public void setFirstTimeDose(String firstTimeDose) {
        this.firstTimeDose = firstTimeDose;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public static Medication getInstance() {
        if (medication == null) {
            medication = new Medication();
        }
        return medication;
    }

    public String getFirstDateDose() {
        return firstDateDose;
    }

    public void setFirstDateDose(String firstDateDose) {
        this.firstDateDose = firstDateDose;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }


    public String getDurationDrug() {
        return durationDrug;
    }

    public void setDurationDrug(String durationDrug) {
        this.durationDrug = durationDrug;
    }

    public String getEveryDayOr() {
        return everyDayOr;
    }

    public void setEveryDayOr(String everyDayOr) {
        this.everyDayOr = everyDayOr;
    }

    public String getTimesInday() {
        return timesInday;
    }

    public void setTimesInday(String timesInday) {
        this.timesInday = timesInday;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndtDateDose() {
        return endtDateDose;
    }

    public void setEndtDateDose(String endtDateDose) {
        this.endtDateDose = endtDateDose;
    }
}
