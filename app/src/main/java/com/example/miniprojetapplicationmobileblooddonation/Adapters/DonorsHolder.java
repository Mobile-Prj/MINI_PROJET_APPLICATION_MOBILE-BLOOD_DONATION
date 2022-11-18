package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.R;

public class DonorsHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleView,nameView,phoneView,cityView;

    public DonorsHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        titleView = itemView.findViewById(R.id.title);
        nameView = itemView.findViewById(R.id.name);
        phoneView = itemView.findViewById(R.id.phone);
        cityView = itemView.findViewById(R.id.city);
    }
}

