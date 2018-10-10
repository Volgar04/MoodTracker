package com.nicolappli.moodtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {
    private ArrayList<MoodItem> mMoodList;

    public static class MoodViewHolder extends RecyclerView.ViewHolder{
        public ImageButton mImageButton;
        public TextView mDate;
        public RelativeLayout mRelativeLayout;

        //itemView is the view corresponding to one cell
        public MoodViewHolder(View itemView) {
            super(itemView);
            mImageButton=itemView.findViewById(R.id.imageButton);
            mDate=itemView.findViewById(R.id.dateItem);
            mRelativeLayout=itemView.findViewById(R.id.relativeMoodItem);
        }
    }

    //a constructor taking in entrance a list
    public MoodAdapter(ArrayList<MoodItem> moodList){
        mMoodList=moodList;
    }

    //Allows to create viewHolder
    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item, parent, false);
        MoodViewHolder mvh = new MoodViewHolder(v);
        return mvh;
    }

    //the function to fill the cell
    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        MoodItem currentItem = mMoodList.get(position);
        //holder.mRelativeLayout.setBackgroundResource(mMoodList.get(position).getColor());
        holder.mDate.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }
}
