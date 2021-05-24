package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.PlayingPieces.Pawn;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        getCheatButton();

    }
    //boolean cheatButtonSetting;
    public String  getCheatButton() {

        Button cheatButton = findViewById(R.id.cheatButton);
        cheatButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (cheatButton.getText().toString().matches("Cheat Off")){
                    cheatButton.setText("Cheat On");
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    //cheatButtonSetting = true;
                }
                else if(cheatButton.getText().toString().matches("Cheat On")){
                    cheatButton.setText("Cheat Off");
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));
                   //cheatButtonSetting = false;
                }

            }

        });

        return cheatButton.getText().toString();
    }








}