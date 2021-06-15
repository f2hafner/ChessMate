package com.game.chessmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.ChessBoard;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();
        Button music = findViewById(R.id.music);
        ChessBoard board = ChessBoard.getInstance();
        if (board.isSoundOn()){
            music.setText("Music off");
        }
        else if(!board.isSoundOn()){
            music.setText("Music on");
        }

        Button back = findViewById(R.id.backButtonOptions);
        back.setOnClickListener(v ->  {
            finish();
        });


        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSound(music);
            }
        });

        Button changeName = findViewById(R.id.changeName);

        changeName.setOnClickListener(v -> {
            Intent changeNameIntent=new Intent (this, HomeActivity.class);
            startActivity(changeNameIntent);
        });


    }

    public void switchSound(Button music) {
        ChessBoard board = ChessBoard.getInstance();
        if (board.isSoundOn()) {
            music.setText("Music on");
            board.setSoundOn(false);

        }
        else if (!board.isSoundOn()) {
            music.setText("Music off");
            board.setSoundOn(true);
        }
        Log.i("", "switchSound: " + board.isSoundOn());
    }
}

