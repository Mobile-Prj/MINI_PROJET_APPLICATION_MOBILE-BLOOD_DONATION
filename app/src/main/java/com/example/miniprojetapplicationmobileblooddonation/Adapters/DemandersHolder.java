package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.R;

public class DemandersHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView Fname, Address,Phone, DateTime, BloodCateg;
    public DemandersHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ImageView);
        Fname = itemView.findViewById(R.id.Fname);
        Address = itemView.findViewById(R.id.Address);
        Phone = itemView.findViewById(R.id.Phone);
        DateTime = itemView.findViewById(R.id.DateTime);
        BloodCateg = itemView.findViewById(R.id.BloodCat);
    }
}
