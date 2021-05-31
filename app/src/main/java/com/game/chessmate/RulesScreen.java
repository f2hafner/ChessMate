package com.game.chessmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RulesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_screen);
        getSupportActionBar().hide();
    }
}