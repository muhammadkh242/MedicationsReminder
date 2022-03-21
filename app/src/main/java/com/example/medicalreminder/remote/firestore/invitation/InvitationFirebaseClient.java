package com.example.medicalreminder.remote.firestore.invitation;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.invitation.Invitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InvitationFirebaseClient implements InvitationFirebaseSource{
    private Context context;
    private static InvitationFirebaseClient client = null;

    private InvitationFirebaseClient(Context context) {
        this.context = context;
    }

    public static InvitationFirebaseClient getClient(Context context) {
        if(client == null){
            client = new InvitationFirebaseClient(context);
        }
        return client;
    }

    @Override
    public void accept() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Invitation invitation = task.getResult().toObject(Invitation.class);
                        String mail = invitation.getEmail();
                        reference.document(mail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                Invitation invitation1 = task.getResult().toObject(Invitation.class);
                                invitation1.setReply("yes");
                                reference.document(mail).set(invitation1);
                            }
                        });
                        String id = invitation.getId();
                        Invitation invitation1 = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),id, false,null);
                        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                                .set(invitation1);
                    }
                });

    }

    @Override
    public void deny() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Invitation invitation = task.getResult().toObject(Invitation.class);
                String mail = invitation.getEmail();

                reference.document(mail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Invitation invitation1 = task.getResult().toObject(Invitation.class);
                        invitation1.setReply("no");
                        reference.document(mail).set(invitation1);

                    }
                });
            }
        });
        Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                null, false,null);
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .set(invitation);



    }
}
