package com.example.birddance;

import static com.example.birddance.MainActivity.GAME_HEIGHT;
import static com.example.birddance.MainActivity.GAME_WIDTH;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.birddance.Assets.Background;
import com.example.birddance.Assets.RandomPictures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends View {
    //important Game Variables
    //Game Loop variables
    private Handler handler;
    private Runnable gameLoop;
    private static final long FRAME_DELAY = 10;//for framing millis for frame updates
    public SoundPool soundPool;
    private int duration = 60;
    int second;
    private int lastSecond;
    private int testTimer;
    private int timer = 60;
    Random random = new Random();
    public Rect rect;

    private int level = 1;
    private int randomPictureLevels = 3;

    public boolean  gameEnded = false;

    // for game variables
    private int correct, wrong, randomNumber = random.nextInt(randomPictureLevels) + 1, numberofguesses;
    private int streak, longestStreak;


    Integer[] numbers1 = {1, 2, 3};
    Integer[] numbers2 = {1, 2, 3, 4};
    Integer[] numbers3 = {1, 2, 3, 4, 5};
    private List<Integer> numberList1 = Arrays.asList(numbers1);
    private List<Integer> numberList2 = Arrays.asList(numbers2);
    private List<Integer> numberList3 = Arrays.asList(numbers3);

    private List<Integer> lastNumberList1;
    private List<Integer> lastNumberList2;
    private List<Integer> lastNumberList3;

    int MainPictureRandomizer = random.nextInt(randomPictureLevels) + 1;

    // end of game variables

    // forest Frames
    public Bitmap originalBitmap;
    public Bitmap resizedBitmap;

    private Bitmap resizeBitmap(Bitmap original, int width, int height) {
        return Bitmap.createScaledBitmap(original, width, height, true);
    }

    public ArrayList<Background> forests;
    // resizing

    SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

    // for random pictures LEVEL
    private float mainPictureX = 40 * GAME_WIDTH / 100,
            mainPictureX2 = 70 * GAME_WIDTH / 100,
            mainpictureY = 20 * GAME_HEIGHT / 100,
            mainpictureY2 = 50 * GAME_HEIGHT / 100;

    //TODO Level 1 - 3
    // for picture Choice level 1
    private float onePictureX1 = 18 * GAME_WIDTH / 100,
            onePictureX21 = 34 * GAME_WIDTH / 100,
            onepictureY1 = 65 * GAME_HEIGHT / 100,
            onepictureY21 = 88 * GAME_HEIGHT / 100;
    // for choice 2
    private float twoPictureX1 = 42 * GAME_WIDTH / 100,
            twoPictureX21 = 58 * GAME_WIDTH / 100,
            twopictureY1 = 65 * GAME_HEIGHT / 100,
            twopictureY21 = 88 * GAME_HEIGHT / 100;
    // for choice 3
    private float threePictureX1 = 64 * GAME_WIDTH / 100,
            threePictureX21 = 82 * GAME_WIDTH / 100,
            threepictureY1 = 65 * GAME_HEIGHT / 100,
            threepictureY21 = 88 * GAME_HEIGHT / 100;


    // ideal size 10 in width 20 in height

    // for picture Choice level 2
    private float onePictureX = 10 * GAME_WIDTH / 100,
            onePictureX2 = 28 * GAME_WIDTH / 100,
            onepictureY = 65 * GAME_HEIGHT / 100,
            onepictureY2 = 88 * GAME_HEIGHT / 100;
    // for choice 2
    private float twoPictureX = 30 * GAME_WIDTH / 100,
            twoPictureX2 = 48 * GAME_WIDTH / 100,
            twopictureY = 65 * GAME_HEIGHT / 100,
            twopictureY2 = 88 * GAME_HEIGHT / 100;
    // for choice 3
    private float threePictureX = 52 * GAME_WIDTH / 100,
            threePictureX2 = 70 * GAME_WIDTH / 100,
            threepictureY = 65 * GAME_HEIGHT / 100,
            threepictureY2 = 88 * GAME_HEIGHT / 100;
    // for choice 4
    private float fourPictureX = 72 * GAME_WIDTH / 100,
            fourPictureX2 = 90 * GAME_WIDTH / 100,
            fourpictureY = 65 * GAME_HEIGHT / 100,
            fourpictureY2 = 88 * GAME_HEIGHT / 100;


    // for picture Choice level 3
    private float onePictureX3 = 5 * GAME_WIDTH / 100,
            onePictureX23 = 21 * GAME_WIDTH / 100,
            onepictureY3 = 65 * GAME_HEIGHT / 100,
            onepictureY23 = 88 * GAME_HEIGHT / 100;
    // for choice 2
    private float twoPictureX3 = 23 * GAME_WIDTH / 100,
            twoPictureX23 = 39 * GAME_WIDTH / 100,
            twopictureY3 = 65 * GAME_HEIGHT / 100,
            twopictureY23 = 88 * GAME_HEIGHT / 100;
    // for choice 3
    private float threePictureX3 = 41 * GAME_WIDTH / 100,
            threePictureX23 = 57 * GAME_WIDTH / 100,
            threepictureY3 = 65 * GAME_HEIGHT / 100,
            threepictureY23 = 88 * GAME_HEIGHT / 100;
    // for choice 4
    private float fourPictureX3 = 59 * GAME_WIDTH / 100,
            fourPictureX23 = 75 * GAME_WIDTH / 100,
            fourpictureY3 = 65 * GAME_HEIGHT / 100,
            fourpictureY23 = 88 * GAME_HEIGHT / 100;
    // for choice 4
    private float fivePictureX3 = 77 * GAME_WIDTH / 100,
            fivePictureX23 = 93 * GAME_WIDTH / 100,
            fivepictureY3 = 65 * GAME_HEIGHT / 100,
            fivepictureY23 = 88 * GAME_HEIGHT / 100;


    private Bitmap myImage;
    private Bitmap dialogBox;

//
//    private float mainPicWidth = 40 * GAME_WIDTH/100, mainPicHeight = 30 * GAME_HEIGHT/100,
//            touchPicWidth = 25 * GAME_WIDTH/100, touchPicHeight = 20 * GAME_HEIGHT/100;
//    end of choices level 2


    boolean draw = true;
    boolean startTheTimer = false;
    boolean afterStartingTimer = false;

//    lastSecond = (int) (SystemClock.elapsedRealtime() / 1000);


    //start here
    // start of paint variables
    public Paint scorePaint;
    public Paint timerPaint;
    public ArrayList<RandomPictures> randy;
    public RandomPictures rp;


    public Game(Context context) {
        super(context);
        handler = new Handler();
        gameLoop = this::invalidate;

        initMusic(context);


        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(60);
        scorePaint.setStyle(Paint.Style.FILL);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        timerPaint = new Paint();
        timerPaint.setColor(Color.GREEN);
        timerPaint.setStyle(Paint.Style.FILL);

        rect = new Rect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Load the original bitmap from resources
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b1);
        // Resize the bitmap to a new width and height (e.g., 800x600)
        resizedBitmap = resizeBitmap(originalBitmap, GAME_WIDTH, GAME_HEIGHT);

        forests = new ArrayList<>();
        initializeForest(context);

        randy = new ArrayList<>();
        initializeRandy(context);

        rp = new RandomPictures(context);

        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        int incroctSound = soundPool.load(context, R.raw.bird_flap, 1);
        int correctSound = soundPool.load(context, R.raw.bird, 1);

        myImage = BitmapFactory.decodeResource(getResources(), R.drawable.newbackground);
        dialogBox = BitmapFactory.decodeResource(getResources(), R.drawable.birddancedialog);

    }

    private void initializeRandy(Context context) {
        for (int i = 0; i < 1; i++) {
            randy.add(new RandomPictures(context));
        }
    }

    private void initializeForest(Context context) {
        for (int i = 0; i < 1; i++) {
            forests.add(new Background(context));
        }
    }

    boolean drawMainRandom = true;

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        startMusic();


