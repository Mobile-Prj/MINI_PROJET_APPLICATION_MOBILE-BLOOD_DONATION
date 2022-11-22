package com.example.miniprojetapplicationmobileblooddonation.Models;

import android.media.Image;

public class UserProfile {

    String userImage;
    String firstName;
    String LastName;
    String email;
    String phone;
    String address;
    String gender;
    String bloodType;
    String isDonor;

    public UserProfile(String userImage, String firstName, String lastName, String email, String phone, String address, String gender, String bloodType, String isDonor) {
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
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

    public String getDonor() {
        return isDonor;
    }

    public void setDonor(String donor) {
        isDonor = donor;
    }
}
