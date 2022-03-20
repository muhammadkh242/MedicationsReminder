package com.example.medicalreminder.model.editmedication;

import com.example.medicalreminder.model.addmedication.Medication;

import java.util.List;

public interface RepoEditInterface {

    public void deleteDrugFirestore(List<String> days , Medication medication );
    public List<String> getDrugsDaysRealtime(String name);
    public void deleteDrugRealtime(String date);
}
