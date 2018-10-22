package com.nicolappli.moodtracker;

import android.content.Context;

public class MoodItem {
    private String mDate;
    private String mCommentary;
    private int mMood;
    private Context mContext;

    public MoodItem(Context context, String date, int mood, String commentary){
        mDate=date;
        mMood=mood;
        mCommentary=commentary;
        this.mContext=context;
    }

    public String getDate(){
        return mDate;
    }

    public int getMood(){
        return mMood;
    }

    public String getCommentary(){
        return mCommentary;
    }

    public Context getContext(){
        return mContext;
    }
}
