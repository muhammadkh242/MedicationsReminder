package com.example.medicalreminder.addMedication.view.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import com.example.medicalreminder.R;

import java.util.ArrayList;
import java.util.List;

public class AddMedicationAdapter extends RecyclerView.Adapter<AddMedicationAdapter.AddMedicationHolder> {

    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private OnAddMedClickListner listner;

    public AddMedicationAdapter(Context mContext,OnAddMedClickListner listner) {
        this.mContext = mContext;
        this.listner = listner;
    }

    @NonNull
    @Override
    public AddMedicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddMedicationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_add_med, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddMedicationHolder holder, int position) {

        String txt =mList.get(position);
        holder.txt.setText(txt);
        Drawable mDivider = ContextCompat.getDrawable(mContext, R.drawable.shape_line);
        // Create a DividerItemDecoration whose orientation is Horizontal
        DividerItemDecoration hItemDecoration = new DividerItemDecoration(mContext,
                DividerItemDecoration.HORIZONTAL);
        // Set the drawable on it
        hItemDecoration.setDrawable(mDivider);
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClick(txt);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<String> mList) {
        this.mList = mList;
    }

    public String getAddMed(int position) {
        return mList.get(position);
    }

    public class AddMedicationHolder extends RecyclerView.ViewHolder {
        TextView txt ;
        public AddMedicationHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
