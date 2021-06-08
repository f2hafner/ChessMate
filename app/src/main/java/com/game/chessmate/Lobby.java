package com.game.chessmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.NetObjects.LobbyDataObject;
import com.game.chessmate.GameFiles.Networking.NetObjects.startGameParameters;
import com.game.chessmate.GameFiles.Networking.NetworkManager;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        getSupportActionBar().hide();



        TextView codeOutput = findViewById(R.id.CodeOutput);
        //codeOutput.setVisibility(View.INVISIBLE);
        TextView player1 = findViewById(R.id.player1Name);
        String name = getIntent().getExtras().getString("playername1");
        String name2 = getIntent().getExtras().getString("playername2");
        String lobbycode = getIntent().getExtras().getString("lobbycode");
        player1.setText(name);
        codeOutput.setText(lobbycode);
        TextView player2 = findViewById(R.id.player2Name);
        if(name2 == null) player2.setText("Waiting for player2");
        player2.setText(name2);

        Button enterGameLobbyButton = findViewById(R.id.enterGameLobbyButton);
        enterGameLobbyButton.setVisibility(View.INVISIBLE);

        enterGameLobbyButton.setOnClickListener(v -> {
            startGameParameters startParameter = NetworkManager.startGame(lobbycode);
            if(startParameter!=null){
                Intent toGameIntent = new Intent(this, GameActivity.class);
                if(startParameter.getInitColour() == ChessPieceColour.BLACK)
                    toGameIntent.putExtra("initcolour",true);
                startActivity(toGameIntent);
            }
        });

        Listener lobbyUpdateListener = new Listener(){
            public void received(Connection connection, Object object) {
                if(object instanceof LobbyDataObject){
                    LobbyDataObject req = (LobbyDataObject) object;
                    runOnUiThread(() -> {
                        player2.setText(req.getPlayer2().getName());
                        player2.setVisibility(View.VISIBLE);
                        enterGameLobbyButton.setVisibility(View.VISIBLE);
                    });
                }
            }
        };
        ChessMateClient.getInstance().getClient().addListener(lobbyUpdateListener);
    }
}