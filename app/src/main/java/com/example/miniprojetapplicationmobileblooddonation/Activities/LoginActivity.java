package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.miniprojetapplicationmobileblooddonation.R;

public class LoginActivity extends AppCompatActivity {

    Typeface myFont;
    TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appTitle = findViewById(R.id.app_title);
        myFont = Typeface.createFromAsset(getAssets(), "fonts/LavishlyYours-Regular.ttf");
        appTitle.setTypeface(myFont);

    }

    public void goToSignUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void login(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}