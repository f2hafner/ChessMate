package com.game.chessmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.NetworkManager;

public class CreateSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        getSupportActionBar().hide();


        Button createSession=(Button) findViewById(R.id.createSessionButton);
        Button joinSession=(Button) findViewById(R.id.joinSessionButton);
        Button options =(Button) findViewById(R.id.optionsButton);
        Button rules =(Button) findViewById(R.id.rulesButton);

        TextView namedisplay = findViewById(R.id.playerName);
        String name = getIntent().getExtras().getString("name");

        namedisplay.setText("Welcome "+ name);

        createSession.setOnClickListener(v -> {
            ChessMateClient chessMateClient = ChessMateClient.getInstance();
            String lobbycode = NetworkManager.createSession(name);
            if(lobbycode!=null){
                Intent createSessionIntent = new Intent(this, Lobby.class);
                createSessionIntent.putExtra("name",name);
                createSessionIntent.putExtra("lobbycode",lobbycode);
                startActivity(createSessionIntent);
            }
        });

        joinSession.setOnClickListener(v -> {
            Intent joinSessionIntent = new Intent(this, EnterCodeActivity.class);
            startActivity(joinSessionIntent);
        });

        options.setOnClickListener(v -> {
            Intent optionsIntent=new Intent (this, OptionsActivity.class);
            startActivity(optionsIntent);
        });

        rules.setOnClickListener(v -> {
                    Intent ruleIntent = new Intent(this, RuleActivity.class);
                    startActivity(ruleIntent);
        });
    }
}