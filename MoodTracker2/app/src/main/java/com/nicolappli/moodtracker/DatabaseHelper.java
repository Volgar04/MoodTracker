package com.nicolappli.moodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moodTracker.db";
    public static final String TABLE_NAME = "mood";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "mood";
    public static final String COL_3 = "commentary";
    public static final String COL_4 = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MOOD INTEGER, COMMENTARY TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int mood, String commentary, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, mood);
        contentValues.put(COL_3, commentary);
        contentValues.put(COL_4, date);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC LIMIT 8", null);
    }

    public void removeData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String tableName = TABLE_NAME;
        String whereClause = "DATE=?";
        String[] whereArgs = new String[] {date};
        db.delete(tableName,whereClause,whereArgs);
    }
}
