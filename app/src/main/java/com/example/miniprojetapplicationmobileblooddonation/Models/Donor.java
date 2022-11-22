package com.example.miniprojetapplicationmobileblooddonation.Models;

public class Donor {

    String title;
    String name;
    String phone;
    String city;
    int image;

    public Donor(String title,String name, String phone,String city, int image) {
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.image = image;
    }

    public Donor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}