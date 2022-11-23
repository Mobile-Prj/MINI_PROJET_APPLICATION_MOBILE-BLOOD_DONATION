package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Fragments.DemandersListFragment;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {
    TextView title,Location;
    MaskedEditText Contact;
    DataBaseHelper db;
    Button btnInsert;
    Spinner listSpinner;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String date;
    RecyclerView recyclerView;
    Dialog dialog;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        db = new DataBaseHelper(this);
        btnInsert=findViewById(R.id.postbtn);
        title=findViewById(R.id.title);
        Location=findViewById(R.id.Location);
        Contact=findViewById(R.id.Contact);
        listSpinner=findViewById(R.id.SpinnerBlood);
        recyclerView = findViewById(R.id.demander_list_recyleview);
        dialog = new Dialog(this);
        title.setText("New Request");
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {
                //String ContactTXT= Contact.getText().toString();
                String LocationTXT= Location.getText().toString();
                String BloodCateg = listSpinner.getSelectedItem().toString();
                calendar = Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date = simpleDateFormat.format(calendar.getTime());
                String ContactMasked = String.valueOf(Contact.getText());
                if(ContactMasked.length()!=17)
                    Toast.makeText(FormActivity.this ,"The Contact field should follow the format number",Toast.LENGTH_SHORT).show();
                else if(LocationTXT.isEmpty())
                    Toast.makeText(FormActivity.this ,"Please enter your Location",Toast.LENGTH_SHORT).show();
                else if(BloodCateg.equals("Group"))
                    Toast.makeText(FormActivity.this ,"Please choose a blood group ",Toast.LENGTH_SHORT).show();
                else {
                    boolean CheckInsertedData = db.insertRequester("FULL NAME",ContactMasked,date,LocationTXT,BloodCateg);
                    if(CheckInsertedData){
                        /*Toast.makeText(getApplicationContext(),"Your request have been successfully added !",Toast.LENGTH_LONG).show();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);*/
                        openSuccessDialog();

                    }
                    else
                        openFailDialog();
                        //Toast.makeText(getApplicationContext(),"Error! Can't add this request",Toast.LENGTH_LONG).show();

                }


            }
        });

    }
    private void openFailDialog() {
        dialog.setContentView(R.layout.dialog_error_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ImageView imgClose= dialog.findViewById(R.id.imageViewClose);
        Button btnOk = dialog.findViewById(R.id.btn_Error);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    private void openSuccessDialog() {
        dialog.setContentView(R.layout.dialog_success_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ImageView imgClose= dialog.findViewById(R.id.imageViewClose);
        Button btnOk = dialog.findViewById(R.id.btn_Success);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
        dialog.show();
    }




}