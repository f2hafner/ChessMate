package com.game.chessmate.GameFiles;

import android.content.Context;

import java.security.SecureRandom;
import java.util.Random;

/**
 * The type Deck.
 */
public class Deck {

    //current deck of possible Cards
    private final Card[] deck;

    //max size of deck
    private int size=16;//25

    //size of deck for each player during game
    private final int initialCardNumber=3;

    //index for deck (to get next Card)
    private int currentCard=0;

    //seed for shuffle
    private final Random rand;

    //last Card which was played in the game
    private Card lastCardPlayed=null;
    private boolean lastCardPlayedbyOponent=false;


    /**
     * Instantiates a new Deck.
     *
     * @param context the context
     */
    public Deck(Context context){
        deck=new Card[size];

        for (int i=0;i<size;i++){
            deck[i]=new Card(i,context);
        }

        rand=new SecureRandom();
        shuffle();
    }

    /**
     * Get initial cards card [ ].
     *
     * @return the card [ ]
     */
    public Card[] getInitialCards(){
        //return start Card-Array for each player
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

    /**
     * Set size.
     *
     * @param size the size
     */
    public void setSize(int size){this.size=size;}

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize(){return size;}

    /**
     * Shuffle.
     */
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

    /**
     * Draw card card.
     *
     * @return the card
     */
    public Card drawCard() {

        Card card;

        if (currentCard > size-1) {
            shuffle();
        }

        while (deck[currentCard].isOwned()){
            currentCard++;
            if (currentCard==size) {
                shuffle();
            }
        }

        card=deck[currentCard];
        card.setOwned(true);
        currentCard++;

        return card;
    }

    /**
     * Set current card.
     *
     * @param number the number
     */
    public void setCurrentCard(int number){currentCard=number;}

    /**
     * Get deck card [ ].
     *
     * @return the card [ ]
     */
    public Card [] getDeck(){return deck;}

    /**
     * Get last card played card.
     *
     * @return the card
     */
    public Card getLastCardPlayed(){return lastCardPlayed;}

    /**
     * Set last card played.
     *
     * @param card the card
     */
    public void setLastCardPlayed(Card card){this.lastCardPlayed=card;}

    /**
     * Get initial card number int.
     *
     * @return the int
     */
    public int getInitialCardNumber(){return this.initialCardNumber;}

    /**
     * Get specific card card.
     *
     * @param cardId the card id
     * @return the card
     */
    public Card getSpecificCard(int cardId){
        for (int i=0;i<size;i++){
            if (deck[i].getId()==cardId){
                return deck[i];
            }
        }
        return null;
    }

    public boolean isLastCardPlayedbyOponent() {
        return lastCardPlayedbyOponent;
    }

    public void setLastCardPlayedbyOponent(boolean lastCardPlayedbyOponent) {
        this.lastCardPlayedbyOponent = lastCardPlayedbyOponent;
    }
}
