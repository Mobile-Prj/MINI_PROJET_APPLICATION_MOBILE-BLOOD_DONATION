package com.example.miniprojetapplicationmobileblooddonation.Models;

/**
 * DemanderItem
 */

public class DemanderItem {

   //Attributes
    String Contact,RequestedBy,Adress,DateTime,BloodCateg;
    byte[] image;

    //Constructors
    public DemanderItem(String contact, String requestedBy, String adress, String dateTime, String bloodCateg, byte[] Image) {
        Contact = contact;
        RequestedBy = requestedBy;
        Adress = adress;
        DateTime = dateTime;
        BloodCateg = bloodCateg;
        image = Image;
    }
    public DemanderItem() {

    }

    //Getters and Setters

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] Image) {
        image = Image;
    }
}