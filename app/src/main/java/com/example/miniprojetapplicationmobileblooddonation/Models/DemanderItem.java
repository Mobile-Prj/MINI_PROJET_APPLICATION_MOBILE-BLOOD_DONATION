package com.example.miniprojetapplicationmobileblooddonation.Models;

public class DemanderItem {

    String Contact,RequestedBy,Adress,DateTime,BloodCateg;
    int Image;

    public DemanderItem(String contact, String requestedBy, String adress, String dateTime, String bloodCateg, int image) {
        Contact = contact;
        RequestedBy = requestedBy;
        Adress = adress;
        DateTime = dateTime;
        BloodCateg = bloodCateg;
        Image = image;
    }
    public DemanderItem() {

    }

    public String getBloodCateg() {
        return BloodCateg;
    }

    public void setBloodCateg(String bloodCateg) {
        BloodCateg = bloodCateg;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getRequestedBy() {
        return RequestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        RequestedBy = requestedBy;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}