//      TODO for end game
        if (timer > 100) {
            synchronized (forests) {
                for (Background forest : forests
                ) {
                    Bitmap map = forest.getBitmap();
                    canvas.drawBitmap(resizeBitmap(map, GAME_WIDTH, GAME_HEIGHT), 0, 0, null);
                    forest.forestFrame = (forest.forestFrame + 1) % 20;
                    if (forest.forestFrame > 20) {
                        forest.resetForestFrames();
                    }
                }
            }
        }

        canvas.drawBitmap(resizeBitmap(myImage, GAME_WIDTH, GAME_HEIGHT), 0, 0, null);
        canvas.drawBitmap(resizeBitmap(dialogBox, GAME_WIDTH, 20 * GAME_HEIGHT / 100), 0, 0, null);


        synchronized (randy) {
            for (RandomPictures random : randy
            ) {
                Bitmap unitPic = random.getBitmap();
                canvas.drawBitmap(resizeBitmap(unitPic, 400, 400),
                        GAME_WIDTH / 2 - unitPic.getWidth() / 3, 26 * GAME_HEIGHT / 100, null);
                random.randomFrame = randomNumber - 1;
            }
        }
        if (draw == true) {
//        TODO LEVEL 1 change location depending on the width and height
            if (level == 1) {
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers1[0]), (int) (onePictureX21 - onePictureX1), (int) (onepictureY21 - onepictureY1)), onePictureX1, onepictureY1, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers1[1]), (int) (twoPictureX21 - twoPictureX1), (int) (twopictureY21 - twopictureY1)), twoPictureX1, twopictureY1, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers1[2]), (int) (threePictureX21 - threePictureX1), (int) (threepictureY21 - threepictureY1)), threePictureX1, threepictureY1, null);
            } else if (level == 2) {
//        TODO LEVEL 2 COMPLETE
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers2[0]), (int) (onePictureX2 - onePictureX), (int) (onepictureY2 - onepictureY)), onePictureX, onepictureY, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers2[1]), (int) (twoPictureX2 - twoPictureX), (int) (twopictureY2 - twopictureY)), twoPictureX, twopictureY, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers2[2]), (int) (threePictureX2 - threePictureX), (int) (threepictureY2 - threepictureY)), threePictureX, threepictureY, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers2[3]), (int) (fourPictureX2 - fourPictureX), (int) (fourpictureY2 - fourpictureY)), fourPictureX, fourpictureY, null);
            } else if (level == 3) {
//        TODO LEVEL 3
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers3[0]), (int) (onePictureX23 - onePictureX3), (int) (onepictureY23 - onepictureY3)), onePictureX3, onepictureY3, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers3[1]), (int) (twoPictureX23 - twoPictureX3), (int) (twopictureY23 - twopictureY3)), twoPictureX3, twopictureY3, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers3[2]), (int) (threePictureX23 - threePictureX3), (int) (threepictureY23 - threepictureY3)), threePictureX3, threepictureY3, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers3[3]), (int) (fourPictureX23 - fourPictureX3), (int) (fourpictureY23 - fourpictureY3)), fourPictureX3, fourpictureY3, null);
                canvas.drawBitmap(resizeBitmap(rp.getperBitmap(numbers3[4]), (int) (fivePictureX23 - fivePictureX3), (int) (fivepictureY23 - fivepictureY3)), fivePictureX3, fivepictureY3, null);
            }
        }

