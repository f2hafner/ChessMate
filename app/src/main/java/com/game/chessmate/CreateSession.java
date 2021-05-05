package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        getSupportActionBar().hide();

        Button createSession=(Button) findViewById(R.id.createSessionButton);
        Button joinSession=(Button) findViewById(R.id.joinSessionButton);
        Button options=(Button) findViewById(R.id.optionsButton);
        Button rules=(Button) findViewById(R.id.rulesButton);

        String name =getIntent().getExtras().getString("name");

        createSession.setOnClickListener(v -> {
            Intent createSessionIntent=new Intent(this,Lobby.class);
            createSessionIntent.putExtra("name",name);
            startActivity(createSessionIntent);
        });

        joinSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            }
        });

        options.setOnClickListener(v -> {
            Intent optionsIntent=new Intent (this, OptionsActivity.class);
            startActivity(optionsIntent);
        });

        rules.setOnClickListener(v -> {
                    Intent rulesIntent = new Intent(this, RuleActivity.class);
                    startActivity(rulesIntent);
        });

    }
}