package com.example.medicalreminder.local.dbdrug;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.medicalreminder.model.addmedication.DataBaseConvert;
import com.example.medicalreminder.model.addmedication.Drug;

@Database(entities = Drug.class, version =1, exportSchema = false)
@TypeConverters(DataBaseConvert.class)

    public abstract class DrugDataBase extends RoomDatabase {

        private static DrugDataBase instance;
        public abstract DrugDao drugDao();

        public static synchronized DrugDataBase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        DrugDataBase.class, "drugs")
                        .build();
            }
            return instance;
        }
    }
