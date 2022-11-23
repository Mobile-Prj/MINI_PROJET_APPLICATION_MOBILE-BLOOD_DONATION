package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.UserProfile;
import com.example.miniprojetapplicationmobileblooddonation.R;

public class ProfileFragment extends Fragment {

    EditText phone, address, gender, bloodType, firstName, lastName;
    ImageView profileImage;
    TextView email;
    CheckBox isDonor;
    Button editButton;
    UserProfile userProfile;
    String user_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        userProfile = new UserProfile(
                "img",
                "Frederic",
                "FAYA",
                "fredericfaya@gmail.com",
                "+212638743853",
                "my adresse",
                "Male",
                "O+",
                true
        );

        // get the user profile
        // userProfile = dataBaseHelper.getUserProfile(user_email);


        editButton = view.findViewById(R.id.edit_profile_button);
        phone = view.findViewById(R.id.profile_telephone);
        address = view.findViewById(R.id.profile_address);
        gender = view.findViewById(R.id.profile_gender);
        bloodType = view.findViewById(R.id.profile_blood_type);
        isDonor = view.findViewById(R.id.profile_checkBox);
        firstName = view.findViewById(R.id.profile_firstName);
        lastName = view.findViewById(R.id.profile_lastName);
        profileImage = view.findViewById(R.id.imageView9);
        email = view.findViewById(R.id.profile_email);

        // set initial values in the deseign
        firstName.setText(userProfile.getFirstName());
        lastName.setText(userProfile.getLastName());
        email.setText(userProfile.getEmail());
        phone.setText(userProfile.getPhone());
        address.setText(userProfile.getAddress());
        gender.setText(userProfile.getGender());
        bloodType.setText(userProfile.getBloodType());
        isDonor.setChecked(userProfile.getDonor());


        phone.setEnabled(false);
        address.setEnabled(false);
        gender.setEnabled(false);
        bloodType.setEnabled(false);
        firstName.setEnabled(false);
        lastName.setEnabled(false);
        isDonor.setClickable(false);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editButton.getText().toString().equals("edit")){
                    // if the edit button is clicked
                    // change the edit button to done
                    editButton.setText(R.string.edit_done);
                    // set the editText editable to change values
                    phone.setEnabled(true);
                    address.setEnabled(true);
                    gender.setEnabled(true);
                    bloodType.setEnabled(true);
                    firstName.setEnabled(true);
                    lastName.setEnabled(true);
                    isDonor.setClickable(true);
                }else{

                    // get the new values of texts
                    userProfile.setFirstName(firstName.getText().toString());
                    userProfile.setLastName(lastName.getText().toString());
                    userProfile.setPhone(phone.getText().toString());
                    userProfile.setAddress(address.getText().toString());
                    userProfile.setGender(gender.getText().toString());
                    userProfile.setBloodType(bloodType.getText().toString());
                    userProfile.setDonor(isDonor.isChecked());

                    boolean updated = dataBaseHelper.updateUserProfile(userProfile);

                    if(updated){
                        editButton.setText(R.string.edit_text);
                        phone.setEnabled(false);
                        address.setEnabled(false);
                        gender.setEnabled(false);
                        bloodType.setEnabled(false);
                        firstName.setEnabled(false);
                        lastName.setEnabled(false);
                        isDonor.setClickable(false);
                    }else{
                        Toast.makeText(getContext(),"Update profile failed !",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}
