package com.game.chessmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.game.chessmate.GameFiles.Deck;

public class GameActivity extends AppCompatActivity {
    ImageView card1,card2,card3, exactView;
    Button button;
    Deck deck;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //init deck of cards
        deck=new Deck();

        card1=(ImageView)findViewById(R.id.cardView1);
        card2=(ImageView)findViewById(R.id.cardView2);
        card3=(ImageView)findViewById(R.id.cardView3);
        exactView=(ImageView)findViewById(R.id.exactView);
        button=(Button)findViewById(R.id.back);


        //close card
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exactView.setVisibility(View.GONE);
                switch (id){
                        case 1:
                            card1.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            card2.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            card3.setVisibility(View.VISIBLE);
                            break;
                    }
                    id=0;
            }
        });


        //click on first card
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[0].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card1.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=1;
            }
        });

        //click on second card
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[1].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card2.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=2;
            }
        });

        //click on third card
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exactView.setImageResource(deck.cardsPlayer1[2].getDrawableId());
                exactView.setVisibility(View.VISIBLE);
                card3.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                id=3;
            }
        });
    }
}