package com.example.medicalreminder.model.home;

import com.example.medicalreminder.model.addmedication.Drug;

public interface RepoHomeInterface {

    void updateDrugRealTime(Drug drug);

    Drug getDrugRealTime(String name);

}
