package com.game.chessmate.GameFiles;

import java.security.SecureRandom;
import java.util.Random;

public class Deck {
    private  Card[] deck;
    private int size=13;//25
    private int initialCardNumber=3;
    private int currentCard=0;
    private Random rand;
    private Card lastCardPlayed=null;

    public Deck(){
        deck=new Card[size];

        for (int i=0;i<size;i++){
            deck[i]=new Card(i);
        }

        rand=new SecureRandom();
        shuffle();
    }

    public Card[] getInitialCards(){
        Card[]temp=new Card[initialCardNumber];
        int j=0;

        while (j<initialCardNumber){
            temp[j]=deck[currentCard];
            deck[currentCard].setOwned(true);
            currentCard++;
            j++;
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
            currentCard=0;
        }

        shuffle();

        while (deck[currentCard].isOwned()){
            currentCard++;
            if (currentCard==size)
                currentCard=0;
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
