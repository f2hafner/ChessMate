package com.game.chessmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.NetObjects.LobbyDataObject;
import com.game.chessmate.GameFiles.Networking.NetworkManager;

public class CreateSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        getSupportActionBar().hide();

        ChessMateClient.getInstance();

        Button createSession=(Button) findViewById(R.id.createSessionButton);
        Button joinSession=(Button) findViewById(R.id.joinSessionButton);
        Button options =(Button) findViewById(R.id.optionsButton);
        Button rules =(Button) findViewById(R.id.rulesButton);

        TextView namedisplay = findViewById(R.id.playerName);
        String name = getIntent().getExtras().getString("name");

        namedisplay.setText("Welcome "+ name);
        final String[] lobbycode = new String[1];
        createSession.setOnClickListener(v -> {
            lobbycode[0] = NetworkManager.createSession(name);
            if(lobbycode[0]!=null){
                if(lobbycode[0].length()>0){
                    Intent toLobbyIntent = new Intent(this, Lobby.class);
                    toLobbyIntent.putExtra("playername1",name);
                    toLobbyIntent.putExtra("lobbycode",lobbycode[0]);
                    startActivity(toLobbyIntent);
                } else {
                    Toast.makeText(this, "No server available!", Toast.LENGTH_LONG).show();

                }
            }
        });

        joinSession.setOnClickListener(v -> {
            Intent joinSessionIntent = new Intent(this, EnterCodeActivity.class);
            joinSessionIntent.putExtra("name",name);
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