//        canvas.drawText("Random Number : " + randomNumber, mainPictureX, mainpictureY, scorePaint);
//        canvas.drawText(numbers[0].toString(), onePictureX, onepictureY, scorePaint);
//        canvas.drawText(numbers[1].toString(), twoPictureX, twopictureY, scorePaint);
//        canvas.drawText(numbers[2].toString(), threePictureX, threepictureY, scorePaint);
//        canvas.drawText(numbers[3].toString(), fourPictureX, fourpictureY, scorePaint);

//working timer without affecting handler
        second = (int) (SystemClock.elapsedRealtime() / 1000);
        if (startTheTimer == true) {

            if(afterStartingTimer == false) {
                afterStartingTimer = true;
                lastSecond = (int) (SystemClock.elapsedRealtime() / 1000);
            }

            testTimer = (int) (second - lastSecond);
            if(gameEnded == true){
                timer = 0;
            }else {
                timer = duration - testTimer;
            }
        }
        if (timer == 1000) {
            randomPictureLevels = 3;
            level = 1;
        }
        if (timer == 60) {
            randomPictureLevels = 4;
            level = 2;
        }
        if (timer == 30) {
            randomPictureLevels = 5;
            level = 3;
        }

        if (timer <= 0 && !gameEnded) {
            gameEnded = true;
            endTheGame();
        }

        canvas.drawText("Time Left ", 41 * GAME_WIDTH / 100, 100, scorePaint);
        canvas.drawText( timer + " Second(s)", 40 * GAME_WIDTH / 100, 190, scorePaint);

        canvas.drawText("Current Streak : " + streak, 3 * GAME_WIDTH / 100, 100, scorePaint);
        canvas.drawText("Longest Streak : " + longestStreak, 3 * GAME_WIDTH / 100, 190, scorePaint);
        canvas.drawText("Correct : " + correct, 80 * GAME_WIDTH / 100, 100, scorePaint);
        canvas.drawText("Wrong : " + wrong, 81 * GAME_WIDTH / 100, 190, scorePaint);
