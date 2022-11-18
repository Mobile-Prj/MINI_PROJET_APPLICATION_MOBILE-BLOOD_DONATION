package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DonorsAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Models.DonorsList;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.ArrayList;
import java.util.List;

public class DonorListActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<DonorsList> items = new ArrayList<DonorsList>();
        items.add(new DonorsList("A+ Donor","Full Name : abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("AB+ Donor","Full Name : Sara Chakir","Phone : +212650065744","City : Rabat",R.drawable.ic_profile));
        items.add(new DonorsList("A- Donor","Full Name : Yassmin Kardad","Phone : +212650065744","City : Safi",R.drawable.ic_profile));
        items.add(new DonorsList("A+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("B+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("O+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Rabat",R.drawable.ic_profile));
        items.add(new DonorsList("AB- Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Safi",R.drawable.ic_profile));
        items.add(new DonorsList("A+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));






        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DonorsAdapter(getApplicationContext(),items));

    }
}