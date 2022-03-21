package com.example.medicalreminder.model.medicationsmanaging;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.remote.realtime.RealTime;
import com.example.medicalreminder.remote.realtime.RealTimeInterface;

import java.util.List;

public class MedicationsRepo implements MedicationsRepoInterface{

    private Context context;
    private static MedicationsRepo medicationsRepo = null;
    private ConcreteLocalSource localSource;
    private RealTimeInterface realTimeDBInterface;

    private MedicationsRepo(Context context, ConcreteLocalSource localSource) {
        this.context = context;
        this.localSource = localSource;
        realTimeDBInterface = new RealTime();
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
