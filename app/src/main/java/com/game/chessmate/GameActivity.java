package com.game.chessmate;
import android.app.Service;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.BoardView;
import com.game.chessmate.GameFiles.CheatFunktion;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Deck;
import com.game.chessmate.GameFiles.Player;
import com.game.chessmate.GameFiles.GameState;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;

import com.game.chessmate.GameFiles.Networking.NetObjects.PlayerDataObject;
import com.game.chessmate.GameFiles.Networking.PlayerObject;
import com.game.chessmate.GameFiles.Player;

/**
 * The type Game activity.
 */
public class GameActivity extends AppCompatActivity {

    private static ImageView card1, card2, card3, exactView;
    private static Button button;
    private static int id = 3;
    private static boolean selected;

    Player player;

    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private TextView gameStateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gameStateView = findViewById(R.id.gameStateView);
        gameStateView.setTextSize(18);
        gameStateView.setTextColor(Color.BLACK);
        gameStateView.setGravity(1);
        gameStateView.setTypeface(null, Typeface.BOLD);
        gameStateView.setText("The Game started !");

        Button cheatButton = getCheatButton();

        Player player = ChessBoard.getInstance().getLocalPlayer();

        if (sensor == null) {
            Toast.makeText(this, "your device has no light sensore, so you wont be able to use the cheat funktion", Toast.LENGTH_SHORT).show();
            CheatFunktion.setCheatFunction(false);
        } else {
            CheatFunktion.setCheatFunction(true);

        }
        maxValue = sensor.getMaximumRange();
        CheatFunktion cheatFunktion = new CheatFunktion(GameActivity.this);
        //Log.d("Sensor", String.valueOf(maxValue));
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                player.setLightValue(sensorEvent.values[0]);
                //float closeSensor = maxValue/100;
                if ( sensorEvent.values[0] <= 500  ) {
                    cheatFunktion.teterminCheat();
                    }
                //Log.d("SENSOR", String.valueOf(lightValue));
            }



            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };



         Button sensorButton = findViewById(R.id.LightSensorButton);
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.setLightValue(0);
            }
        });


        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO How to distinguish who pressed the button
                if (cheatButton.getText().toString().matches("Cheat Off")) {
                    cheatButton.setText("Cheat On");
                    player.setCheatOn(true);
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.black));
                    isCheatOn = true;
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.white));

                } else if (cheatButton.getText().toString().matches("Cheat On")) {
                    cheatButton.setText("Cheat Off");
                    isCheatOn = false;
                    ChessBoard.getInstance().resetLegalMoves();
                    player.setCheatOn(false);
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.white));
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));

                }
            }
        });


        card1 = (ImageView) findViewById(R.id.cardView1);
        card2 = (ImageView) findViewById(R.id.cardView2);
        card3 = (ImageView) findViewById(R.id.cardView3);
        exactView = (ImageView) findViewById(R.id.exactView);
        button = (Button) findViewById(R.id.back);


        //Select Card
        exactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mark card selected
                exactView.setVisibility(View.GONE);
                selected=true;
                switch (id) {
                    case 0:
                        card1.setVisibility(View.VISIBLE);
                        card1.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        card2.setVisibility(View.VISIBLE);
                        card2.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        card3.setVisibility(View.VISIBLE);
                        card3.setBackgroundColor(Color.RED);
                        break;
                }
            }
        });

        //close card
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setVisibility(View.GONE);
                button.setVisibility(View.INVISIBLE);
                switch (id) {
                    case 0:
                        card1.setVisibility(View.VISIBLE);
                        card1.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        card2.setVisibility(View.VISIBLE);
                        card2.setBackgroundColor(Color.WHITE);
                        break;
                    case 2:
                        card3.setVisibility(View.VISIBLE);
                        card3.setBackgroundColor(Color.WHITE);
                        break;
                }
                id = 3;
                selected=false;
            }
        });


        //click on first card
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3||id==0) {
                    exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[0].getDrawableId());
                    exactView.setVisibility(View.VISIBLE);
                    card1.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    id = 0;
                }
            }
        });

        //click on second card
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3||id==1) {
                    exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[1].getDrawableId());
                    exactView.setVisibility(View.VISIBLE);
                    card2.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    id = 1;
                }
            }
        });

        //click on third card
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==3||id==2) {
                    exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[2].getDrawableId());
                    exactView.setVisibility(View.VISIBLE);
                    card3.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    id = 2;
                }
            }
        });




    //Cheat-Function
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
                    if (ChessBoard.getInstance().getwasMoveLegal()) {

                        //TODO Player has to stop for one round
                    } else {
                        ChessBoard.getInstance().getStartPossition();
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


    /**
     * Gets cheat button.
     *
     * @return the cheat button
     */
    public Button getCheatButton() {
        Button cheatButton = findViewById(R.id.cheatButton);
        return cheatButton;
    }

    private static boolean isCheatOn = false;


    /**
     * Cheat button status boolean.
     *
     * @return the boolean
     */
    public static boolean cheatButtonStatus() {
        return isCheatOn;
    }

    public static boolean isSelected(){
        return selected;
    }

    public static int getId(){
        return id;
    }

    public static void unselectAfterCardActivation(){
        button.setVisibility(View.INVISIBLE);
        switch (id) {
            case 0:
                card1.setVisibility(View.VISIBLE);
                card1.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                card2.setVisibility(View.VISIBLE);
                card2.setBackgroundColor(Color.WHITE);
                break;
            case 2:
                card3.setVisibility(View.VISIBLE);
                card3.setBackgroundColor(Color.WHITE);
                break;
        }
        id = 3;
        selected=false;
    }

    public void setGameStateView(GameState gameState) {
        gameStateView = findViewById(R.id.gameStateView);
        if (gameStateView != null) {
            Log.i("TAG", "setGameStateView: " + gameStateView);
            switch (gameState){
                case ACTIVE: gameStateView.setText("Your Turn !"); break;
                case WAITING: gameStateView.setText("Waiting for enemy..."); break;
                case WIN: gameStateView.setText("You Won !"); break;
                case LOOSE: gameStateView.setText("You Lost"); break;
                default: gameStateView.setText("..."); break;
            }
        }
    }
}

