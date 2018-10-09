package com.nicolappli.moodtracker;

public class MoodItem {
    private String mDate;
    private int mColor;
    private int mSize;

    public MoodItem(String date, int color, int size){
        mDate=date;
        mColor=color;
        mSize=size;
    }

    public String getDate(){
        return mDate;
    }

    public int getColor(){
        return mColor;
    }

    public int getSize(){
        return mSize;
    }
}
