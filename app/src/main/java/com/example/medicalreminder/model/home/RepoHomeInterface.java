package com.example.medicalreminder.model.home;

import com.example.medicalreminder.model.addmedication.Drug;

public interface RepoHomeInterface {

    public void updateDrugRealTime(Drug drug);
    public Drug getDrugRealTime(String name);
}
