package com.example.miniprojetapplicationmobileblooddonation.Models;
/**
 * Donor
 */

public class Donor {
    // Attributes
    String title;
    String name;
    String phone;
    String city;
    byte[] image;

    // constructors

    public Donor(String title,String name, String phone,String city, byte[] image) {
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.image = image;
    }

    public Donor() {

    }
    // Getters and setters
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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