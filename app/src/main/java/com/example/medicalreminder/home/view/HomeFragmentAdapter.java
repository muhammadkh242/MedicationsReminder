package com.example.medicalreminder.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.model.addmedication.MedicationDose;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentHolder>{

    private List<MedicationDose> mList = new ArrayList<>();
    private Context mContext;
    private OnClickListenerHomeFragment listener;

    public HomeFragmentAdapter(Context mContext, OnClickListenerHomeFragment listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeFragmentAdapter.HomeFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeFragmentAdapter.HomeFragmentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentAdapter.HomeFragmentHolder holder, int position) {
        MedicationDose myListData = mList.get(position);
        holder.nameTxt.setText(myListData.getName());
        holder.timeTxt.setText(myListData.getHour());
        holder.takeTxt.setText("Take (1) Pill");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(myListData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<MedicationDose> mList) {
        this.mList = mList;
    }

    public class HomeFragmentHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, takeTxt, timeTxt ;
        public HomeFragmentHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name_med_txt);
            takeTxt = itemView.findViewById(R.id.pillsTxt_home);
            timeTxt = itemView.findViewById(R.id.time_txt_home);
        }
    }
}
