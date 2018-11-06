package com.nicolappli.moodtracker;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    static class MoodViewHolder extends RecyclerView.ViewHolder{
        ImageButton mImageButton;
        TextView mDate;
        ConstraintLayout mConstraintLayout;
        CardView mCardView;

        //itemView is the view corresponding to one cell
        MoodViewHolder(View itemView) {
            super(itemView);
            mImageButton=itemView.findViewById(R.id.imageButton);
            mDate=itemView.findViewById(R.id.dateItem);
            mConstraintLayout=itemView.findViewById(R.id.constraintLayout);
            mCardView=itemView.findViewById(R.id.cardView);
        }
    }

    /**
     * MoodAdapter constructor's taking in entrance a list
     * @param moodList = the list which contains
     */
    MoodAdapter(ArrayList<MoodItem> moodList){
        mMoodList=moodList;
    }

    /**
     * Allows to create viewHolder
     * @return the view
     */
    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item, parent, false);
        v.getLayoutParams().height = parent.getHeight()/7;
        return new MoodViewHolder(v);
    }

    /**
     * the function to fill the cell
     * @param holder = update the recycler view contents
     * @param position = the position of the current item
     */
    @Override
    public void onBindViewHolder(@NonNull final MoodViewHolder holder, final int position) {
        final MoodItem currentItem = mMoodList.get(position);

        //Show the commentary if the user wrote one
        if(!currentItem.getCommentary().isEmpty()){
            holder.mImageButton.setVisibility(View.VISIBLE);

            holder.mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(currentItem.getContext(),currentItem.getCommentary(),Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            holder.mImageButton.setVisibility(View.INVISIBLE);
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

    /**
     * display the good color and the good size of the current item according to the mood in the database
     * @param currentItem = the actual item
     * @param holder = update the recycler view contents
     */
    private void displayColor(MoodItem currentItem, final MoodViewHolder holder){
        ConstraintSet set = new ConstraintSet();
        set.clone(holder.mConstraintLayout);

        if(currentItem.getMood() == 0){
            holder.mCardView.setBackgroundResource(R.color.banana_yellow);
            set.connect(holder.mCardView.getId(), ConstraintSet.END, R.id.guideline100, ConstraintSet.END,0);
        }else if(currentItem.getMood() == 1){
            holder.mCardView.setBackgroundResource(R.color.light_sage);
            set.connect(holder.mCardView.getId(), ConstraintSet.END, R.id.guideline84, ConstraintSet.END,0);
        }else if(currentItem.getMood() == 2){
            holder.mCardView.setBackgroundResource(R.color.cornflower_blue_65);
            set.connect(holder.mCardView.getId(), ConstraintSet.END, R.id.guideline68, ConstraintSet.END,0);
        }else if(currentItem.getMood() == 3){
            holder.mCardView.setBackgroundResource(R.color.warm_grey);
            set.connect(holder.mCardView.getId(), ConstraintSet.END, R.id.guideline52, ConstraintSet.END,0);
        }else if(currentItem.getMood() == 4){
            holder.mCardView.setBackgroundResource(R.color.faded_red);
            set.connect(holder.mCardView.getId(), ConstraintSet.END, R.id.guideline36, ConstraintSet.END,0);
        }
        set.applyTo(holder.mConstraintLayout);
    }

    /**
     * display the date
     * @param currentItem = the actual item
     * @param holder = update the recycler view contents
     */
    private void showDate(MoodItem currentItem, final MoodViewHolder holder){
        String registerDate = currentItem.getDate();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", new Locale("FR"));

        try {
            Date dateStart =format.parse(registerDate);
            Date dateEnd = format.parse(currentDate);
            long numberOfDays = getUnitBetweenDates(dateEnd,dateStart);
            String displayNumberOfDay;
            if(numberOfDays==1){
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

    /**
     * Method to have difference between two dates
     * @param date = current date
     * @param dayDate = register date
     * @return = the difference (int)
     */
    private static long getUnitBetweenDates(Date date, Date dayDate) {
        TimeUnit days = TimeUnit.DAYS;
        long timeDiff = date.getTime() - dayDate.getTime();
        return days.convert(timeDiff, TimeUnit.MILLISECONDS);
    }
}