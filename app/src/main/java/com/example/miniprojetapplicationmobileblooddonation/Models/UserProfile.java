package com.example.miniprojetapplicationmobileblooddonation.Models;

import android.graphics.Bitmap;

public class UserProfile {
    Bitmap userImage;
    String firstName;
    String LastName;
    String email;
    String phone;
    String address;
    String gender;
    String bloodType;
    boolean isDonor;

    public UserProfile(Bitmap userImage, String firstName, String lastName, String email, String phone, String address, String gender, String bloodType, boolean isDonor) {
        this.userImage = userImage;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.bloodType = bloodType;
        this.isDonor = isDonor;
    }

    public UserProfile() {

    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public boolean getDonor() {
        return isDonor;
    }

    public void setDonor(boolean donor) {
        isDonor = donor;
    }
}
