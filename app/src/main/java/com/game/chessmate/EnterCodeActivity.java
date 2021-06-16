package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.GameState;
import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.NetObjects.ErrorPacket;
import com.game.chessmate.GameFiles.Networking.NetObjects.LobbyDataObject;
import com.game.chessmate.GameFiles.Networking.NetObjects.startGameParameters;
import com.game.chessmate.GameFiles.Networking.NetworkManager;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;

public class EnterCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        getSupportActionBar().hide();

        Button enterLobbyBtn =(Button) findViewById(R.id.btn_EnterLobby);
        EditText lobbycode = findViewById(R.id.CodeInputEditText);
        String name = getIntent().getExtras().getString("name");

        enterLobbyBtn.setOnClickListener(v -> {
            LobbyDataObject lobbyObject = NetworkManager.joinSession(lobbycode.getText().toString(),name);
            if(lobbyObject!=null){
                if(lobbyObject.isClearLobby()){
                    Toast.makeText(this, lobbyObject.getLobbycode(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent toLobbyIntent = new Intent(this, Lobby.class);
                    toLobbyIntent.putExtra("playername1",lobbyObject.getPlayer1().getName());
                    toLobbyIntent.putExtra("playername2",lobbyObject.getPlayer2().getName());
                    toLobbyIntent.putExtra("lobbycode",lobbyObject.getLobbycode());
                    startActivity(toLobbyIntent);
                }
            }
        });
    }
}