package com.example.birddance;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ComicsLoader extends AppCompatActivity {

    private VideoView videoView;
    private MediaPlayer mediaPlayer;

    boolean proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_loader);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        videoView = findViewById(R.id.videoView);
        // Set video source (Use a local file or online URL)
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.comcscut1); // Example for raw folder video
        mediaPlayer = MediaPlayer.create(this, R.raw.comicslodersound);
        mediaPlayer.start();
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(null);
        // Add media controls (play, pause, seek)
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        // Start playing the video
        videoView.start();


        proceed = true;


        // Dispose video when playback is complete
        videoView.setOnCompletionListener(mp -> {
            videoView.stopPlayback();
            videoView.setVisibility(VideoView.GONE);
            if (proceed == true) {
                mediaPlayer.stop();
                Intent intent = new Intent(this, LoadingScreenGame.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void skip(View view) {
        mediaPlayer.stop();
        proceed = false;
        Intent intent = new Intent(this, LoadingScreenGame.class);
        startActivity(intent);
        finish();
    }


}