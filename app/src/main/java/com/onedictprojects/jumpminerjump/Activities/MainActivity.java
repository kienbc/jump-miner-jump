package com.onedictprojects.jumpminerjump.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.onedictprojects.jumpminerjump.View.GameView;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        gameView = new GameView(this);
        gameView.setOnTouchListener(gameView);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }
}