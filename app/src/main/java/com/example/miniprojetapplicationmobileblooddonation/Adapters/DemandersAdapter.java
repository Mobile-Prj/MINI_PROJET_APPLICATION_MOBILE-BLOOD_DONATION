package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.List;

/**
 * Adaptateur des demandeurs
 */
public class DemandersAdapter extends RecyclerView.Adapter<DemandersHolder> {

    Context context;
    List<DemanderItem> items;
    public DemandersAdapter(Context context, List<DemanderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DemandersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DemandersHolder(LayoutInflater.from(context).inflate(R.layout.demander_item_view,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DemandersHolder holder, int position) {
        holder.Address.setText("Address : "+ items.get(position).getAdress());
        holder.BloodCateg.setText("Needs "+items.get(position).getBloodCateg());
        holder.DateTime.setText("Posted On : "+items.get(position).getDateTime());
        holder.Phone.setText("Phone : "+items.get(position).getContact());
        holder.Fname.setText("Full Name : "+items.get(position).getRequestedBy());
        holder.imageView.setImageResource(items.get(position).getImage());

    }

    @Override
    public int getItemCount() {return items.size(); }


}
