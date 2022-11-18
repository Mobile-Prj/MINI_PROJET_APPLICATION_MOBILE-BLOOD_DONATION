package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miniprojetapplicationmobileblooddonation.R;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Typeface myFont;
    TextView appTitle;
    Spinner listBloodGroup;
    ArrayAdapter<CharSequence> arrayAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        appTitle = findViewById(R.id.app_title);
        listBloodGroup = findViewById(R.id.listGrpBlood);

        myFont = Typeface.createFromAsset(getAssets(), "fonts/LavishlyYours-Regular.ttf");
        appTitle.setTypeface(myFont);

        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.bloodGroups, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        listBloodGroup.setAdapter(arrayAdapter);
        listBloodGroup.setOnItemSelectedListener(this);
    }

    public void goToLogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}