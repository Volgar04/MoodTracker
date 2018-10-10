package com.nicolappli.moodtracker;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int moodChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        if(intent!=null){
            moodChosen=intent.getIntExtra("moodChosen",6);
        }

        ArrayList<MoodItem> moodList = new ArrayList<>();
        moodList.add(new MoodItem("01-01-2001",1,2));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new MoodAdapter(moodList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("moodChosen => " + moodChosen);
    }
}
