package com.nicolappli.moodtracker;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;
    /**RelativeLayout principalScreen;
    ImageView imageSmiley;
    ImageButton imageComment;
    ImageButton imageHistory;
    int actualScreen = 1;**/

    public OnSwipeTouchListener (Context ctx){
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
    public void onSwipeRight() {
    }
    public void onSwipeLeft() {
    }
    public void onSwipeTop() {
    }
    public void onSwipeBottom() {
    }
    /**public int onSwipeScreen(int numberScreen){
        if (numberScreen == 0) {
            principalScreen.setBackgroundResource(R.color.banana_yellow);
            imageSmiley.setImageResource(R.drawable.smiley_super_happy);
            imageComment.setBackgroundResource(R.color.banana_yellow);
            imageHistory.setBackgroundResource(R.color.banana_yellow);
        } else if (numberScreen == 1) {
            principalScreen.setBackgroundResource(R.color.light_sage);
            imageSmiley.setImageResource(R.drawable.smiley_happy);
            imageComment.setBackgroundResource(R.color.light_sage);
            imageHistory.setBackgroundResource(R.color.light_sage);
        } else if (numberScreen == 2) {
            principalScreen.setBackgroundResource(R.color.cornflower_blue_65);
            imageSmiley.setImageResource(R.drawable.smiley_normal);
            imageComment.setBackgroundResource(R.color.cornflower_blue_65);
            imageHistory.setBackgroundResource(R.color.cornflower_blue_65);
        } else if (numberScreen == 3) {
            principalScreen.setBackgroundResource(R.color.warm_grey);
            imageSmiley.setImageResource(R.drawable.smiley_disappointed);
            imageComment.setBackgroundResource(R.color.warm_grey);
            imageHistory.setBackgroundResource(R.color.warm_grey);
        } else if (numberScreen == 4) {
            principalScreen.setBackgroundResource(R.color.faded_red);
            imageSmiley.setImageResource(R.drawable.smiley_sad);
            imageComment.setBackgroundResource(R.color.faded_red);
            imageHistory.setBackgroundResource(R.color.faded_red);
        } else if (numberScreen==5) {
            numberScreen=4;
            principalScreen.setBackgroundResource(R.color.faded_red);
            imageSmiley.setImageResource(R.drawable.smiley_sad);
            imageComment.setBackgroundResource(R.color.faded_red);
            imageHistory.setBackgroundResource(R.color.faded_red);
        }else{
            numberScreen=0;
            principalScreen.setBackgroundResource(R.color.banana_yellow);
            imageSmiley.setImageResource(R.drawable.smiley_super_happy);
            imageComment.setBackgroundResource(R.color.banana_yellow);
            imageHistory.setBackgroundResource(R.color.banana_yellow);
        }
        return numberScreen;
    }**/
}
