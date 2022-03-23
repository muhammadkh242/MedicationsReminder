package com.example.medicalreminder.seconduser.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.addmedication.MedicationDose;

import java.util.ArrayList;
import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder>{

    private List<MedicationDose> meds = new ArrayList<>();
    private Context context;
    private OnClickListenerSecondUser onClickListenerSecondUser;

    public SecondAdapter(Context context, OnClickListenerSecondUser onClickListenerSecondUser) {
        this.context = context;
        this.onClickListenerSecondUser = onClickListenerSecondUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_adapter, parent, false);
        SecondAdapter.ViewHolder viewHolder = new SecondAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicationDose medicationDose = meds.get(position);
        holder.getNameTxt().setText(meds.get(position).getName());
        holder.getTimeTxt().setText(meds.get(position).getHour());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListenerSecondUser.onClick(medicationDose);
            }
        });
    }

    public void setData(List<MedicationDose> meds){
        this.meds = meds;
        Log.i("TAG", "setData: medlist size : " + this.meds.size());

        Log.i("TAG", "setData: " + this.meds.size());
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView timeTxt;
        TextView pillTxt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name_med_txt);
            timeTxt = itemView.findViewById(R.id.time_txt_home);
            pillTxt = itemView.findViewById(R.id.pillsTxt_home);

        }

        public TextView getNameTxt() {
            return nameTxt;
        }

        public TextView getTimeTxt() {
            return timeTxt;
        }

        public TextView getPillTxt() {
            return pillTxt;
        }
    }
}
