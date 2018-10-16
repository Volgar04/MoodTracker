package com.nicolappli.moodtracker;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {
    private ArrayList<MoodItem> mMoodList;

    public static class MoodViewHolder extends RecyclerView.ViewHolder{
        public ImageButton mImageButton;
        public TextView mDate;
        public RelativeLayout mRelativeLayout;
        public CardView mCardView;


        //itemView is the view corresponding to one cell
        public MoodViewHolder(View itemView) {
            super(itemView);
            mImageButton=itemView.findViewById(R.id.imageButton);
            mDate=itemView.findViewById(R.id.dateItem);
            mRelativeLayout=itemView.findViewById(R.id.relativeMoodItem);
            mCardView=itemView.findViewById(R.id.cardView);
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
    public void onBindViewHolder(@NonNull final MoodViewHolder holder, final int position) {
        MoodItem currentItem = mMoodList.get(position);
        //holder.mRelativeLayout.setBackgroundResource(mMoodList.get(position).getColor());
        holder.mDate.setText(currentItem.getDate());

        if(mMoodList.get(position).getCommentary().equals("  ")){
            holder.mImageButton.setVisibility(View.INVISIBLE);
        }else{
            holder.mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(, mMoodList.get(position).getCommentary(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        if(mMoodList.get(position).getMood() == 0){
            holder.mCardView.setBackgroundResource(R.color.banana_yellow);
            holder.mRelativeLayout.setBackgroundResource(R.color.banana_yellow);
        }else if(mMoodList.get(position).getMood() == 1){
            holder.mCardView.setBackgroundResource(R.color.light_sage);
            holder.mRelativeLayout.setBackgroundResource(R.color.light_sage);
        }else if(mMoodList.get(position).getMood() == 2){
            holder.mCardView.setBackgroundResource(R.color.cornflower_blue_65);
            holder.mRelativeLayout.setBackgroundResource(R.color.cornflower_blue_65);
        }else if(mMoodList.get(position).getMood() == 3){
            holder.mCardView.setBackgroundResource(R.color.warm_grey);
            holder.mRelativeLayout.setBackgroundResource(R.color.warm_grey);
        }else if(mMoodList.get(position).getMood() == 4){
            holder.mCardView.setBackgroundResource(R.color.faded_red);
            holder.mRelativeLayout.setBackgroundResource(R.color.faded_red);
        }
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }
}
