package com.game.chessmate;
import android.app.Service;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.CheatFunktion;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Networking.NetworkTasks;
import com.game.chessmate.GameFiles.Player;
import com.game.chessmate.GameFiles.GameState;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * The type Game activity.
 */
public class GameActivity extends AppCompatActivity {

    //Declaring Card-Variables
    private static ImageView card1, card2, card3, exactView;
    private static Button button;
    private static int id = 3;
    private static boolean selected;
    private MediaPlayer cardSelectSound;

    /**
     * The Player.
     */
    Player player;

    //Declaring CheatFunction-Variables
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private TextView gameStateView;
    private static boolean isCheatOn = false;

    /**
     * The Cheat button.
     */
    Button cheatButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        //initialise Sound Effect
        cardSelectSound=MediaPlayer.create(this,R.raw.mixkit_card_select);
        this.cardSelectSound.setVolume(1.0f,1.0f);

        //initialise Sensor
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //initialise Game-State-View
        gameStateView = findViewById(R.id.gameStateView);
        gameStateView.setTextSize(18);
        gameStateView.setTextColor(Color.BLACK);
        gameStateView.setGravity(1);
        gameStateView.setTypeface(null, Typeface.BOLD);
        gameStateView.setText("The Game started !");

        cheatButton = findViewById(R.id.cheatButton);
        player = ChessBoard.getInstance().getLocalPlayer();

        if (sensor == null) {
            Toast.makeText(this, "your device has no light sensor, so you wont be able to use the cheat function", Toast.LENGTH_SHORT).show();
            CheatFunktion.setCheatFunction(false);
        } else {
            CheatFunktion.setCheatFunction(true);
        }

         //Cheat-Function
        maxValue = sensor.getMaximumRange();
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float lightValue = sensorEvent.values[0];
                //float closeSensor = maxValue/100;
                if (lightValue < 1) {
                    Toast.makeText(GameActivity.this, "You tried to reveal a cheat!", Toast.LENGTH_SHORT).show();
                    new NetworkTasks.SendSensorPacket();
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        cheatButton.setOnClickListener(v -> {

            if (isCheatOn) {
                if(player.getTimesCheatFunktionUsedWrongly()==0){
                    isCheatOn = false;
                    cheatButton.setText("No Cheats Left!");
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.white));
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));
                } else {
                    cheatButton.setText("Cheat OFF " + player.getTimesCheatFunktionUsedWrongly() + " left");
                    ChessBoard.getInstance().resetLegalMoves();
                    player.setCheatOn(false);
                    isCheatOn = false;
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.white));
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));
                }
            } else {
                if(player.getTimesCheatFunktionUsedWrongly()==0){
                    isCheatOn = false;
                    cheatButton.setText("No Cheats Left!");
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.white));
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.black));
                } else {
                    cheatButton.setText("Cheat ON " + player.getTimesCheatFunktionUsedWrongly()+" left");
                    isCheatOn = true;
                    player.setCheatOn(true);
                    cheatButton.setTextColor(getApplication().getResources().getColor(R.color.black));
                    cheatButton.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });




        //initialising Card Variables
        card1 = findViewById(R.id.cardView1);
        card2 = findViewById(R.id.cardView2);
        card3 = findViewById(R.id.cardView3);
        exactView = findViewById(R.id.exactView);
        button = findViewById(R.id.back);


        //Select Card
        exactView.setOnClickListener(v -> {
            //start sound
            cardSelectSound.start();

            //mark card selected
            exactView.setVisibility(View.GONE);
            selected = true;
            gameStateView.setVisibility(View.VISIBLE);

            switch (id) {
                case 0:
                    card1.setVisibility(View.VISIBLE);
                    card1.setBackgroundColor(Color.RED);

                    if (ChessBoard.getInstance().getCardsPlayer()[0].getId()==12) {
                        if(ChessBoard.getInstance().getDeck().isLastCardPlayedbyOponent())
                            ChessBoard.getInstance().getCardsPlayer()[0].vulture(0, ChessBoard.getInstance().getLocalPlayer(), ChessBoard.getInstance().getDeck());
                        else
                            unselectAfterCardActivation();
                    }
                    break;
                case 1:
                    card2.setVisibility(View.VISIBLE);
                    card2.setBackgroundColor(Color.RED);

                    if (ChessBoard.getInstance().getCardsPlayer()[1].getId()==12){
                        if(ChessBoard.getInstance().getDeck().isLastCardPlayedbyOponent())
                            ChessBoard.getInstance().getCardsPlayer()[1].vulture(1,ChessBoard.getInstance().getLocalPlayer(), ChessBoard.getInstance().getDeck());
                        else
                            unselectAfterCardActivation();
                    }
                    break;
                case 2:
                    card3.setVisibility(View.VISIBLE);
                    card3.setBackgroundColor(Color.RED);

                    if (ChessBoard.getInstance().getCardsPlayer()[2].getId()==12){
                        if(ChessBoard.getInstance().getDeck().isLastCardPlayedbyOponent())
                            ChessBoard.getInstance().getCardsPlayer()[2].vulture(2,ChessBoard.getInstance().getLocalPlayer(), ChessBoard.getInstance().getDeck());
                        else
                            unselectAfterCardActivation();
                    }
                    break;
            }
        });

        //close card
        button.setOnClickListener(v -> {
            //mark card unselected
            gameStateView.setVisibility(View.VISIBLE);
            exactView.setVisibility(View.GONE);
            unselectAfterCardActivation();
        });


        //click on first card
        card1.setOnClickListener(v -> {
            //show first card and button
            if (id == 3 || id == 0) {
                gameStateView.setVisibility(View.INVISIBLE);
                exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[0].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card1.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id = 0;
            }
        });

        //click on second card
        card2.setOnClickListener(v -> {
            //show second card and button
            if (id == 3 || id == 1) {
                gameStateView.setVisibility(View.INVISIBLE);
                exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[1].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card2.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id = 1;
            }
        });

        //click on third card
        card3.setOnClickListener(v -> {
            //show third card and button
            if (id == 3 || id == 2) {
                gameStateView.setVisibility(View.INVISIBLE);
                exactView.setImageResource(ChessBoard.getInstance().getCardsPlayer()[2].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card3.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id = 2;
            }
        });


    }

    //Methods for sensor
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
        cheatButton=findViewById(R.id.cheatButton);
        return cheatButton;
    }

    /**
     * Cheat button status boolean.
     *
     * @return the boolean
     */
    public static boolean cheatButtonStatus() {
        return isCheatOn;
    }

    /**
     * Is card selected boolean.
     *
     * @return the boolean
     */
    public static boolean isSelected(){
        return selected;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public static int getId(){
        return id;
    }

    /**
     * Unselect after card activation.
     */
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

        if (!ChessBoard.getInstance().isSpecialActivated()) {
            ChessBoard.getInstance().setCardActivated(false);
        }
    }

    /**
     * Sets game state view.
     *
     * @param gameState the game state
     */
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new NetworkTasks.SendLeaveSessionPacket();
    }

    /**
     * Show toast.
     *
     * @param text the text
     */
    //message to user that oponent used a card
    @WorkerThread
    public void showToast(String text) {
        runOnUiThread(() -> {
            String message="Opponent used the card: "+text;
            Toast.makeText(GameActivity.this,message,Toast.LENGTH_LONG).show();
        });
    }

}

