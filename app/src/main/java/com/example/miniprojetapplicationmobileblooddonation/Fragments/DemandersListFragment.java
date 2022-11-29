package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
    DataBaseHelper db;
    RecyclerView recyclerView;
    String user_email;
    DemandersAdapter adapter;
    byte[] img_byte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demanders_list, container, false);
        floatingButton = view.findViewById(R.id.add_fab);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get the email from the Menu activity to perform requests
        if(getArguments() != null){
            user_email = getArguments().getString("user_email");
        }
        db = new DataBaseHelper(getContext());
        items= new ArrayList<>();
        recyclerView = view.findViewById(R.id.demander_list_recyleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new DemandersAdapter(getContext(),items);
        displayData();
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormActivity.class);
                intent.putExtra("user_email",user_email);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        displayData();
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }


    private void displayData(){
        Cursor c = db.getRequesters();
        items.clear();
        if(c.getCount()==0){
            Toast.makeText(getContext(),"NO requesters !",Toast.LENGTH_LONG).show();
            return;
        }

        else if(c.getCount() > 0)
        {
            while (c.moveToNext()){
                img_byte= c.getBlob(6);
                items.add( new DemanderItem(c.getString(2),c.getString(1),c.getString(4),c.getString(3),c.getString(5),img_byte));

            }
            recyclerView.setAdapter(adapter);

        }
    }


}
