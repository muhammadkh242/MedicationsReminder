package com.example.medicalreminder.model.editmedication;

import com.example.medicalreminder.model.addmedication.Medication;

import java.util.List;

public interface RepoEditInterface {

    public void getDrugDaysRealtime(Medication medication);
}