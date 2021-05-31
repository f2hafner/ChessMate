package com.game.chessmate;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.ChessBoard;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;
    SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        Button cheatButton = getCheatButton();

        //Intent cheatIntent = new Intent(this, ChessBoard.class);
        //cheatIntent.putExtra("cheatButtonMessage", cheatButton.getText().toString());
        //startActivity(cheatIntent);


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


    public  Button getCheatButton() {
        Button cheatButton = findViewById(R.id.cheatButton);
        return cheatButton;
    }
    private static boolean isCheatOn = false;


    public static boolean cheatButtonStatus(){
        return isCheatOn;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            if (getCheatButton().equals("Cheat On")) {


            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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








