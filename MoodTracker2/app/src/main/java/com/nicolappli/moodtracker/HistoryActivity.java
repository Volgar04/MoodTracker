package com.nicolappli.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private TextView moodView;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        moodView = findViewById(R.id.moodView);
        databaseHandler=new DatabaseHandler(this);

        databaseHandler.insertMood(1, "Très satisfait de ma journée !");
        databaseHandler.insertMood(5, "Ma copine ma quitté...");

        databaseHandler.close();
    }
}
