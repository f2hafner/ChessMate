package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.game.chessmate.GameFiles.NetworkManager;
import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionRequest;
import com.game.chessmate.GameFiles.Networking.NetworkTasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
            ExecutorService service = Executors.newFixedThreadPool(10);
            Future<String> future = service.submit(new NetworkTasks.CreateSession(name));
            try{
                String lobbycode = future.get();
                Log.i("NETWORK","LobbyCode: "+lobbycode);
                Intent createSessionIntent = new Intent(this,Lobby.class);
                createSessionIntent.putExtra("name",name);
                createSessionIntent.putExtra("lobbycode",lobbycode);
                startActivity(createSessionIntent);
            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
            //warum komm funktioniert der zurueck button nicht
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
                    Intent ruleIntent = new Intent(this, RuleActivity.class);
                    startActivity(ruleIntent);
        });

    }
}