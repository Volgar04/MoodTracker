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


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        mAdapter = new MoodAdapter(moodList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        moodDb = new DatabaseHelper(this);

        Cursor cursor = moodDb.getData();
        cursor.moveToFirst();

        do{
            String commentary = cursor.getString(cursor.getColumnIndex("COMMENTARY"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            int mood = cursor.getInt(cursor.getColumnIndex("MOOD"));
            moodList.add(new MoodItem(getApplicationContext(),date,mood,commentary));
        }while(cursor.moveToNext());

        moodDb.close();
    }
}
