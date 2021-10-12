package com.example.powerpal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DbManager extends SQLiteOpenHelper {

    public DbManager(Context context) {
        super(context,  "powerpal.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean getAppliance() {
        System.out.println(System.getProperty("user.dir"));
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from appliance", null );
        return true;
    }

    public boolean searchAppliance(String appliance) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from appliance where appliance like %" + appliance +"%", null );

        return true;
    }
}