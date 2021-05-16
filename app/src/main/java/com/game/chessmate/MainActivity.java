package com.game.chessmate;
// Test commit Comment

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.game.chessmate.GameFiles.BoardView;
import com.game.chessmate.GameFiles.ChessBoard;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}