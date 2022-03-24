package com.example.medicalreminder.medicationsmanaging.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.addmedication.Drug;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Drug> meds = new ArrayList<>();
    private final Context context;
    private OnMedClickListener listener;
    public RecyclerAdapter(Context context, OnMedClickListener listener) {
        this.listener = listener;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.second_user_med_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getMedTxt().setText(meds.get(position).getName());
        holder.getFormTxt().setText(meds.get(position).getForm());
        holder.getPillNum().setText(String.valueOf(meds.get(position).getTotalPills()) + " Pill(s) left");
        if(meds.get(position).getForm() != null){
            if(meds.get(position).getForm().equals("pill")){
                holder.getImageView().setImageResource(R.drawable.drug);

            }
            else{
                holder.getImageView().setImageResource(R.drawable.injection);
            }

        }
        else{
            holder.getImageView().setImageResource(R.drawable.drug);

        }

        holder.getMedRow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(meds.get(position));
            }
        });
    }

    public void setData(List<Drug> meds){
        this.meds = meds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }

    //________________________VIEW HOLDER______________________________________________


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView medTxt;
        TextView formTxt;
        ImageView imageView;
        TextView pillNum;
        View medRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medRow = itemView;
            medTxt = medRow.findViewById(R.id.medName);
            formTxt = medRow.findViewById(R.id.formTxt);
            imageView = medRow.findViewById(R.id.image);
            pillNum = medRow.findViewById(R.id.pillTxt);

        }

        public TextView getMedTxt() {
            return medTxt;
        }

        public TextView getFormTxt() {
            return formTxt;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getPillNum() {
            return pillNum;
        }

        public View getMedRow() {
            return medRow;
        }
    }
}
