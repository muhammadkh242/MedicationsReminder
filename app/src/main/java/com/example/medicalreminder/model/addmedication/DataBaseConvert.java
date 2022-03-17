package com.example.medicalreminder.model.addmedication;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DataBaseConvert implements Serializable {

        static Gson gson = new Gson();
        @androidx.room.TypeConverter
        public  List<MedicationDose> stringMedToObjectList(String data) {
            if (data == null) {
                return Collections.emptyList();
            }

            Type listType = new TypeToken<List<MedicationDose>>() {
            }.getType();

            return gson.fromJson(data, listType);
        }

        @androidx.room.TypeConverter
        public  String objectMedListToString(List<MedicationDose> data) {
            if (data == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicationDose>>() {
            }.getType();
            String json = gson.toJson(data, type);
            return json;
        }

    @androidx.room.TypeConverter
    public  List<MedicationList> stringToObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<MedicationList>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public  String objectListToString(List<MedicationList> data) {
        if (data == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MedicationList>>() {
        }.getType();
        String json = gson.toJson(data, type);
        return json;
    }

       @androidx.room.TypeConverter
       public  MedicationList stringToObject(String data) {
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<MedicationList>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

      @androidx.room.TypeConverter
       public  String objectToString(MedicationList data) {
        if (data == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<MedicationList>() {
        }.getType();
        String json = gson.toJson(data, type);
        return json;
    }

    }
