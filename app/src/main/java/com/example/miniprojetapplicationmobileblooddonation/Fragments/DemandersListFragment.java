package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.miniprojetapplicationmobileblooddonation.Activities.FormActivity;
import com.example.miniprojetapplicationmobileblooddonation.Adapters.DemandersAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DemandersListFragment extends Fragment {

    private FloatingActionButton floatingButton;
    private List<DemanderItem> items;
    TextView title;
    DataBaseHelper db;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demanders_list, container, false);
        swipeRef= view.findViewById(R.id.swipeRefresh);
        floatingButton = view.findViewById(R.id.add_fab);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        db = new DataBaseHelper(getContext());
        items= new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.demander_list_recyleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        displayData();
        swipeRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*val ft: FragmentTransaction = this.fragmentManager!!.beginTransaction()
                ft.detach(this)
                ft.attach(this)
                ft.commit()*/
            }
        });

        /*RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.demander_list_recyleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        DemandersAdapter demandersAdapter = new DemandersAdapter(getContext(),items);
        recyclerView.setAdapter(demandersAdapter);
        demandersAdapter.notifyDataSetChanged();*/
    }
    private void displayData(){
        Cursor c = db.getRequesters();
        if(c.getCount()==0){
            Toast.makeText(getContext(),"NO requesters !",Toast.LENGTH_LONG).show();
            return;
        }


        else{
            while (c.moveToNext()){
                items.add( new DemanderItem(c.getString(2),c.getString(1),c.getString(4),c.getString(3),c.getString(5),R.drawable.icon));

            }
            recyclerView.setAdapter(new DemandersAdapter(getContext(),items));

        }
    }

    private void dataInitialize() {

        items= new ArrayList<DemanderItem>();
        items.add( new DemanderItem("+6273890","FULL NAME","HOPITAL WXXXX","06/12/2022 on 6PM","AB+",R.drawable.icon));
        items.add( new DemanderItem("+6273890","FULL NAME","HOPITAL YYYYY","08/10/2022 on 9PM","O+",R.drawable.icon));
        items.add( new DemanderItem("+4989299","FULL NAME","HOPITAL ZZZZ","14/12/2022 on 10AM","A+",R.drawable.icon));

    }

}
