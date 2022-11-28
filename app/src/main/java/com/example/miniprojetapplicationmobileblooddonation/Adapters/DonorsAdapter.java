package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import android.app.Dialog;
import android.app.LauncherActivity;
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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;

/**
 * Adaptateur des donneurs
 */
public class DonorsAdapter extends RecyclerView.Adapter<DonorsAdapter.DonorsHolder> {
    Context context;
    List<Donor> items;


    public DonorsAdapter(Context context, List<Donor> items) {
        this.context = context;
        this.items = items;
    }

    public List<Donor> getItems() {
        return items;
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
    public class DonorsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Dialog dialog = new Dialog(context);

        ImageView imageView;
        TextView titleView,nameView,phoneView,cityView;

        public DonorsHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageview);
            titleView = itemView.findViewById(R.id.title);
            nameView = itemView.findViewById(R.id.name);
            phoneView = itemView.findViewById(R.id.phone);
            cityView = itemView.findViewById(R.id.city);
        }

        @Override
        public void onClick(View view) {
            openPopUpDialog();

        }
        private void openPopUpDialog() {
            dialog.setContentView(R.layout.choice_popup);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ImageView imgClose= (ImageView) dialog.findViewById(R.id.imageViewClose);
            Button call_btn= dialog.findViewById(R.id.btn_call);
            Button wtp_btn= dialog.findViewById(R.id.btn_wtp);

            imgClose.setOnClickListener(view -> dialog.dismiss());
            call_btn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Donor item = items.get(position);
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+item.getPhone()));
                context.startActivity(i);
                dialog.dismiss();

            });
            wtp_btn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Donor item = items.get(position);
                Uri uri = Uri.parse("smsto:"+item.getPhone());
                Intent i = new Intent(Intent.ACTION_SENDTO,uri);
                i.setPackage("com.whatsapp");
                context.startActivity(i);
                dialog.dismiss();

            });

            dialog.show();
        }

    }
}