//        canvas.drawText("Number of Guesses : " + numberofguesses, 40, 560, scorePaint);
        //      TODO spawn moving units after correct guess
        handler.postDelayed(gameLoop, FRAME_DELAY);
    }

    private void endTheGame() {
        stopMusic();
        handler.removeCallbacks(gameLoop); // important for no laggs
        Intent intent = new Intent(getContext(), LoadingScreenEnd.class);
        intent.putExtra("correct", correct);
        intent.putExtra("wrong", wrong);
        intent.putExtra("streak", streak);
        intent.putExtra("longestStreak", longestStreak);
        intent.putExtra("numberofguesses", numberofguesses);
        getContext().startActivity(intent);
        ((Activity) getContext()).finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touch = false;
        startTheTimer = true;

        int pointerCount = event.getPointerCount();

        if (pointerCount == 1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float touchX = event.getX();
                float touchY = event.getY();

                int choice = 0;


//            TODO level 1 and level 3
                if (level == 1) {
                    if (touchX > onePictureX1 && touchX < onePictureX21 &&
                            touchY > onepictureY1 && touchY < onepictureY21) {

                        choice = numbers2[0];
                        touch = true;
                    } else if (touchX > twoPictureX1 && touchX < twoPictureX21 &&
                            touchY > twopictureY1 && touchY < twopictureY21) {

                        choice = numbers2[1];
                        touch = true;
                    } else if (touchX > threePictureX1 && touchX < threePictureX21 &&
                            touchY > threepictureY1 && touchY < threepictureY21) {

                        choice = numbers2[2];
                        touch = true;
                    }

                    if (touch == true) {
                        if (choice == randomNumber) {
                            correct += 1;
                            streak += 1;
                            if(streak > longestStreak) {
                                longestStreak = streak;
                            }
                            playSound(1);
                        } else if (choice != randomNumber) {
                            wrong += 1;
                            streak = 0;
                            playSound(2);
                        }
                        numberofguesses += 1;

                        // end of working codes
//                    TODO level 1 and 2
                        lastNumberList1 = new ArrayList<>(numberList1);
                        Collections.shuffle(numberList1);
                        if (lastNumberList1.equals(numberList1)) {
                            draw = false;
                            while (lastNumberList1.equals(numberList1)) {
                                Collections.shuffle(numberList1, random);
                            }
                            draw = true;
                        } else {
                            draw = true;
                        }

                    }
                }// TODO end of level 2 touch

//            Workring level 2
                if (level == 2) {
                    if (touchX > onePictureX && touchX < onePictureX2 &&
                            touchY > onepictureY && touchY < onepictureY2) {

                        choice = numbers2[0];
                        touch = true;
                    } else if (touchX > twoPictureX && touchX < twoPictureX2 &&
                            touchY > twopictureY && touchY < twopictureY2) {

                        choice = numbers2[1];
                        touch = true;
                    } else if (touchX > threePictureX && touchX < threePictureX2 &&
                            touchY > threepictureY && touchY < threepictureY2) {

                        choice = numbers2[2];
                        touch = true;
                    } else if (touchX > fourPictureX && touchX < fourPictureX2 &&
                            touchY > fourpictureY && touchY < fourpictureY2) {

                        choice = numbers2[3];
                        touch = true;
                    }

                    if (touch == true) {
                        if (choice == randomNumber) {
                            correct += 1;
                            streak += 1;
                            if(streak > longestStreak) {
                                longestStreak = streak;
                            }
                            playSound(1);
                        } else if (choice != randomNumber) {
                            wrong += 1;
                            streak = 0;
                            playSound(2);
                        }
                        numberofguesses += 1;

                        // end of working codes

//                    TODO level 1 and 2
                        lastNumberList2 = new ArrayList<>(numberList2);
                        Collections.shuffle(numberList2);
                        if (lastNumberList2.equals(numberList2)) {
                            draw = false;
                            while (lastNumberList2.equals(numberList2)) {
                                Collections.shuffle(numberList2, random);
                            }
                            draw = true;
                        } else {
                            draw = true;
                        }
                    }
                }// TODO end of level 2 touch

                if (level == 3) {
                    if (touchX > onePictureX3 && touchX < onePictureX23 &&
                            touchY > onepictureY3 && touchY < onepictureY23) {

                        choice = numbers3[0];
                        touch = true;
                    } else if (touchX > twoPictureX3 && touchX < twoPictureX23 &&
                            touchY > twopictureY3 && touchY < twopictureY23) {

                        choice = numbers3[1];
                        touch = true;
                    } else if (touchX > threePictureX3 && touchX < threePictureX23 &&
                            touchY > threepictureY3 && touchY < threepictureY23) {

                        choice = numbers3[2];
                        touch = true;
                    } else if (touchX > fourPictureX3 && touchX < fourPictureX23 &&
                            touchY > fourpictureY3 && touchY < fourpictureY23) {

                        choice = numbers3[3];
                        touch = true;
                    } else if (touchX > fivePictureX3 && touchX < fivePictureX23 &&
                            touchY > fivepictureY3 && touchY < fivepictureY23) {

                        choice = numbers3[4];
                        touch = true;
                    }

                    if (touch == true) {

                        if (choice == randomNumber) {
                            correct += 1;
                            streak += 1;
                            if(streak > longestStreak) {
                                longestStreak = streak;
                            }
                            playSound(1);
                        } else if (choice != randomNumber) {
                            wrong += 1;
                            streak = 0;
                            playSound(2);
                        }
                        numberofguesses += 1;

                        // end of working codes

//                    TODO level 1 and 2
                        lastNumberList3 = new ArrayList<>(numberList3);
                        Collections.shuffle(numberList3);
                        if (lastNumberList3.equals(numberList3)) {
                            draw = false;
                            while (lastNumberList3.equals(numberList3)) {
                                Collections.shuffle(numberList3, random);
                            }
                            draw = true;
                        } else {
                            draw = true;
                        }
                    }
                }// TODO end of level 2 touch
            }
        }

//        if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP){
//
//        }

        if (touch == true) {
            int lastRandomNumber = randomNumber;
            if (lastRandomNumber == randomNumber) {
                drawMainRandom = false;
                while (lastRandomNumber == randomNumber) {
                    randomNumber = random.nextInt(randomPictureLevels) + 1;
                }
                drawMainRandom = true;
            } else {
                drawMainRandom = true;
            }
        }

        return super.onTouchEvent(event);
    }

    private void playSound(int soundId) {
        if (soundId != 0) {
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }
    }


    private MediaPlayer mediaPlayer;



    private void initMusic(Context context) {
        // Initialize MediaPlayer with a music file from res/raw
        mediaPlayer = MediaPlayer.create(context, R.raw.gamesong);
    }

    // Start Music
    public void startMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    // Stop Music
    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
