package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RulesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_screen);
        getSupportActionBar().hide();
    }
}