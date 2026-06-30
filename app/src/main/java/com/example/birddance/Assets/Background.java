package com.example.birddance.Assets;

import static com.example.birddance.MainActivity.GAME_HEIGHT;
import static com.example.birddance.MainActivity.GAME_WIDTH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    //store the all the background frames for animation
    Bitmap[] forestFrames = new Bitmap[20];
    // store background position
    public int forestX, forestY;
    public int frameVelocity = 0;
    public int forestFrame;
    public Background(Context context) {
        initializeForestFrames(context);
        resetForestFrames();
    }

    public Bitmap getBitmap(){
        return forestFrames[forestFrame];
    }

    public int getWidth(){
        return forestFrames[0].getWidth() * 100; // all frames have the same width
    }

    public int getHeight(){
        return forestFrames[0].getHeight()*100;
    }

    public void resetForestFrames() {
//        forestX = GameView.dWidth;
//        forestY = GameView.dHeight;
        forestX = GAME_WIDTH;
        forestY = GAME_HEIGHT;
        frameVelocity = 2;
        forestFrame = 0;
    }
    private void initializeForestFrames(Context context) {
        for (int i = 0; i < 20; i++) {
            int resId = context.getResources()
                    .getIdentifier("b" + (i + 1), "drawable",
                            context.getPackageName());
            forestFrames[i] = BitmapFactory.decodeResource(context.getResources(), resId);
        }
    }
}
