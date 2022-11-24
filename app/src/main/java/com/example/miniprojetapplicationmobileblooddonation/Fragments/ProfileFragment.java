package com.example.miniprojetapplicationmobileblooddonation.Fragments;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniprojetapplicationmobileblooddonation.Database.DataBaseHelper;
import com.example.miniprojetapplicationmobileblooddonation.Models.UserProfile;
import com.example.miniprojetapplicationmobileblooddonation.R;

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
                null,
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
        //bloodType.setOnItemSelectedListener(this);

        // set initial values in the deseign
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
                    profileImage.setClickable(true);
                }else{

                    // get the new values of texts
                    userProfile.setFirstName(firstName.getText().toString());
                    userProfile.setLastName(lastName.getText().toString());
                    userProfile.setPhone(phone.getText().toString());
                    userProfile.setAddress(address.getSelectedItem().toString());
                    userProfile.setGender(gender.getSelectedItem().toString());
                    userProfile.setBloodType(bloodType.getSelectedItem().toString());
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

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
