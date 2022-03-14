package com.example.medicalreminder.model.addmedication;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

import java.util.List;

public class DataBaseConvert implements Serializable {


        @TypeConverter // note this annotation
        public String fromStringList(List<MedicationDose> hour) {
            if (hour == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicationDose>>() {
            }.getType();
            String json = gson.toJson(hour, type);
            return json;
        }

        @TypeConverter // note this annotation
        public List<MedicationDose> toStringList(String hour) {
            if (hour == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<MedicationDose> hours = gson.fromJson(hour, type);
            return hours;
        }


    }
