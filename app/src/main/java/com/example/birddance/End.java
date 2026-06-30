package com.example.birddance;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class End extends Activity {
    DatabaseReference databaseReference;
    Button addDatabase;

    MediaPlayer mediaPlayer;
    String idNumber,name;
    int score;

    private List<User> userList;
    private FirebaseFirestore db;

    int correct;
    int wrong;
    int streak ;
    int longestStreak;
    int numberofguesses;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        FirebaseApp.initializeApp(getApplicationContext());


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        mediaPlayer = MediaPlayer.create(this, R.raw.edibonebonfestival);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
//        TODO FOR SCORES
        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);
        streak = getIntent().getIntExtra("streak", 0);
        longestStreak = getIntent().getIntExtra("longestStreak", 0);
        numberofguesses = getIntent().getIntExtra("numberofguesses", 0);
        TextView correct1,wrong1,longestStreak1,numberOfGuesses1,finalsScore1;
        correct1 = findViewById(R.id.correct);
        wrong1 = findViewById(R.id.wrong);
        longestStreak1 = findViewById(R.id.longestStreak);
        numberOfGuesses1 = findViewById(R.id.numberOfGuesses);
        finalsScore1 = findViewById(R.id.finalScore);
        correct1.setText("Correct : " + correct);
        wrong1.setText("Wrong : " + wrong);
        longestStreak1.setText("Longest Streak : " + longestStreak);
        numberOfGuesses1.setText("Total Guesses : " + numberofguesses);
        String finalScore = String.valueOf((correct * 10) - (wrong * 5) + (longestStreak * 2) + (100 - (numberofguesses / 2)));
        finalsScore1.setText("Score Grade : " + finalScore);
        score = (correct * 10) - (wrong * 5) + (longestStreak * 2) + (100 - (numberofguesses / 2));
//




    }


    public void goMainMenu (View view){
        EditText textName = findViewById(R.id.userName);
        EditText textId = findViewById(R.id.userID);

        String idNumber = textId.getText().toString().trim();
        String name = textName.getText().toString().trim();

        if (idNumber.isEmpty() || name.isEmpty()) {
            Intent intent = new Intent(End.this, returnMain.class);
            startActivity(intent);
            finishAffinity();
        }else{
        db = FirebaseFirestore.getInstance();

        // Using Map instead of User object
        Map<String, Object> user = new HashMap<>();
        user.put("idNumber", idNumber);
        user.put("name", name);
        user.put("score", score);

        db.collection("Game1").document(idNumber)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "User added!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "User successfully added to Firestore");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error adding user", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error adding user", e);
                });

        mediaPlayer.stop();
        Intent intent = new Intent(End.this, returnMain.class);
        startActivity(intent);
        finishAffinity();

        } 


    }

    public void exit(View view){
        finish();
    }
}
