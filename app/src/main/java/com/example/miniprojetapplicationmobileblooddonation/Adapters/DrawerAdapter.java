package com.example.miniprojetapplicationmobileblooddonation.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities.MenuActivity;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.ProfileFragment;
import com.example.miniprojetapplicationmobileblooddonation.Models.DrawerItem;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slidingnav.SlidingRootNav;
import slidingnav.SlidingRootNavBuilder;

/**
 * Adapter pour le Menu de Navigation
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder>{
    private List<DrawerItem> items;
    private Map<Class<? extends DrawerItem>, Integer> viewTypes;
    private SparseArray<DrawerItem> holderFactories;
    public static Boolean check=true;
    //private SlidingRootNav slidingRootNav;


    Context context;

    private OnItemSelectedListener listener;


    public DrawerAdapter(Context context,List<DrawerItem> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();
        this.context = context;
        processViewTypes();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.adapter = this;
        return holder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item : items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

    public void setSelected(int position) {
        if(ProfileFragment.isClicked && position!=0) {
            showDialog(position);

        }
        else {
            check_save_mode(position);
        }


    }

    private void check_save_mode(int position) {

        DrawerItem newChecked = items.get(position);
        if (!newChecked.isSelectable()) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            DrawerItem item = items.get(i);
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newChecked.setChecked(true);
        notifyItemChanged(position);

        if (listener != null) {
            listener.onItemSelected(position);
        }
    }

    public void showDialog(int position){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("You have unsaved Changes !");
        builder1.setCancelable(false);

        builder1.setMessage("If you close this dialog , you will lose all chages you made."+"\n" +
                "Are You sure, you cant to close ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Don't Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        MenuActivity.slidingRootNav.closeMenu();

                    }
                });

        builder1.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProfileFragment.isClicked=false;
                        check=false;
                        check_save_mode(position);



                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DrawerAdapter adapter;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapter.setSelected(getAdapterPosition());
        }


    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }






}
