package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        getSupportActionBar().hide();

        TextView codeOutput = findViewById(R.id.CodeOutput);
        //codeOutput.setVisibility(View.INVISIBLE);

        TextView player1 = findViewById(R.id.player1Name);
        String name =getIntent().getExtras().getString("name");
        player1.setText(name);
        TextView player2 = findViewById(R.id.player2Name);

        Button enterGameLobbyButton  = findViewById(R.id.enterGameLobbyButton);

        enterGameLobbyButton.setOnClickListener(v -> {
            Intent createSessionIntent=new Intent(this,GameActivity.class);
            startActivity(createSessionIntent);
        });


    }
}