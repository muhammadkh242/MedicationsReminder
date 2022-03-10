package com.example.medicalreminder.medicationsmanaging.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.Med;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Med> meds = new ArrayList<>();
    private final Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.medication_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMedTxt().setText(meds.get(position).getName());
        holder.getStrengthTxt().setText(String.valueOf(meds.get(position).getStrength()));
        holder.getPillsTxt().setText(meds.get(position).getPill());
        holder.getImageView().setImageResource(meds.get(position).getThumbnail());

    }

    public void setData(List<Med> meds){
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
        TextView strengthTxt;
        TextView pillsTxt;
        ImageView imageView;
        View medRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medRow = itemView;
            medTxt = medRow.findViewById(R.id.medTxt);
            strengthTxt = medRow.findViewById(R.id.strengthTxt);
            pillsTxt = medRow.findViewById(R.id.pillsTxt);
            imageView = medRow.findViewById(R.id.imageView);
            imageView.setClipToOutline(true);

        }

        public TextView getMedTxt() {
            return medTxt;
        }

        public TextView getStrengthTxt() {
            return strengthTxt;
        }

        public TextView getPillsTxt() {
            return pillsTxt;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}