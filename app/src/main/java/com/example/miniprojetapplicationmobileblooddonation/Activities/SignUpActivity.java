package com.example.miniprojetapplicationmobileblooddonation.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
import com.makeramen.roundedimageview.RoundedDrawable;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText fname,lname,mail,add,password;
    RadioGroup rg;
    Spinner listSpinner;
    MaskedEditText phone;
    ImageView img;
    CheckBox donor;
    Button signup ,selectimg;
    Typeface myFont;
    TextView appTitle;
    Spinner listBloodGroup;
    ArrayAdapter<CharSequence> arrayAdapter;
    DataBaseHelper db;
    Dialog dialog;
    Boolean isImageSelected;
    Bitmap imgProfileBitmap;
    RoundedDrawable roundedProfileImage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dialog = new Dialog(this);

        appTitle = findViewById(R.id.app_title);
        listBloodGroup = findViewById(R.id.listGrpBlood);
        listSpinner=findViewById(R.id.listCities);
        myFont = Typeface.createFromAsset(getAssets(), "fonts/LavishlyYours-Regular.ttf");
        appTitle.setTypeface(myFont);
        isImageSelected=false;
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.bloodGroups, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        listBloodGroup.setAdapter(arrayAdapter);
        listBloodGroup.setOnItemSelectedListener(this);
        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastName);
        mail=findViewById(R.id.email);
        phone=findViewById(R.id.editTextPhone);
        password=findViewById(R.id.password_login);
        rg=findViewById(R.id.radioGroup);
        img=findViewById(R.id.add_img);
        donor=findViewById(R.id.checkBox_cond);
        signup=findViewById(R.id.signup_button);
        db=new DataBaseHelper(this);
        selectimg=findViewById(R.id.picture_title);

        img.setOnClickListener(view -> imageChooser());

        signup.setOnClickListener(view -> {
            String gender;
            String prenom=fname.getText().toString();
            String nom=lname.getText().toString();
            String email=mail.getText().toString();
            String num=phone.getText().toString();
            String addr=listSpinner.getSelectedItem().toString();
            String pass=password.getText().toString();
            String BloodGrup=listBloodGroup.getSelectedItem().toString();
            if(rg.getCheckedRadioButtonId()==R.id.radioButton2) gender="Female";
            else gender="Male";
            Boolean don= donor.isChecked();

            if(prenom.equals("")||nom.equals("")||email.equals("")||num.equals("")|| pass.equals("")||BloodGrup.equals("Blood Group")||addr.equals("City"))
                Toast.makeText(SignUpActivity.this ,"Please enter all the fields",Toast.LENGTH_SHORT).show();
            else {
                if(num.length()!=17)
                    Toast.makeText(SignUpActivity.this ,"The Contact field should follow the format number",Toast.LENGTH_SHORT).show();

                else{
                    String emailToText=mail.getText().toString();
                    if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
                        Boolean checkuser = db.checkusername(email);
                        if (!checkuser) {
                            boolean insert = db.insertData(prenom, nom, email, num, addr, gender, BloodGrup, pass, Convert_ToBipmap(img), don);
                            if (insert) {
                               openSuccessDialog();
                            } else {
                                openFailDialog();
                            }

                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "Email already exists! please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    }

                }


            }

        });
    }
    private  byte[] Convert_ToBipmap(ImageView img){
        // convert the image to bipmap and then use the bipmap to get roundedImage
        img.setDrawingCacheEnabled(true);
        imgProfileBitmap = img.getDrawingCache();
        roundedProfileImage = RoundedDrawable.fromBitmap(imgProfileBitmap);
        return getImage(roundedProfileImage);
    }
    private byte[] getImage(RoundedDrawable img) {
        byte[] image = new byte[0];
        if(isImageSelected) {
            Bitmap bitmap = RoundedDrawable.drawableToBitmap(img);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = stream.toByteArray();
            return image;

        }
        else {
            return ConvertImage();

        }
    }

    private byte[] ConvertImage() {
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_donor);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
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
                        isImageSelected=true;
                    }
                }
            });

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void openFailDialog() {
        dialog.setContentView(R.layout.failed_signup_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnOk = dialog.findViewById(R.id.btn_Error);

        btnOk.setOnClickListener(view -> {
            dialog.dismiss();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

        dialog.show();
    }
    private void openSuccessDialog() {
        dialog.setContentView(R.layout.successfull_signup_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnOk = dialog.findViewById(R.id.btn_Success);

        btnOk.setOnClickListener(view -> {
            dialog.dismiss();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        dialog.show();
    }
}