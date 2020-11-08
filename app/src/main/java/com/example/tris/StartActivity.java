package com.example.tris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {


    Button btnMultiplayer;
    Button btnSingleplayer;
    ImageView imageView;

    Intent intentMultiplayer;
    Intent intentSingleplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        intentMultiplayer = new Intent(StartActivity.this, MultiplayerActivity.class);
        intentSingleplayer = new Intent(StartActivity.this, Singleplayer.class);
        btnMultiplayer = findViewById(R.id.btn_multiPlayer);
        btnSingleplayer = findViewById(R.id.btn_singlePlayer);
        imageView = findViewById(R.id.imageView2);
        btnMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMultiplayer);
            }
        });

        btnSingleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSingleplayer);
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(270).withEndAction(this).setDuration(2000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        imageView.animate().rotationBy(0).withEndAction(runnable).setDuration(0).setInterpolator(new LinearInterpolator()).start();
    }
}
