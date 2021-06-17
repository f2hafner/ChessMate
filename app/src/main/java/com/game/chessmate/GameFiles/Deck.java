package com.game.chessmate.GameFiles;

import android.content.Context;

import java.security.SecureRandom;
import java.util.Random;

public class Deck {

    //current deck of possible Cards
    private  Card[] deck;

    //max size of deck
    private int size=14;//25

    //size of deck for each player during game
    private int initialCardNumber=3;

    //index for deck (to get next Card)
    private int currentCard=0;

    //seed for shuffle
    private Random rand;

    //last Card which was played in the game
    private Card lastCardPlayed=null;


    //init deck
    public Deck(Context context){
        deck=new Card[size];

        for (int i=0;i<size;i++){
            deck[i]=new Card(i,context);
        }

        rand=new SecureRandom();
        shuffle();
    }

    //return start Card-Array for each player
    public Card[] getInitialCards(){
        Card[]temp=new Card[initialCardNumber];
        int j=0;

        while (j<initialCardNumber){
            temp[j]=deck[currentCard];
            deck[currentCard].setOwned(true);
            currentCard++;
            j++;
        }

        for (int i=0;i<size;i++){
            if(deck[i].getId()==9)
                temp[0]=deck[i];
            if (deck[i].getId()==13)
                temp[1]=deck[i];
            if (deck[i].getId()==8)
                temp[2]=deck[i];
        }

        return temp;
    }

    public void setSize(int size){this.size=size;}
    public int getSize(){return size;}

    public void shuffle(){
        Card temp;
        int random;


        for (int i=0;i<size;i++){
            random=rand.nextInt(size);
            temp=deck[i];
            deck[i]=deck[random];
            deck[random]=temp;
        }
    }

    public Card drawCard() {
        if (currentCard == size) {
            shuffle();
        }

        while (deck[currentCard].isOwned()){
            currentCard++;
            if (currentCard==size) {
                shuffle();
            }
        }

        deck[currentCard].setOwned(true);

        return deck[currentCard];
    }

    public void setCurrentCard(int number){currentCard=number;}

    public Card [] getDeck(){return deck;}

    public Card getLastCardPlayed(){return lastCardPlayed;}

    public void setLastCardPlayed(Card card){this.lastCardPlayed=card;}

    public int getInitialCardNumber(){return this.initialCardNumber;}

    public Card getSpecificCard(int cardId){
        for (int i=0;i<size;i++){
            if (deck[i].getId()==cardId){
                return deck[i];
            }
        }
        return null;
    }
}
