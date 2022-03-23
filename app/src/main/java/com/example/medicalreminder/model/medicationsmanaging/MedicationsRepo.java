package com.example.medicalreminder.model.medicationsmanaging;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.medicalreminder.local.ConcreteLocalSource;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.remote.realtime.medicationsmanaging.MedicationManagingRealTime;

import java.util.List;

public class MedicationsRepo implements MedicationsRepoInterface{

    private Context context;
    private static MedicationsRepo medicationsRepo = null;
    private ConcreteLocalSource localSource;
    private MedicationManagingRealTime realTimeDBInterface;

    private MedicationsRepo(Context context, ConcreteLocalSource localSource) {
        this.context = context;
        this.localSource = localSource;
        realTimeDBInterface = new MedicationManagingRealTime();
    }

    public static MedicationsRepo getMedicationsRepo(Context context, ConcreteLocalSource localSource) {
        if(medicationsRepo == null){
            medicationsRepo = new MedicationsRepo(context, localSource);
        }
        return medicationsRepo;
    }

    //realtime
    @Override
    public MutableLiveData<List<Drug>> getMedsRealtime() {
        return realTimeDBInterface.getNamesDrugsRealTime();
    }

    //room
    @Override
    public LiveData<List<Drug>> getMedsOffline() {
        return localSource.getAllDrugDetailsOffline();
    }
}
