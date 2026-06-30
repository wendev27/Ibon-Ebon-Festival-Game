package com.example.birddance;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int GAME_WIDTH, GAME_HEIGHT;

    public boolean language = true;

    private MediaPlayer mediaPlayer;

    private boolean isImage1 = true; // Track which image is displayed

    ImageView languageButton,languageButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Initialize and play background music
        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.newbackgroundmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);

        GAME_WIDTH = dm.widthPixels;
        GAME_HEIGHT = dm.heightPixels;

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


//        languageButton = findViewById(R.id.ib_LanguageEnglish);
//        languageButton2 = findViewById(R.id.ib_LanguageFilipino);
//
//
//        languageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                languageButton.setVisibility(View.GONE);
//                language = false;
//
//                languageButton2 = findViewById(R.id.ib_LanguageEnglish);
//                languageButton2.setVisibility(View.VISIBLE);
//            }
//        });
//
//        languageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    public void changeLanguageToFilipino(View view){

         languageButton = findViewById(R.id.ib_LanguageEnglish);
         languageButton.setVisibility(View.GONE);
         language = false;

         languageButton2 = findViewById(R.id.ib_LanguageFilipino);
         languageButton2.setVisibility(View.VISIBLE);


    }

    public void changeLanguageToEnglish(View view){

        languageButton2 = findViewById(R.id.ib_LanguageFilipino);
        languageButton2.setVisibility(View.GONE);
        language = true;

        languageButton = findViewById(R.id.ib_LanguageEnglish);
        languageButton.setVisibility(View.VISIBLE);


    }

    public void startGameActivity(View view){

        if(language == true) {

            ImageView imageView = findViewById(R.id.ib_start);
            mediaPlayer.stop();
            Intent intent = new Intent(this, ComicsLoader.class);
            startActivity(intent);
            finish();
        }else{
            ImageView imageView = findViewById(R.id.ib_start);
            mediaPlayer.stop();
            Intent intent = new Intent(this, ComicsLoader2.class);
            startActivity(intent);
            finish();
        }
//        Intent intent = new Intent(this, GameActivity.class);
//        startActivity(intent);
//        finish();

    }
}