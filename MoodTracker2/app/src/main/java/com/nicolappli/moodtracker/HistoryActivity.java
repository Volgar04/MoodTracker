package com.nicolappli.moodtracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper moodDb;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ArrayList<MoodItem> moodList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MoodAdapter(moodList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        moodDb = new DatabaseHelper(this);

        Cursor cursor = database.rawQuery("SELECT mood, commentary, date From mood",null);
        displayResult(cursor);

        //moodList.add(new MoodItem("27-01-2018",0,"  "));
        //moodList.add(new MoodItem("28-01-2018",4,"Salut"));
        //moodList.add(new MoodItem("29-01-2018",2,"Salut ça va?"));
        //moodList.add(new MoodItem("27-01-2018",3,"  "));
        //moodList.add(new MoodItem("28-01-2018",0,"yooooo"));
        //moodList.add(new MoodItem("29-01-2018",2,"Hello"));
        //moodList.add(new MoodItem("27-01-2018",1,"je vais bien"));
        //moodList.add(new MoodItem("28-01-2018",2,"  "));
        //moodList.add(new MoodItem("29-01-2018",0,"  "));
        //moodList.add(new MoodItem("27-01-2018",1,"  "));
        //moodList.add(new MoodItem("28-01-2018",3,"ça marche?"));
        //moodList.add(new MoodItem("29-01-2018",0,"  "));
        //moodList.add(new MoodItem("27-01-2018",4,"salut"));
        //moodList.add(new MoodItem("28-01-2018",4,"  "));
        //moodList.add(new MoodItem("29-01-2018",2,"oui"));
        //moodList.add(new MoodItem("27-01-2018",3,"salut"));
        //moodList.add(new MoodItem("28-01-2018",4,"  "));
        //moodList.add(new MoodItem("29-01-2018",1,"ok"));





        //moodDb = new DatabaseHelper(this);
        //SQLiteDatabase database = moodDb.getReadableDatabase();
//
        //Cursor cursor = database.rawQuery("SELECT mood, commentary, date From mood",null);
        //int mood[] = new int[cursor.getCount()];
        //String commentary[] = new String[cursor.getCount()];
        //String date[] = new String[cursor.getCount()];
        //int i = 0;
        //if(cursor.getCount()>0){
        //    cursor.moveToFirst();
        //            do{
        //                mood[i] = cursor.getInt(cursor.getColumnIndex("mood"));
        //                commentary[i] = cursor.getString(cursor.getColumnIndex("commentary"));
        //                date[i] = cursor.getString(cursor.getColumnIndex("date"));
        //                moodList.add(new MoodItem(date[i],mood[i],commentary[i]));
        //                i++;
        //            }while(cursor.moveToNext());
        //            cursor.close();
        //}


        //helper = new DatabaseHelper(this);
//
        //SQLiteDatabase db = helper.getReadableDatabase();
//
        //String table = "mood";
        //String[] columns = null;
        //String selection = null;
        //String[] selectionArgs = null;
        //String groupBy = null;
        //String having = null;
        //String orderBy = null;
//
        //Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
//
        //while(cursor.moveToNext()){
        //    Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
        //    Integer mood = cursor.getInt(cursor.getColumnIndex("mood"));
        //    String commentary = cursor.getString(cursor.getColumnIndex("commentary"));
        //    String date = cursor.getString(cursor.getColumnIndex("date"));
        //    moodList.add(new MoodItem(date,mood,commentary));
        //}
//
        //db.close();
    }

    public void displayResult(Cursor cursor){
        ArrayList<MoodItem> moodList = new ArrayList<>();
        if(cursor.moveToFirst()){
            String commentary = cursor.getString(cursor.getColumnIndex("commentary"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int mood = cursor.getInt(cursor.getColumnIndex("mood"));

            int count=0;
            do{
                moodList.add(new MoodItem(date,mood,commentary));
                count++;
            }while(cursor.moveToNext());
        }
    }

    //public void viewData() {
    //    Cursor result = moodDb.getData();
    //    if (result.getCount() == 0) {
    //        showMessage("Error", "No data found");
    //        return;
    //    }
//
    //    StringBuffer buffer = new StringBuffer();
    //    while (result.moveToNext()) {
    //        buffer.append("Id : ").append(result.getString(0)).append("\n");
    //        buffer.append("Mood : ").append(result.getString(1)).append("\n");
    //        buffer.append("Commentary : ").append(result.getString(2)).append("\n");
    //        buffer.append("Date : ").append(result.getString(3)).append("\n\n");
    //    }
    //    showMessage("Data", buffer.toString());
    //}

    //public void showMessage(String title, String message){
    //    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //    builder.setCancelable(true);
    //    builder.setTitle(title);
    //    builder.setMessage(message);
    //    builder.show();
    //}
}
