package com.example.medicalreminder.firebase.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;

public class Firestore implements FirestoreInterface{

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference  = firebaseFirestore.collection("Drug");
    //RequestUser user = new RequestUser(FirebaseAuth.getInstance().getUid(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), "NULL",false);



    @Override
    public void sendDrugs( MedicationList list) {
        collectionReference.add(list);
       // collectionReference.add(list);
        /*firebaseFirestore.collection("drug")
                .add(list)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", "Error adding document", e);
                    }
                });

        /*collectionReference.add(list)
             .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                 @Override
                 public void onSuccess(DocumentReference documentReference) {
                     Log.i("TAG", "onSuccess: ");
                 }
             }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TAG", "onFailure: ");
            }
        });*/
    }

    @Override
    public MedicationList getDrugs() {

        firebaseFirestore.collection("Drug")
                .whereEqualTo("date","14/03/2022")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                MedicationList c = d.toObject(MedicationList.class);
                                Log.i("TAG", "onSuccess: "+list.size());
                                Log.i("TAG", "onSuccess: "+c.getList().get(0).getName());
                                Log.i("TAG", "onSuccess: "+c.getList().get(0).getHour());
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
        /*DocumentReference reference = firebaseFirestore.collection("Drug")
                .document("7JhOY2SB5hizhLQ2qjdh");

        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    MedicationList l = document.toObject(MedicationList.class);
                    Log.i("TAG", "onComplete: "+l.getDate());
                }
            }
        });

        //collectionReference.whereEqualTo("date","19/3/2022")
//                collectionReference.get()
//        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d("TAG", document.getId() + " => " + document.getData());
//                    }
//                } else {
//                    Log.d("TAG", "Error getting documents: ", task.getException());
//                }
//            }
//        });*/

        return null;
    }
}
