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
        public  List<MedicationDose> stringToObjectList(String data) {
            if (data == null) {
                return Collections.emptyList();
            }

            Type listType = new TypeToken<List<MedicationDose>>() {
            }.getType();

            return gson.fromJson(data, listType);
        }

        @androidx.room.TypeConverter
        public  String someObjectListToString(List<MedicationDose> data) {
            if (data == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicationDose>>() {
            }.getType();
            String json = gson.toJson(data, type);
            return json;
        }


/*    @TypeConverter // note this annotation
        public String fromMedicationDoseList(List<MedicationDose> medDose) {
            if (medDose == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicationDose>>() {
            }.getType();
            String json = gson.toJson(medDose, type);
            return json;
        }

        @TypeConverter // note this annotation
        public List<MedicationDose> toStringList(String medDose) {
            if (medDose == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<MedicationDose>>() {
            }.getType();
            List<MedicationDose> hours = gson.fromJson(medDose, type);
            return hours;
        }*/
    }
