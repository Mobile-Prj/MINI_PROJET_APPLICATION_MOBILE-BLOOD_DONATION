package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText fname,lname,mail,phone,add,password;
    RadioGroup rg;
    ImageView img;
    CheckBox donor;
    Button signup ,selectimg;
    Typeface myFont;
    TextView appTitle;
    Spinner listBloodGroup;
    ArrayAdapter<CharSequence> arrayAdapter;
    DataBaseHelper db;
    int SELECT_PICTURE = 200;
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
        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastName);
        mail=findViewById(R.id.email);
        phone=findViewById(R.id.editTextPhone);
        add=findViewById(R.id.address);
        password=findViewById(R.id.password_login);
        rg=findViewById(R.id.radioGroup);
        img=findViewById(R.id.add_img);
        donor=findViewById(R.id.checkBox_cond);
        signup=findViewById(R.id.signup_button);
        db=new DataBaseHelper(this);
        selectimg=findViewById(R.id.picture_title);

        selectimg.setOnClickListener(view -> imageChooser());

        signup.setOnClickListener(view -> {
            String gender;
            String prenom=fname.getText().toString();
            String nom=lname.getText().toString();
            String email=mail.getText().toString();
            String num=phone.getText().toString();
            String addr=add.getText().toString();
            String pass=password.getText().toString();
            String BloodGrup=listBloodGroup.getSelectedItem().toString();
            if(rg.getCheckedRadioButtonId()==R.id.radioButton2) gender="FEMALE";
            else gender="Male";
            Boolean don= Boolean.valueOf(donor.getText().toString());

            if(prenom.equals("")||nom.equals("")||email.equals("")||num.equals("")||addr.equals("") || pass.equals("")||BloodGrup.equals("Groupe"))
                Toast.makeText(SignUpActivity.this ,"Please enter all the fields",Toast.LENGTH_SHORT).show();

            else {
                String emailToText=mail.getText().toString();
                if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
                    Boolean checkuser = db.checkusername(email);
                    if (!checkuser) {
                        boolean insert = db.insertData(prenom, nom, email, num, addr, gender, BloodGrup, pass, getImage(img), don);
                        if (insert) {
                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Email already exists! please Sign in", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    private byte[] getImage(ImageView img) {
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        return  image;
    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        img.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}