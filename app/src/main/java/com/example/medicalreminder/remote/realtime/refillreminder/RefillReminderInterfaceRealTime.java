package com.example.medicalreminder.remote.realtime.refillreminder;

import com.example.medicalreminder.model.addmedication.Drug;

public interface RefillReminderInterfaceRealTime {


    Drug getDrugRealtime(String name);

    void updateDrugRealTime(Drug drug);
}
