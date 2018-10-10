package com.nicolappli.moodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moodTracker.db";
    public static final String TABLE_NAME = "mood";
    public static final String COL_1 = "id";
    public static final String COL_2 = "mood";
    public static final String COL_3 = "commentary";
    public static final String COL_4 = "date";
    public static final String COL_5 = "color";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MOOD INTEGER, COMMENTARY TEXT, DATE TEXT, COLOR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int mood, String commentary, String date, int color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, mood);
        contentValues.put(COL_3, commentary);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, color);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}
