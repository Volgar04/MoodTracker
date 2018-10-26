package com.nicolappli.moodtracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    DatabaseHelper moodDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ArrayList<MoodItem> moodList = new ArrayList<>();

        //Call classes
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new MoodAdapter(moodList);
        moodDb = new DatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //Make begin the RecyclerView through the bottom
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //Create the cursor to get back data
        Cursor cursor = moodDb.getData();
        cursor.moveToFirst();

        //loop to add data in RecyclerView
        do{
            String commentary = cursor.getString(cursor.getColumnIndex("COMMENTARY"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            int mood = cursor.getInt(cursor.getColumnIndex("MOOD"));
            moodList.add(new MoodItem(getApplicationContext(),date,mood,commentary));
        }while(cursor.moveToNext());
        moodDb.close();
    }
}
