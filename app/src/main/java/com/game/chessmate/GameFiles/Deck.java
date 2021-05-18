package com.game.chessmate.GameFiles;

import com.game.chessmate.GameFiles.Card;

import java.util.Random;

public class Deck {
    private  Card[] deck;
    private Card[] used;
    private int size=25;
    private int initialCardNumber=3;
    private int currentCard=0;

    public Deck(){
        deck=new Card[size];
        used=new Card[size];

        for (int i=0;i<size;i++){
            deck[i]=new Card(i);
        }
    }

    public Card[] getInitialCards(){
        Card[]temp=new Card[initialCardNumber];
        int j=0;

        while (j<initialCardNumber){
            temp[j]=deck[currentCard];
            deck[currentCard].setOwned();
            currentCard++;
            j++;
        }

        return temp;
    }

    public Card[] getDeck(){return deck;}

    public void setSize(int size){this.size=size;}
    public int getSize(){return size;}

    public void shuffle(){
        Card temp;
        int random;

        Random rand=new Random();

        for (int i=0;i<size;i++){
            random=rand.nextInt(size);
            temp=deck[i];
            deck[i]=deck[random];
            deck[random]=temp;
        }
    }

    public Card drawCard() {
        if (currentCard == 25) {
            shuffle();
            currentCard=0;
        }

        while (deck[currentCard].isOwned()){
            currentCard++;
        }

        deck[currentCard].setOwned();

        return deck[currentCard];
    }

    public void setCurrentCard(int number){currentCard=number;}


}