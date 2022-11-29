package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities.MenuActivity;
import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.R;

public class LoginActivity extends AppCompatActivity {
    EditText email , password ,passe;
    Button login ;
    Typeface myFont;
    TextView appTitle;
    DataBaseHelper db;
    Dialog dialog;
    TextView fpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new Dialog(this);
        appTitle = findViewById(R.id.app_title);
        myFont = Typeface.createFromAsset(getAssets(), "fonts/LavishlyYours-Regular.ttf");
        appTitle.setTypeface(myFont);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password_login);
        login=(Button) findViewById(R.id.login_button);
        fpass=(TextView) findViewById(R.id.reset_pwd);
        db=new DataBaseHelper(this);
        //Forgot Password
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView mdp=dialog.findViewById(R.id.password);
                TextView mail=dialog.findViewById(R.id.email);
                Button save= dialog.findViewById(R.id.btn_call);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String emailS=mail.getText().toString();
                        String pass=mdp.getText().toString();
                        if(emailS.equals("")||pass.equals(""))
                            Toast.makeText(LoginActivity.this ,"Please enter all the fields",Toast.LENGTH_SHORT).show();

                        else {
                            Boolean checkemail=db.checkusername(emailS);
                            if(checkemail){
                                db.updatepassword(emailS,pass);
                                Toast.makeText(LoginActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                });
                dialog.show();
            }
        });
        //login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=email.getText().toString();
                String pass=password.getText().toString();

                if(mail.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkusernamepassword(mail, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("user_email", mail);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void goToSignUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}