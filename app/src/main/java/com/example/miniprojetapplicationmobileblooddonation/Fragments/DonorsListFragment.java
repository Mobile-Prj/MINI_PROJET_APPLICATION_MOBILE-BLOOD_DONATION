package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DonorsAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Models.DonorsList;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.ArrayList;
import java.util.List;

public class DonorsListFragment extends Fragment {

    private List<DonorsList> items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*View rootView = inflater.inflate(R.layout.fragment_donors_list, container, false);

         */
        return inflater.inflate(R.layout.fragment_donors_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        DonorsAdapter donorsAdapter = new DonorsAdapter(getContext(),items);
        recyclerView.setAdapter(donorsAdapter);
        donorsAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        items = new ArrayList<DonorsList>();
        items.add(new DonorsList("A+ Donor","Full Name : abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("AB+ Donor","Full Name : Sara Chakir","Phone : +212650065744","City : Rabat",R.drawable.ic_profile));
        items.add(new DonorsList("A- Donor","Full Name : Yassmin Kardad","Phone : +212650065744","City : Safi",R.drawable.ic_profile));
        items.add(new DonorsList("A+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("B+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));
        items.add(new DonorsList("O+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Rabat",R.drawable.ic_profile));
        items.add(new DonorsList("AB- Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Safi",R.drawable.ic_profile));
        items.add(new DonorsList("A+ Donor","Full Name : el abbasy soukaina","Phone : +212650065744","City : Marrakech",R.drawable.ic_profile));

    }
}
