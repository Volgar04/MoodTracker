package com.nicolappli.moodtracker;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    //MoodAdapter constructor's taking in entrance a list
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
        final MoodItem currentItem = mMoodList.get(position);

        //Show the commentary if the user wrote one
        if(currentItem.getCommentary().equals(" ")){
            holder.mImageButton.setVisibility(View.INVISIBLE);
        }else{
            holder.mImageButton.setVisibility(View.VISIBLE);

            holder.mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(currentItem.getContext(),currentItem.getCommentary(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        //Show the good colors
        displayColor(currentItem, holder);
        //show the date
        showDate(currentItem,holder);
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }

    private void displayColor(MoodItem currentItem, final MoodViewHolder holder){
        if(currentItem.getMood() == 0){
            holder.mRelativeLayout.setBackgroundResource(R.color.banana_yellow);
        }else if(currentItem.getMood() == 1){
            holder.mRelativeLayout.setBackgroundResource(R.color.light_sage);
        }else if(currentItem.getMood() == 2){
            holder.mRelativeLayout.setBackgroundResource(R.color.cornflower_blue_65);
        }else if(currentItem.getMood() == 3){
            holder.mRelativeLayout.setBackgroundResource(R.color.warm_grey);
        }else if(currentItem.getMood() == 4){
            holder.mRelativeLayout.setBackgroundResource(R.color.faded_red);
        }
    }

    private void showDate(MoodItem currentItem, final MoodViewHolder holder){
        String registerDate = currentItem.getDate();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date dateStart =format.parse(registerDate);
            Date dateEnd = format.parse(currentDate);
            getDaysDifference(dateStart,dateEnd);
            long numberOfDays = getUnitBetweenDates(dateStart,dateEnd,TimeUnit.DAYS);
            String displayNumberOfDay;
            if(numberOfDays==0){
                displayNumberOfDay = "Aujourd'hui";
            }
            else if(numberOfDays==1){
                displayNumberOfDay = "Hier";
            }
            else if(numberOfDays==2){
                displayNumberOfDay= "Avant hier";
            }
            else{
                displayNumberOfDay = "Il y a "+numberOfDays+" jours";
            }
            holder.mDate.setText(displayNumberOfDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static long getUnitBetweenDates(Date date, Date dayDate, TimeUnit days) {
        long timeDiff = date.getTime() - dayDate.getTime();
        return days.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    private static void getDaysDifference(Date fromDate, Date toDate)
    {
        toDate.getTime();
        fromDate.getTime();
    }
}
