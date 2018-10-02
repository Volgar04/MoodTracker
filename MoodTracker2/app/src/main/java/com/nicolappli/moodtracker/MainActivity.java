package com.nicolappli.moodtracker;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    RelativeLayout principalScreen;
    ImageView imageSmiley;
    ImageButton imageComment;
    ImageButton imageHistory;
    int actualMoodScreen = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        principalScreen=findViewById(R.id.principalScreen);
        imageSmiley=findViewById(R.id.imageSmiley);
        imageComment=findViewById(R.id.imageComment);
        imageHistory=findViewById(R.id.imageHistory);

        /**
         * Tests et affichages d'où on se situe en fonction des écrans d'humeur
         */

        principalScreen.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                actualMoodScreen--;
                if (actualMoodScreen == 0) {
                    principalScreen.setBackgroundResource(R.color.banana_yellow);
                    imageSmiley.setImageResource(R.drawable.smiley_super_happy);
                } else if (actualMoodScreen == 1) {
                    principalScreen.setBackgroundResource(R.color.light_sage);
                    imageSmiley.setImageResource(R.drawable.smiley_happy);
                } else if (actualMoodScreen == 2) {
                    principalScreen.setBackgroundResource(R.color.cornflower_blue_65);
                    imageSmiley.setImageResource(R.drawable.smiley_normal);
                } else if (actualMoodScreen == 3) {
                    principalScreen.setBackgroundResource(R.color.warm_grey);
                    imageSmiley.setImageResource(R.drawable.smiley_disappointed);
                } else if (actualMoodScreen == 4) {
                    principalScreen.setBackgroundResource(R.color.faded_red);
                    imageSmiley.setImageResource(R.drawable.smiley_sad);
                } else if (actualMoodScreen==5) {
                    actualMoodScreen=4;
                } else {
                    actualMoodScreen=0;
                }
            }

            public void onSwipeBottom() {
                actualMoodScreen++;
                if (actualMoodScreen == 0) {
                    principalScreen.setBackgroundResource(R.color.banana_yellow);
                    imageSmiley.setImageResource(R.drawable.smiley_super_happy);
                } else if (actualMoodScreen == 1) {
                    principalScreen.setBackgroundResource(R.color.light_sage);
                    imageSmiley.setImageResource(R.drawable.smiley_happy);
                } else if (actualMoodScreen == 2) {
                    principalScreen.setBackgroundResource(R.color.cornflower_blue_65);
                    imageSmiley.setImageResource(R.drawable.smiley_normal);
                } else if (actualMoodScreen == 3) {
                    principalScreen.setBackgroundResource(R.color.warm_grey);
                    imageSmiley.setImageResource(R.drawable.smiley_disappointed);
                } else if (actualMoodScreen == 4) {
                    principalScreen.setBackgroundResource(R.color.faded_red);
                    imageSmiley.setImageResource(R.drawable.smiley_sad);
                } else if (actualMoodScreen==5) {
                    actualMoodScreen=4;
                } else {
                    actualMoodScreen=0;
                }
            }
        });

        /**
         * Lancer un AlertDialogue quand l'utilisateur veut ajouter un commentaire
         */

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
                        if(!mComment.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Commentaire reçu",Toast.LENGTH_SHORT).show();
                        }else{
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


        /**
         * Lancer une nouvelle activitée quand l'utilisateur veut consulter son historique d'humeur
         */

        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }
}
