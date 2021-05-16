package com.game.chessmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();


        Button back = findViewById(R.id.backButtonOptions);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music.getText().toString().matches("Music on")) {
                    music.setText("Music off");
                }
                else if (music.getText().toString().matches("Music off")) {
                    music.setText("Music on");
                }
            }
        });

        Button changeName = findViewById(R.id.changeName);


        changeName.setOnClickListener(v -> {
            Intent changeNameIntent=new Intent (this, HomeActivity.class);
            startActivity(changeNameIntent);
        });


    }

}

