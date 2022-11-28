package com.example.miniprojetapplicationmobileblooddonation.Activities;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities.MenuActivity;
import com.example.miniprojetapplicationmobileblooddonation.Adapters.DemandersAdapter;
import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DemandersListFragment;
import com.example.miniprojetapplicationmobileblooddonation.Models.DemanderItem;
import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Form Activity pour le formulaire de Soumission de demande de sang
 */
public class FormActivity extends AppCompatActivity {
    TextView title;
    MaskedEditText Contact;
    DataBaseHelper db;
    Spinner Cities_listSpinner;
    Button btnInsert;
    Spinner listSpinner;
    Bundle bd;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String date;
    Dialog dialog;
    private List<DemanderItem> demanders;
    DemanderItem req;
    DemandersListFragment f;
    String user_email,Fname;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = new DataBaseHelper(this);
        btnInsert=findViewById(R.id.postbtn);
        title=findViewById(R.id.title);
        Cities_listSpinner=findViewById(R.id.Location);
        Contact=findViewById(R.id.Contact);
        listSpinner=findViewById(R.id.SpinnerBlood);
        dialog = new Dialog(this);
        title.setText("New Request");
        f = new DemandersListFragment();
        demanders = new ArrayList<>();
        /*
        récupération des données de l'intent
         */
        bd=getIntent().getExtras();
        if(bd!=null){
            user_email=bd.getString("user_email");

        }
        /*
        get the user name
         */
        Fname = db.getUserName(user_email);

        btnInsert.setOnClickListener(view -> {
            String LocationTXT= Cities_listSpinner.getSelectedItem().toString();
            String BloodCateg = listSpinner.getSelectedItem().toString();
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.format(calendar.getTime());
            String ContactMasked = String.valueOf(Contact.getText());
            if(ContactMasked.length()!=17)
                Toast.makeText(FormActivity.this ,"The Contact field should follow the format number",Toast.LENGTH_SHORT).show();
            else if(BloodCateg.equals("Group"))
                Toast.makeText(FormActivity.this ,"Please choose a blood group ",Toast.LENGTH_SHORT).show();
            else if(LocationTXT.equals("City"))
                Toast.makeText(FormActivity.this ,"Please choose a City ",Toast.LENGTH_SHORT).show();
            else {
                boolean CheckInsertedData = db.insertRequester(Fname,ContactMasked,date,LocationTXT,BloodCateg);
                req = new DemanderItem(Fname,ContactMasked,date,LocationTXT,BloodCateg,R.drawable.icon);
                if(CheckInsertedData){
                    openSuccessDialog();
                }
                else
                    openFailDialog();
            }
        });
    }

    private void openFailDialog() {
        dialog.setContentView(R.layout.dialog_error_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnOk = dialog.findViewById(R.id.btn_Error);
        btnOk.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    private void openSuccessDialog() {
        dialog.setContentView(R.layout.dialog_success_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnOk = dialog.findViewById(R.id.btn_Success);

        btnOk.setOnClickListener(view -> {
            dialog.dismiss();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

        dialog.show();
    }
}