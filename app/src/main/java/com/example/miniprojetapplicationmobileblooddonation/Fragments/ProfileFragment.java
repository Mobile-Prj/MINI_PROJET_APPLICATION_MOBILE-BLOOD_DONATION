package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniprojetapplicationmobileblooddonation.Activities.MenuActivities.MenuActivity;
import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.UserProfile;
import com.example.miniprojetapplicationmobileblooddonation.R;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * Profile Fragment
 */

public class ProfileFragment extends Fragment {

    EditText phone, firstName, lastName;
    ImageView profileImage;
    TextView email;
    CheckBox isDonor;
    Button editButton;
    UserProfile userProfile;
    Spinner bloodType, address, gender;
    String user_email;
    ArrayAdapter<CharSequence> bloodTpyeArrayAdapter, genderArrayAdapter, cityArrayAdapter;
    Bitmap imgProfileBitmap;
    RoundedDrawable roundedProfileImage;

    public static Boolean isClicked=false,leave;
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isClicked=false;
        leave=true;
        dialog = new Dialog(getContext());

        // get the email from the Menu activity to perform requests
        if(getArguments() != null){
            user_email = getArguments().getString("user_email");
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        // get the user profile
        userProfile = dataBaseHelper.getUserProfile(user_email);

        editButton = view.findViewById(R.id.edit_profile_button);
        phone = view.findViewById(R.id.profile_telephone);
        address = view.findViewById(R.id.profile_address);
        gender = view.findViewById(R.id.profile_gender);
        bloodType = view.findViewById(R.id.listGrpBlood);
        isDonor = view.findViewById(R.id.profile_checkBox);
        firstName = view.findViewById(R.id.profile_firstName);
        lastName = view.findViewById(R.id.profile_lastName);
        profileImage = view.findViewById(R.id.imageView9);
        email = view.findViewById(R.id.profile_email);

        // for the spinners parts
        bloodTpyeArrayAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.bloodGroups, R.layout.spinner_item);
        genderArrayAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.genders, R.layout.spinner_item);
        cityArrayAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.city_list, R.layout.spinner_item);
        bloodTpyeArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        genderArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

        cityArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        bloodType.setAdapter(bloodTpyeArrayAdapter);
        gender.setAdapter(genderArrayAdapter);
        address.setAdapter(cityArrayAdapter);

        // set initial values in the design
        firstName.setText(userProfile.getFirstName());
        lastName.setText(userProfile.getLastName());
        email.setText(userProfile.getEmail());
        phone.setText(userProfile.getPhone());
        isDonor.setChecked(userProfile.getDonor());
        // for the spinners
        int position_blood_type = bloodTpyeArrayAdapter.getPosition(userProfile.getBloodType());
        bloodType.setSelection(position_blood_type);
        int position_gender = genderArrayAdapter.getPosition(userProfile.getGender());
        gender.setSelection(position_gender);
        int position_city = cityArrayAdapter.getPosition(userProfile.getAddress());
        address.setSelection(position_city);

        imgProfileBitmap = BitmapFactory.decodeByteArray(userProfile.getUserImage(), 0, userProfile.getUserImage().length);
        profileImage.setImageBitmap(imgProfileBitmap);

        enable(false);
        editButton.setOnClickListener(view1 -> {
            if(editButton.getText().toString().equals("edit")){
                isClicked=true;
                // if the edit button is clicked
                // change the edit button to done
                editButton.setText(R.string.edit_done);
                editButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.white));
                editButton.setTextColor(getContext().getResources().getColorStateList(R.color.primary_color));

                // set the editText editable to change values
                enable(true);
                profileImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        imageChooser();
                    }
                });
            }else{
                isClicked=false;
                editButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.primary_color));
                editButton.setTextColor(getContext().getResources().getColorStateList(R.color.white));
                // get the new values of texts
                userProfile.setFirstName(firstName.getText().toString());
                userProfile.setLastName(lastName.getText().toString());
                userProfile.setPhone(phone.getText().toString());
                userProfile.setAddress(address.getSelectedItem().toString());
                userProfile.setGender(gender.getSelectedItem().toString());
                userProfile.setBloodType(bloodType.getSelectedItem().toString());
                userProfile.setDonor(isDonor.isChecked());

                // convert the image to bipmap and then use the bipmap to get roundedImage
                profileImage.setDrawingCacheEnabled(true);
                imgProfileBitmap = profileImage.getDrawingCache();
                roundedProfileImage = RoundedDrawable.fromBitmap(imgProfileBitmap);
                userProfile.setUserImage(getImage(roundedProfileImage));

                boolean updated = dataBaseHelper.updateUserProfile(userProfile);

                if(updated){
                    editButton.setText(R.string.edit_text);
                    enable(false);
                    Toast.makeText(getContext(),"Profile Updated successfully !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Update profile failed !",Toast.LENGTH_LONG).show();
                }

            }

        });

    }



    private void enable(boolean enabled){
        phone.setEnabled(enabled);
        address.setEnabled(enabled);
        gender.setEnabled(enabled);
        bloodType.setEnabled(enabled);
        firstName.setEnabled(enabled);
        lastName.setEnabled(enabled);
        isDonor.setClickable(enabled);
        profileImage.setClickable(enabled);
    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }


    private static byte[] getImage(RoundedDrawable img) {
        Bitmap bitmap = RoundedDrawable.drawableToBitmap(img);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        return  image;
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
                                    getActivity().getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        profileImage.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });


}
