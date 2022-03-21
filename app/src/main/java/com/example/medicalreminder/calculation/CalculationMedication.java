package com.example.medicalreminder.calculation;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalculationMedication {

    public static int increament;
    public static List<MedicationDose> medDose = new ArrayList<>();
    public static Medication medication = Medication.getInstance();

    public static int getAnswer() {
        int answer = 0;
        if (medication.getEveryDayOr().equals("Yes")) {
            answer = 1;
        } else if (medication.getEveryDayOr().equals("No")) {
            answer = 2;
        }
        return answer;
    }

    public static void calListHour() {
        if (medication.getEveryDayOr().equals("Yes")) {
            increament = 1;
            String timeDay = medication.getTimesInday();
            List<String> hours = new ArrayList<>();
            switch (timeDay) {
                case "Once day":
                    hours.add(medication.getFirstTimeDose());
                    medication.setHours(hours);
                    break;
                case "Twice day":
                    setListHour(2, 12);
                    break;
                case "3 times in day":
                    setListHour(3, 8);
                    break;
                case "4 times in day":
                    setListHour(4, 6);
                    break;
            }
        } else {
            String timeWeek= medication.getTimesInWeeks();
            List<String> hours = new ArrayList<>();
            hours.add(medication.getFirstTimeDose());
            medication.setHours(hours);
            switch (timeWeek) {
                case "Once week":
                    increament = 7;
                    break;
                case "Twice week":
                    increament = 2;
                    break;
                case "3 times in week":
                    increament = 3;
                    break;
                case "4 times in week":
                    increament = 4;
                    break;
            }
        }
    }

    public static void setListHour(int times, int eachHour) {
        List<String> hours = new ArrayList<>();
        String time = medication.getFirstTimeDose();
        hours.add(time);
        for (int i = 1; i < times; i++) {
            time = hours.get(i - 1);
            String[] separated = time.split(":");
            String hour = separated[0];
            String minutes = separated[1];

            String h = String.valueOf(Integer.parseInt(hour) + eachHour);
            if (Integer.parseInt(h) >= 24) {
                h = String.valueOf(Integer.parseInt(h) - 24);
            }
            hours.add(h + ":" + minutes);
        }
        medication.setHours(hours);
    }

    public static void calDuration() {
        String duration = medication.getDurationDrug();
        switch (duration) {
            case "30 days":
                days(30);
                break;
            case "1 week":
                days(7);
                break;
            case "10 days":
                days(10);
                break;
            case "5 days":
                days(5);
                break;
        }
    }

    public static void days(int duration ) {
        String date = medication.getFirstDateDose();
        List<String> days = new ArrayList<>();
        date = formatCalenderDate(date);

        // list for name , hour
        for (int i = 0; i < medication.getHours().size(); i++) {
            MedicationDose med = new MedicationDose();
            med.setName(medication.getName());
            med.setHour(medication.getHours().get(i));
            medDose.add(med);
        }

        // list for date , list (name,hour)
        for (int i = 0; i < duration; i++) {
            days.add(date);
            incrementCalenderDate(date);
        }
        medication.setDays(days);
    }

    public static String formatCalenderDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            date = sdf.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String incrementCalenderDate(String date ) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            c.add(Calendar.DAY_OF_MONTH, increament);
            date = sdf.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    }
