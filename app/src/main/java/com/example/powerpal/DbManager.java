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
        super(context, "powerpal.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table appliance " +
//                    "(id integer primary key," +
//                    "Appliance text," +
//                    "Minimum number," +
//                    "Maximum number," +
//                    "Standby number)"
//        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean getAppliance() {
        SQLiteDatabase db = this.getReadableDatabase();
        // onCreate(db);

        Cursor res =  db.rawQuery( "select * from appliance", null );
        return true;
    }

    public boolean searchAppliance(String appliance) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from appliance where appliance like %" + appliance +"%", null );

        return true;
    }
}