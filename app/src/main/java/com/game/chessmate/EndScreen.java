package com.game.chessmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.GameState;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        TextView endMsg = findViewById(R.id.txtEnd);
        if(ChessBoard.getInstance().getGameState() == GameState.LOOSE){
            endMsg.setText("SORRY. YOU LOST. WANT TO TRY AGAIN?");
        }else if(ChessBoard.getInstance().getGameState() == GameState.WIN) {
            endMsg.setText("CONGRATULATIONS! YOU WON! WANNA GO AGAIN?");
        }

        Button backHome = findViewById(R.id.btnHome);
        backHome.setOnClickListener(v -> {
                Intent createSessionIntent = new Intent(this, HomeActivity.class);
                startActivity(createSessionIntent);
        });
    }
}