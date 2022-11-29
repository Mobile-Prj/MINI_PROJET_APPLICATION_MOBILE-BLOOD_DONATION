package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.miniprojetapplicationmobileblooddonation.Fragments.ProfileFragment;
import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.util.List;

/**
 * Adapter for the requesters
 */
public class DemandersAdapter extends RecyclerView.Adapter<DemandersHolder> {

    Context context;
    List<DemanderItem> items;
    Bitmap imgProfileBitmap ;
    RoundedDrawable roundedProfileImage;
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
        holder.imageView.setDrawingCacheEnabled(true);
        imgProfileBitmap = holder.imageView.getDrawingCache();
        roundedProfileImage = RoundedDrawable.fromBitmap(imgProfileBitmap);
        holder.Address.setText("City : "+ items.get(position).getAdress());
        holder.BloodCateg.setText("Needs "+items.get(position).getBloodCateg());
        holder.DateTime.setText("Posted On : "+items.get(position).getDateTime());
        holder.Phone.setText("Phone : "+items.get(position).getContact());
        holder.Fname.setText("Full Name : "+items.get(position).getRequestedBy());
        imgProfileBitmap = BitmapFactory.decodeByteArray(items.get(position).getImage(), 0, items.get(position).getImage().length);
        holder.imageView.setImageBitmap(imgProfileBitmap);

    }

    @Override
    public int getItemCount() {return items.size(); }


}
