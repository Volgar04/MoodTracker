package com.nicolappli.moodtracker;

import android.database.Cursor;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ArrayList<MoodItem> moodList = new ArrayList<>();

        //Call classes
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MoodAdapter(moodList);
        moodDb = new DatabaseHelper(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        //Make begin the RecyclerView through the bottom
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

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
