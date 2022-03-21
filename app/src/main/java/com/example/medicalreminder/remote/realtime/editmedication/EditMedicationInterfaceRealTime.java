package com.example.medicalreminder.remote.realtime.editmedication;

import com.example.medicalreminder.model.addmedication.Drug;

import java.util.List;

public interface EditMedicationInterfaceRealTime {


    List<String> getDrugDaysRealtime(String name);

    void deleteDrugRealtime(String date);

}
