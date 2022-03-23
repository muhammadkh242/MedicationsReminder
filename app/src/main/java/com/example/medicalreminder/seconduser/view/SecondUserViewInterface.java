package com.example.medicalreminder.seconduser.view;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface SecondUserViewInterface {
    public void showData(MutableLiveData<List<MedicationList>> medList);
    public void getMeds(String date);
    public void deleteFriend();

    public void takeFriendPill(String name);

    public void take(String name);

//    public void showFriendMeds(MutableLiveData<List<Drug>> meds);
//    public void getFriendMeds();


}
