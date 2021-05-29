package com.game.chessmate;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.ChessBoard;

import java.util.EventListener;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button cheatButton = getCheatButton();

        //Intent cheatIntent = new Intent(this, ChessBoard.class);
        //cheatIntent.putExtra("cheatButtonMessage", cheatButton.getText().toString());
        //startActivity(cheatIntent);
        final Intent cheatIntent = new Intent(GameActivity.this, ChessBoard.class);

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cheatButton.getText().toString().matches("Cheat Off")){
                    cheatButton.setText("Cheat On");
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    cheatIntent.putExtra("cheatButtonSetting", true);
                }
                else if(cheatButton.getText().toString().matches("Cheat On")){
                    cheatButton.setText("Cheat Off");
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));
                    cheatIntent.putExtra("Key cheatButtonSetting", false);
                }
                startActivity(cheatIntent);
            }

        });


    }




    public Button getCheatButton() {
        Button cheatButton = findViewById(R.id.cheatButton);
        return cheatButton;
    }
}


//boolean cheatButtonSetting;
   /* public String  getCheatButton() {

        Button cheatButton = findViewById(R.id.cheatButton);
        WifiP2pManager.ActionListener cheatButtonListener = new EventListener(){



        }

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
    }*/








