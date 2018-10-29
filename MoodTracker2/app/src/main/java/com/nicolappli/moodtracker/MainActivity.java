package com.nicolappli.moodtracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

            /**
             * Declarations of variables
             */
    private RelativeLayout principalScreen;
    private ImageView imageSmiley;
    public ImageButton imageComment;
    public ImageButton imageHistory;

    private List<Integer> color;
    private List<Integer> drawable;
    DatabaseHelper moodDb;

    public String commentary = "";
    public String currentDate;
    private int actualMoodScreen = 1;

            /**
             * Method allowing to get back the good color of the background
             */
    public void initColor(){
        color = new ArrayList<>();

        color.add(R.color.banana_yellow); //=0
        color.add(R.color.light_sage); //=1
        color.add(R.color.cornflower_blue_65); //=2
        color.add(R.color.warm_grey); //=3
        color.add(R.color.faded_red); //=4
    }

            /**
             * Method allowing to get back the good smiley
             */
    public void initDrawable(){
        drawable = new ArrayList<>();

        drawable.add(R.drawable.smiley_super_happy); //=0
        drawable.add(R.drawable.smiley_happy); //=1
        drawable.add(R.drawable.smiley_normal); //=2
        drawable.add(R.drawable.smiley_disappointed); //=3
        drawable.add(R.drawable.smiley_sad); //=4
    }

    @SuppressLint("ClickableViewAccessibility")

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //Connection of variables
        principalScreen=findViewById(R.id.principalScreen);
        imageSmiley=findViewById(R.id.imageSmiley);
        imageComment=findViewById(R.id.imageComment);
        imageHistory=findViewById(R.id.imageHistory);

        initColor();
        initDrawable();

            //Creation of variables sound
        final MediaPlayer sound1 = MediaPlayer.create(this, R.raw.son1);
        final MediaPlayer sound2 = MediaPlayer.create(this, R.raw.son2);
        final MediaPlayer sound3 = MediaPlayer.create(this, R.raw.son3);
        final MediaPlayer sound4 = MediaPlayer.create(this, R.raw.son4);
        final MediaPlayer sound5 = MediaPlayer.create(this, R.raw.son5);

        moodDb = new DatabaseHelper(this);

            //Recovery of the current date
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            //Display of the various screens according to gestures
        principalScreen.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                actualMoodScreen--;
                if(actualMoodScreen<0){
                    actualMoodScreen=0;
                }
                principalScreen.setBackgroundResource(color.get(actualMoodScreen));
                imageSmiley.setImageResource(drawable.get(actualMoodScreen));
                if(actualMoodScreen==0) sound1.start();
                else if(actualMoodScreen==1) sound2.start();
                else if(actualMoodScreen==2) sound3.start();
                else if(actualMoodScreen==3) sound4.start();
                else if(actualMoodScreen==4) sound5.start();
            }

            public void onSwipeBottom() {
                actualMoodScreen++;
                if (actualMoodScreen > 4) {
                    actualMoodScreen = 4;
                }
                principalScreen.setBackgroundResource(color.get(actualMoodScreen));
                imageSmiley.setImageResource(drawable.get(actualMoodScreen));
                if(actualMoodScreen==0) sound1.start();
                else if(actualMoodScreen==1) sound2.start();
                else if(actualMoodScreen==2) sound3.start();
                else if(actualMoodScreen==3) sound4.start();
                else if(actualMoodScreen==4) sound5.start();
            }
        });

            //Launch an AlertDialogue when the user want to add a commentary
        imageComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder comment = new AlertDialog.Builder(MainActivity.this);
                final View commentView = getLayoutInflater().inflate(R.layout.add_comment, null);
                final EditText mComment= commentView.findViewById(R.id.etxCommentUser);
                Button mOk=commentView.findViewById(R.id.btnOk);
                Button mCancel=commentView.findViewById(R.id.btnCancel);
                comment.setView(commentView);
                final AlertDialog alertComment = comment.create();
                alertComment.show();
                mOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mComment.getText().toString().length()>=255){
                            Toast.makeText(MainActivity.this,"Le commentaire doit être inférieur ou égal a 255",Toast.LENGTH_SHORT).show();
                        }else if(!mComment.getText().toString().isEmpty()){
                            commentary =mComment.getText().toString();
                            alertComment.cancel();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Veuillez écrire un commentaire !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertComment.cancel();
                    }
                });
            }
        });


        //***************** To launch the history activity ****************************************************************************************************************************************************/

            //Launch an new activity when the user want to consult his mood history
        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }


    //***************** Insert data into the database "mood.db" **********************************************************************************************************************************************/

    public void addData(){
        moodDb.removeData(currentDate);
        moodDb.insertData(actualMoodScreen,commentary,currentDate);
    }

    //***************** Remove data into the database "mood.db" if a row with currentDate already exist *******************************************************************************************************/

    @Override
    protected void onPause() {
        addData();
        commentary="";
        super.onPause();
    }
}
