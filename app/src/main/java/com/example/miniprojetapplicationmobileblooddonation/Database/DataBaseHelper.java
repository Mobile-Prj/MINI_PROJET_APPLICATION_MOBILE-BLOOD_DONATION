package com.example.miniprojetapplicationmobileblooddonation.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miniprojetapplicationmobileblooddonation.Models.Donor;
import com.example.miniprojetapplicationmobileblooddonation.Models.UserProfile;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "BloodDonationDB1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE Table
        sqLiteDatabase.execSQL("CREATE TABLE user(id integer primary key AUTOINCREMENT,FirstName text,LastName text,gender text,Phone text,Address text,email text,BloodCategory text,password text,image text,Isdonor boolean)");
        sqLiteDatabase.execSQL("CREATE TABLE requester(id integer primary key AUTOINCREMENT,FullName text,Contact text,PostedOn text,Location text,BloodCategory text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("drop table if exists requester");
        onCreate(sqLiteDatabase);

    }

    public boolean insertRequester(String s1, String s2, String d1, String s4, String s5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("FullName",s1);
        c.put("Contact",s2);
        c.put("PostedOn", d1);
        c.put("Location",s4);
        c.put("BloodCategory",s5);

        long result = db.insert("requester",null,c);
        if(result==-1){
            return false;
        }
        else return true;

    }

    public Cursor getRequesters(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =db.rawQuery("SELECT * from requester ",null);
        return c;
    }

    //Display list of donors
    public ArrayList<Donor> getDonors(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM USER WHERE Isdonor  ";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Donor> donors = new ArrayList<Donor>();
        while(cursor.moveToNext()){
            Donor donor = new Donor();
            donor.setName(cursor.getString(2));
            donor.setTitle(cursor.getString(7));
            donor.setCity(cursor.getString(5));
            donor.setPhone(cursor.getString(4));
            donor.setImage(cursor.getInt(9));
            donors.add(donor);
        }
        cursor.close();
        db.close();
        return donors;
    }

    // Search Donors

    public ArrayList<Donor> getSearchedDonors(String loca, String catB){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM USER WHERE Isdonor AND Address=? AND BloodCategory=?";
        ArrayList<Donor> donors = new ArrayList<Donor>();
        Cursor cursor = db.rawQuery(query, new String[]{loca,catB});
        while(cursor.moveToNext()){
            Donor donor = new Donor();
            donor.setName(cursor.getString(2));
            donor.setTitle(cursor.getString(7));
            donor.setCity(cursor.getString(5));
            donor.setPhone(cursor.getString(4));
            donor.setImage(cursor.getInt(9));
            donors.add(donor);
        }
        cursor.close();
        db.close();
        return donors;


    }

    // get current user profile function
    public UserProfile getUserProfile(String email){
        UserProfile userProfile = new UserProfile();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM USER WHERE email=?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        // get informations from the cursor
        userProfile.setUserImage(cursor.getString(9));
        userProfile.setFirstName(cursor.getString(1));
        userProfile.setLastName(cursor.getString(2));
        userProfile.setEmail(cursor.getString(6));
        userProfile.setAddress(cursor.getString(5));
        userProfile.setGender(cursor.getString(3));
        userProfile.setPhone(cursor.getString(3));
        userProfile.setBloodType(cursor.getString(7));
        userProfile.setUserImage(cursor.getString(9));
        userProfile.setDonor(cursor.getInt(10)==1);

        return userProfile;

    }

    public boolean updateUserProfile(UserProfile userProfile){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", userProfile.getFirstName());
        contentValues.put("lastName", userProfile.getLastName());
        contentValues.put("address", userProfile.getAddress());
        contentValues.put("phone", userProfile.getPhone());
        contentValues.put("gender", userProfile.getGender());
        contentValues.put("bloodType", userProfile.getBloodType());
        contentValues.put("image", userProfile.getUserImage());
        contentValues.put("isDonor", userProfile.getDonor());

        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE email = ?", new String[]{userProfile.getEmail()});

        if (cursor.getCount() > 0){
            long result = db.update("user", contentValues, "email=?", new String[]{userProfile.getEmail()});

            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }


}
