package com.game.chessmate;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;


import com.game.chessmate.GameFiles.Deck;

public class GameActivity extends AppCompatActivity {
    ImageView card1, card2, card3, exactView;
    Button button;
    Deck deck;
    int id = 0;


    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener lightEventListener;
    private float maxValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (sensor == null) {
            Toast.makeText(this, "your device has no light sensore, so you wont be able to use the cheat funktion", Toast.LENGTH_SHORT).show();
            //TODO stop cheat function when no sensor is avaliable
        }
        maxValue = sensor.getMaximumRange();
        //Log.d("Sensor", String.valueOf(maxValue));
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float lightValue = sensorEvent.values[0];
                //float closeSensor = maxValue/100;
                if (lightValue <= 500 && cheatButtonStatus()) {
                    if (ChessBoard.getwasMoveLegal()) {
                        //TODO Player has to stop for one round
                    } else {
                        ChessBoard.getStartPossition();
                        //TODO move piece back to start possition

                    }
                    //Log.d("SENSOR", String.valueOf(lightValue));
                    //TODO and person who pressedn cheat button made a move then we need to check if the move was legal
                    //  ChessBoard.getwasMoveLegal();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        Button cheatButton = getCheatButton();


        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cheatButton.getText().toString().matches("Cheat Off")) {
                    cheatButton.setText("Cheat On");
                    isCheatOn = true;
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

                } else if (cheatButton.getText().toString().matches("Cheat On")) {
                    cheatButton.setText("Cheat Off");
                    isCheatOn = false;
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));

                }
            }
        });

        //init deck of cards
        deck=new Deck();

        card1=(ImageView)findViewById(R.id.cardView1);
        card2=(ImageView)findViewById(R.id.cardView2);
        card3=(ImageView)findViewById(R.id.cardView3);
        exactView=(ImageView)findViewById(R.id.exactView);
        button=(Button)findViewById(R.id.back);


        //close card
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exactView.setVisibility(View.GONE);
                switch (id){
                    case 1:
                        card1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        card2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        card3.setVisibility(View.VISIBLE);
                        break;
                }
                id=0;
            }
        });


        //click on first card
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[0].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card1.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=1;
            }
        });

        //click on second card
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[1].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card2.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=2;
            }
        });

        //click on third card
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[2].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card3.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=3;
            }
        });
    }







    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public Button getCheatButton() {
        Button cheatButton = findViewById(R.id.cheatButton);
        return cheatButton;
    }

    private static boolean isCheatOn = false;


    public static boolean cheatButtonStatus() {
        return isCheatOn;
    }

    /*@Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
           if (getCheatButton().equals("Cheat On")) {
              if(!ChessBoard.getwasMoveLegal()){

              }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/





}


       /* //init deck of cards
        deck=new Deck();

        card1=(ImageView)findViewById(R.id.cardView1);
        card2=(ImageView)findViewById(R.id.cardView2);
        card3=(ImageView)findViewById(R.id.cardView3);
        exactView=(ImageView)findViewById(R.id.exactView);
        button=(Button)findViewById(R.id.back);


        //close card
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exactView.setVisibility(View.GONE);
                switch (id){
                        case 1:
                            card1.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            card2.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            card3.setVisibility(View.VISIBLE);
                            break;
                    }
                    id=0;
            }
        });


        //click on first card
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[0].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card1.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=1;
            }
        });

        //click on second card
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[1].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card2.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=2;
            }
        });

        //click on third card
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[2].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card3.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=3;
            }
        });
    }
}*/