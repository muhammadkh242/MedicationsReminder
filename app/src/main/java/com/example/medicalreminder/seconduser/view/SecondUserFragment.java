package com.example.medicalreminder.seconduser.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondUserFragment extends Fragment implements SecondUserViewInterface{

    RecyclerView secondRecycler;
    List<UserMed> medList = new ArrayList<>();
    SecondAdapter adapter;
    LinearLayoutManager layoutManager;
    Handler handler;

    SecondUserPresenterInterface presenter;
    public SecondUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_second_user, container, false);
        secondRecycler = view.findViewById(R.id.recycler_second);
        secondRecycler.setHasFixedSize(true);

        adapter = new SecondAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        secondRecycler.setLayoutManager(layoutManager);

        secondRecycler.setAdapter(adapter);

        getMeds();

        return view;
    }

    //static data to test
//    private List<Med> initData(){
//        medList.add(new Med("one",30,"3 pills left", R.drawable.one));
//        medList.add(new Med("two",40,"4 pills left", R.drawable.two));
//        medList.add(new Med("three",50,"5 pills left", R.drawable.three));
//        medList.add(new Med("four",60,"6 pills left", R.drawable.four));
//        medList.add(new Med("five",70,"7 pills left", R.drawable.five));
//        medList.add(new Med("six",80,"8 pills left", R.drawable.six));
//        return medList;
//    }

    @Override
    public void showData(List<Med> meds) {

    }

    //test to get data from firebase
    public void getMeds(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        Query query = db.child(FirebaseAuth.getInstance().getUid()).orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserMed userMed = dataSnapshot.getValue(UserMed.class);
                    Log.i("TAG", "form: " + userMed.getForm());
                    Log.i("TAG", "name: " + userMed.getName());
                    Log.i("TAG", "___________________________");

                    medList.add(userMed);
//                    Log.i("TAG", "onDataChange: " + medList.size());

                }
                Log.i("TAG", "onDataChange: list size : " + medList.size());
                adapter.setData(medList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}