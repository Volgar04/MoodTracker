package com.nicolappli.moodtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String MOOD_TABLE_NAME="Humeur.db";
    private static final int MOOD_VERSION=1;

    public DatabaseHandler(Context context){
        super(context, MOOD_TABLE_NAME, null, MOOD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE Humeur (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "humeur INTEGER NOT NULL, "
                + "commentaire TEXT,"
                + "date TEXT NOT NULL);";
        db.execSQL( strSql);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql ="DROP TABLE IF EXISTS Humeur;";
        db.execSQL( strSql);
        this.onCreate(db);
        Log.i("DATABASE","onUpgarde invoked");
    }

    public void insertMood(int humeur, String commentaire){
        commentaire = commentaire.replace("'","''");
        String strSql = "INSERT INTO Humeur (humeur, commentaire, date) values ('"
                      + humeur + "', " + commentaire + "', " + new Date().toString() + ");";
        this.getWritableDatabase().execSQL( strSql);
        Log.i("DATABASE","insertHumeur invoked");
    }
}
