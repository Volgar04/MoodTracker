package com.nicolappli.moodtracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    public DBMood dbMood;
    private SQLiteDatabase db;

    private String commentary;
    private int actualMoodScreen = 1;

            /**
             * Method allowing to get back the good color of the background
             */
    private void initColor(){
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
    private void initDrawable(){
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

            //Creation of database
        dbMood = new DBMood(this, DBMood.Constants.DATABASE_NAME, null, DBMood.Constants.DATABASE_VERSION);
        openDB();
        ContentValues contentValues = new ContentValues();
        long rowId = insertRecord(contentValues);
        deleteRecord(rowId);

            //Recovery of the current date
        String currentDate = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        System.out.println("current date => " + currentDate);

            //Display of the various screens according to gestures
        principalScreen.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                actualMoodScreen--;
                if(actualMoodScreen<0){
                    actualMoodScreen=0;
                }
                    principalScreen.setBackgroundResource(color.get(actualMoodScreen));
                    imageSmiley.setImageResource(drawable.get(actualMoodScreen));
            }

            public void onSwipeBottom() {
                actualMoodScreen++;
                if(actualMoodScreen>4){
                    actualMoodScreen=4;
                }
                principalScreen.setBackgroundResource(color.get(actualMoodScreen));
                imageSmiley.setImageResource(drawable.get(actualMoodScreen));
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
                            Toast.makeText(MainActivity.this,"Commentaire reçu",Toast.LENGTH_SHORT).show();
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

        //***************** For the history activity *********************************************************************************************************************************************************/


            //Launch an new activity when the user want to consult his mood history
        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }


    //***************** Database management ********************************************************************************************************************************************************************/

    /**
     * open the database
     * @throws SQLiteException
     */
    public void openDB() throws SQLiteException {
        try {
            db = dbMood.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbMood.getReadableDatabase();
        }
    }

    /**
     * close the database
     */
    public void closeDB() {
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        openDB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("moodSave => " + actualMoodScreen);
        System.out.println("commentaire => " + commentary);
        closeDB();
    }

    //***************** Insert a new line **********************************************************************************************************************************************************************/

    private long insertRecord(ContentValues contentValues) {
        // Assign the values for each column.
        contentValues.put(DBMood.Constants.KEY_COL_MOOD, actualMoodScreen);
        contentValues.put(DBMood.Constants.KEY_COL_COMMENT, commentary);
        contentValues.put(DBMood.Constants.KEY_COL_DATE, "22-01-2018");

        long rowId = db.insert(DBMood.Constants.MY_TABLE, null, contentValues);

        if (rowId == -1) {
            Toast.makeText(this, "Erreur lors de l'insertion de la ligne dans la base de données !",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "La ligne à bien été créée dans la base de données !",
                    Toast.LENGTH_LONG).show();
        }
        return rowId;
    }

    //***************** Delete a line ********************************************************************************************************************************************************************/

    private void deleteRecord(long rowId) {
        rowId = db.delete(DBMood.Constants.MY_TABLE,
                DBMood.Constants.KEY_COL_ID + "=" + rowId, null);
        if (rowId == -1) {
            Toast.makeText(this, "Erreur lors de la suppression !",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "La suppression de la ligne a bien été effectuée !", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
