package com.example.medicalreminder.model.medicationsmanaging;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.remote.firebase.medicationsmanaging.MedicationsFirebaseClient;
import com.example.medicalreminder.remote.firebase.medicationsmanaging.MedicationsFirebaseInterface;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.remote.realtimedb.RealTimeDB;
import com.example.medicalreminder.remote.realtimedb.RealTimeDBInterface;

import java.util.List;

public class MedicationsRepo implements MedicationsRepoInterface{

    private MedicationsFirebaseInterface firebaseInterface;
    private Context context;
    private static MedicationsRepo medicationsRepo = null;
    private ConcreteLocalSource localSource;
    private RealTimeDBInterface realTimeDBInterface;

    private MedicationsRepo(Context context, ConcreteLocalSource localSource) {
        firebaseInterface = new MedicationsFirebaseClient();
        this.context = context;
        this.localSource = localSource;
        realTimeDBInterface = new RealTimeDB();
    }

    public static MedicationsRepo getMedicationsRepo(Context context, ConcreteLocalSource localSource) {
        if(medicationsRepo == null){
            medicationsRepo = new MedicationsRepo(context, localSource);
        }
        return medicationsRepo;
    }

    @Override
    public MutableLiveData<List<Drug>> getMeds() {
        return realTimeDBInterface.getMedNamesRealTime();
    }

    @Override
    public LiveData<List<Drug>> getAllMeds() {
        return localSource.getAllDrugDetails();
    }
}
