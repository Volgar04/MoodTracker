package com.nicolappli.moodtracker;

public class MoodItem {
    private String mDate;
    private String mCommentary;
    private int mMood;

    public MoodItem(String date, int mood, String commentary){
        mDate=date;
        mMood=mood;
        mCommentary=commentary;
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
}
