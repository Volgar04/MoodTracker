package com.nicolappli.moodtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBMood extends SQLiteOpenHelper {

    public static class Constants implements BaseColumns {
        static final String DATABASE_NAME="DBMood.db"; //the database name
        static final int DATABASE_VERSION=1; //the database version
        static final String MY_TABLE="Mood"; //the table name

        // COLUMN NAME
        static final String KEY_COL_ID="_id";
        static final String KEY_COL_MOOD="mood";
        static final String KEY_COL_COMMENT="comment";
        static final String KEY_COL_DATE="date";

        // COLUMN INDEX
        public static final int ID_COLUMN =1;
        public static final int MOOD_COLUMN =2;
        public static final int COMMENT_COLUMN =3;
        public static final int DATE_COLUMN =4;
    }

    /**
     * Static string to create the database
     */
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + Constants.MY_TABLE + "(" + Constants.KEY_COL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.KEY_COL_MOOD
            + " INTEGER, " + Constants.KEY_COL_COMMENT + " TEXT, "
            + Constants.KEY_COL_DATE + " TEXT)";

    /**
     * @param context
     *              = the context of the caller
     * @param name
     *              = Database's name
     * @param cursorFactory
     *              = null
     * @param version
     *              = Database's version
     */
    DBMood(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name,cursorFactory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE);
        // Create the new one
        onCreate(db);
        // or do a smartest stuff
    }
}
