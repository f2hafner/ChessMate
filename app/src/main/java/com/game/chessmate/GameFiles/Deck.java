package com.game.chessmate.GameFiles;

import java.security.SecureRandom;
import java.util.Random;

public class Deck {
    private  Card[] deck;
    private int size=15;//25
    private int initialCardNumber=3;
    private int currentCard=0;
    private Random rand;

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

        for (int i=0;i<size;i++){
            if (deck[i].getId()==7)
                temp[0]=deck[i];
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
}
