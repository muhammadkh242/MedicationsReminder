package com.example.medicalreminder.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.medicalreminder.model.addmedication.DataBaseConvert;
import com.example.medicalreminder.model.addmedication.MedicationList;

@Database(entities = MedicationList.class, version =1, exportSchema = false)
@TypeConverters(DataBaseConvert.class)

    public abstract class MedicationDataBase extends RoomDatabase {

        private static MedicationDataBase instance;
        public abstract MedicationDao medicationDao();

        public static synchronized MedicationDataBase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        MedicationDataBase.class, "drug")
                        .build();
            }
            return instance;
        }
    }
