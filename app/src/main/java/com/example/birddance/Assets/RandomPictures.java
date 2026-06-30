package com.example.birddance.Assets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RandomPictures {

    Bitmap[] randomFrames = new Bitmap[5];

    public int randomFrame;
    public RandomPictures(Context context) {
        initializeForestFrames(context);

        resetRandomFrames();
    }

    private void resetRandomFrames() {
        randomFrame = 0;
    }

    public Bitmap getBitmap(){
        return randomFrames[randomFrame];
    }
    private void initializeForestFrames(Context context) {
        for (int i = 0; i < 5; i++) {
            int resId = context.getResources()
                    .getIdentifier("r" + (i + 1), "drawable",
                            context.getPackageName());
            randomFrames[i] = BitmapFactory.decodeResource(context.getResources(), resId);
        }
    }

    public Bitmap getperBitmap(int frame){
        return randomFrames[frame - 1];
    }

    public int getWidth(){
        return randomFrames[0].getWidth();
    }
    public int getHeight(){
        return randomFrames[0].getHeight();
    }


}
