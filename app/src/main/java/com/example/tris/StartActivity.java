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
                intentMultiplayer.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intentMultiplayer,0);
            }
        });

        btnSingleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSingleplayer.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intentSingleplayer,0);
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

    @Override
    public void onBackPressed() {
        //finish();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        this.startActivity(intent);
    }

}
