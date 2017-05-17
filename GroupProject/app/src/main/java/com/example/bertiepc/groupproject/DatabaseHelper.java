package com.example.bertiepc.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BertiePC on 5/16/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "places.db";

    public static final String Table1Name = "Place_table";
    public static final String T1_Col1 = "PlaceID";
    public static final String T1_Col2 = "Name";
    public static final String T1_Col3 = "Town";
    public static final String T1_Col4 = "Type";
    public static final String T1_Col5 = "TelNum";
    public static final String T1_Col6 = "Email";
    public static final String T1_Col7 = "Website";
    public static final String T1_Col8 = "Location";
    public static final String T1_Col9 = "Description";
    public static final String T1_Col10 = "Picture";

    public static final String Table2Name = "Comments_table";
    public static final String T2_Col1 = "CommentID";
    public static final String T2_Col2 = "PlaceID";
    public static final String T2_Col3 = "Rating";
    public static final String T2_Col4 = "Comment";


    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table1Name+" ("+T1_Col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+T1_Col2+" TEXT,  "+T1_Col3+" TEXT, "+T1_Col4+" TEXT, "+T1_Col5+" TEXT, "+T1_Col6+" TEXT, "+T1_Col7+" TEXT, "+T1_Col8+" TEXT, "+T1_Col9+" TEXT, "+T1_Col10+" BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Table1Name);
        onCreate(db);
    }

    public boolean insertDataT1(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(T1_Col2, "Joe's Beerhouse");
        contentValues.put(T1_Col3, "Windhoek");
        contentValues.put(T1_Col4, "Restaurant");
        contentValues.put(T1_Col5, "+264 61 232 457 ");
        contentValues.put(T1_Col6, "info@joesbeerhouse.com");
        contentValues.put(T1_Col7, "http://www.joesbeerhouse.com/");
        contentValues.put(T1_Col8, "https://www.google.com.na/maps/dir/''/joe's+beerhouse/@-22.5509228,17.0203086,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x1c0b1b575fdbeccd:0xe034c71b981a37ea!2m2!1d17.090349!2d-22.550938");
        contentValues.put(T1_Col9, "Inspired by the fascinating character of Namibia and its people, Joe's is where a love for adventure, stories and living to the fullest, comes to vibrant life. Through our unique combination of delicious and authentic food, heartfelt hospitality, and our one-of-its-kind atmosphere, we feed the mouth and soul, celebrate old memories; and build new ones with you\n" +
                "\n" +
                "So much more than just another restaurant. For people who still dream of truly great escape.");

        long result = db.insert(Table1Name, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public List<String> getAllTownsT1(){
        List<String> towns = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + T1_Col3 + " FROM " + Table1Name, null);
       // Cursor cursor = db.rawQuery("SELECT DISTINCT * FROM " + Table1Name, null);

        if (cursor.moveToFirst()) {
            do {
                towns.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return towns;
    }

    public List<String> getAllTypesT1(){
        List<String> types = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + T1_Col4 + " FROM " + Table1Name, null);
       // Cursor cursor = db.rawQuery("SELECT DISTINCT * FROM " + Table1Name, null);

        if (cursor.moveToFirst()) {
            do {
                types.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return types;
    }
}
