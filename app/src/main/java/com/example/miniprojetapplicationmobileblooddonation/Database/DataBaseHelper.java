package com.example.miniprojetapplicationmobileblooddonation.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
