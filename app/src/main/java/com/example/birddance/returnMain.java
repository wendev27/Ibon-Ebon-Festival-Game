package com.example.birddance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class returnMain extends AppCompatActivity {
    DatabaseReference databaseReference;
    Handler handler = new Handler();



    boolean proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_screen_end);

        proceed = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(proceed == true) {
                    proceed = false;
                    startActivity(new Intent(returnMain.this, MainActivity.class));
                    finish();
                }
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}