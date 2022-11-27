package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojetapplicationmobileblooddonation.Adapters.DonorsAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.util.ArrayList;
import java.util.List;

public class DonorsListFragment extends Fragment {

    private List<Donor> items;
    Button btnSearch;
    Spinner location,bloodGroop;
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

        btnSearch= (Button) view.findViewById(R.id.btnSearch);
        location=(Spinner) view.findViewById(R.id.cityList);
        bloodGroop=(Spinner) view.findViewById(R.id.BloodGroupList);
        items=new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);

        DataBaseHelper db = new DataBaseHelper(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        items = db.getDonors();
        recyclerView.setAdapter(new DonorsAdapter(getContext(),items));


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = location.getSelectedItem().toString();
                String cat = bloodGroop.getSelectedItem().toString();
                items = db.getSearchedDonors(loc,cat);
                recyclerView.setAdapter(new DonorsAdapter(getContext(),items));
            }
        });


    }



}
