package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;

public class DonorsAdapter extends RecyclerView.Adapter<DonorsHolder> {


    Context context;
    List<Donor> items;

    public DonorsAdapter(Context context, List<Donor> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DonorsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new DonorsHolder(LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  DonorsHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle()+ "  Donor");
        holder.nameView.setText("Full Name : "+items.get(position).getName());
        holder.phoneView.setText("Phone : "+items.get(position).getPhone());
        holder.cityView.setText("City : "+items.get(position).getCity());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
