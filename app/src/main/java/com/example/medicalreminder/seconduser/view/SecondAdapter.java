package com.example.medicalreminder.seconduser.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;

import java.util.ArrayList;
import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder>{
    private List<UserMed> meds = new ArrayList<>();
    private final Context context;

    public SecondAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.second_user_med_row, parent, false);
        SecondAdapter.ViewHolder viewHolder = new SecondAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMedTxt().setText(meds.get(position).getName());
        holder.getFormTxt().setText(meds.get(position).getForm());
        holder.getImageView().setImageResource(R.drawable.pills);
    }

    public void setData(List<UserMed> meds){
        this.meds = meds;
        Log.i("TAG", "setData: medlist size : " + this.meds.size());

        notifyDataSetChanged();
        Log.i("TAG", "setData: " + this.meds.size());
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        TextView medTxt;
        TextView formTxt;
        ImageView imageView;
        View medRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medRow = itemView;
            medTxt = medRow.findViewById(R.id.medName);
            formTxt = medRow.findViewById(R.id.formTxt);
            imageView = medRow.findViewById(R.id.image);

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
    }
}
