package com.example.birddance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingScreenEnd extends AppCompatActivity {

    Handler handler = new Handler();

    boolean proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_screen_end);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);
        streak = getIntent().getIntExtra("streak", 0);
        longestStreak = getIntent().getIntExtra("longestStreak", 0);
        numberofguesses = getIntent().getIntExtra("numberofguesses", 0);


        proceed = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(proceed = true){
                    proceed = false;
                    Intent intent = new Intent(LoadingScreenEnd.this, End.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("streak", streak);
                    intent.putExtra("longestStreak", longestStreak);
                    intent.putExtra("numberofguesses", numberofguesses);
                    startActivity(intent);
                }
                finish();
            }
        }, 2000);
    }
    int correct;
    int wrong;
    int streak ;
    int longestStreak;
    int numberofguesses;
//    @Override
//    protected void onDestroy() {
//
//
//
//
//        handler.removeCallbacksAndMessages(null);
//        super.onDestroy();
//    }
}