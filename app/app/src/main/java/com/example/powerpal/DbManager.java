package com.example.powerpal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DbManager extends SQLiteOpenHelper {

    public DbManager(Context context) {
        super(context, "./database/powerpal.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean searchAppliance(String appliance) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from appliance where appliance like %" + appliance +"%", null );

        return true;
    }
}