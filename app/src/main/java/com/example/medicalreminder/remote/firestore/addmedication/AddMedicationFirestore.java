package com.example.medicalreminder.remote.firestore.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.constant.Constant;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTime;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTimeInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddMedicationFirestore implements AddMedicationFirestoreInterface {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance() ;
    CollectionReference collectionReference = firebaseFirestore.collection("Drug");
    List<MedicationList> list = new ArrayList<>();
    MutableLiveData<List<MedicationList>> medication = new MutableLiveData<>();
    List<String> days = new ArrayList<>();
    AddMedicationRealTimeInterface realTimeInterface = new AddMedicationRealTime();
    String userId ;
    public  AddMedicationFirestore(){
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);
    }

    @Override
    public void insertDrugsFirestore(MedicationList med) {
        collectionReference
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(med.getDate())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            collectionReference.
                                    document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection(med.getDate())
                                    .document(med.getList().get(0).getName())
                                    .set(med);
                        } else {
                            Log.i("TAG", "onSuccess: "+FirebaseAuth.getInstance().getUid());
                            collectionReference
                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection(med.getDate())
                                    .document(med.getList().get(0).getName())
                                    .set(med);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    @Override
    public MutableLiveData<List<MedicationList>> getDrugsFireStore(String date) {
        collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            MedicationList medicationList = document.toObject(MedicationList.class);
                            list.add(medicationList);
                        }
                        medication.setValue(list);
                        list = new ArrayList<>();
                    }
                });
        return medication;
    }

    @Override
    public void deleteDrugFirestore(List<String> days,Medication medication) {
        Log.i("TAG", "day: "+ days.size());
        for (int i = 0; i < days.size(); i++) {
            collectionReference.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection(days.get(i))
                    .document(medication.getName())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    });
        }

        for (int i = 0; i < medication.getDays().size(); i++) {
            MedicationList medicationList = new MedicationList();
            List<MedicationDose> medicationDoses = new ArrayList<>();
            for (int j = 0; j < medication.getHours().size(); j++) {
                MedicationDose med = new MedicationDose();
                med.setName(medication.getName());
                med.setHour(medication.getHours().get(j));
                medicationDoses.add(med);
            }
            medicationList.setDate(medication.getDays().get(i));
            medicationList.setList(medicationDoses);
            insertDrugsFirestore(medicationList);
        }

        Drug drug = new Drug();
        drug.setName(medication.getName());
        drug.setDurationDrug(medication.getDurationDrug());
        drug.setStatusDrug("no");
        drug.setDays(medication.getDays());
        if(medication.getEveryDayOr().equals(Constant.ANSWER_YES)){
            drug.setTimesInDays(medication.getTimesInday());
        }
        else{
            drug.setTimesInWeeks(medication.getTimesInWeeks());
        }
        deleteDrugRealtime(medication.getName());
        realTimeInterface.insertDrugRealTime(drug);
    }

    @Override
    public void getDrugDaysRealtime(Medication medication) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query query = reference
                .orderByChild("name").equalTo(medication.getName());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Drug drug = dataSnapshot.getValue(Drug.class);
                    days = drug.getDays();
                }
                deleteDrugFirestore(days,medication);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void deleteDrugRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            Drug drug = snapshot.getValue(Drug.class);
                            if (drug.getName().equals(name)) {
                                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                        child(snapshot.getKey()).removeValue();
                            }
                        }
                    }
                });
    }
}