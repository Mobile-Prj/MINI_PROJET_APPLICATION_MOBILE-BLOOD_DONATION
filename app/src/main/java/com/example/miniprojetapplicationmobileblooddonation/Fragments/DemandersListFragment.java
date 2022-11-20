package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DemandersAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DemandersListFragment extends Fragment {

    FloatingActionButton mAddFab;
    private List<DemanderItem> items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*View rootView = inflater.inflate(R.layout.fragment_donors_list, container, false);

         */
        return inflater.inflate(R.layout.fragment_demanders_list, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.demander_list_recyleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        DemandersAdapter demandersAdapter = new DemandersAdapter(getContext(),items);
        recyclerView.setAdapter(demandersAdapter);
        demandersAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        items= new ArrayList<DemanderItem>();
        items.add( new DemanderItem("+6273890","FULL NAME","HOPITAL WXXXX","06/12/2022 on 6PM","AB+",R.drawable.icon));
        items.add( new DemanderItem("+6273890","FULL NAME","HOPITAL YYYYY","08/10/2022 on 9PM","O+",R.drawable.icon));
        items.add( new DemanderItem("+4989299","FULL NAME","HOPITAL ZZZZ","14/12/2022 on 10AM","A+",R.drawable.icon));

    }

}
