package com.game.chessmate.GameFiles;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DeckTest {
    Deck deck;

    @Mock
    Card card;

    @Mock
    Context context;

    Card [] cards;

    @Before
    public void init(){
        context=Mockito.mock(Context.class);
        card= Mockito.mock(Card.class);

        deck=new Deck(context);
    }

    @Test
    public void testInitialCards(){

        //the first card of the initial card array should be the next card not owned
        Card[]initial=deck.getInitialCards();
        cards=deck.getDeck();

        assertEquals(cards[0],initial[0]);
        assertEquals(3,deck.getInitialCardNumber());
    }

    @Test
    public void testGetSize(){
        assertEquals(16,deck.getSize());
    }

    @Test
    public void testSetStize(){
        deck.setSize(26);
        assertEquals(26,deck.getSize());
    }

    @Test
    public void testShuffle(){
        deck.shuffle();
        cards=deck.getDeck();

        assertNotEquals(cards,deck);

    }

    @Test
    //draw card before any card is owned
    public void testDrawCardBeforeBeginning(){
        cards=deck.getDeck();

        //check if deck and reference deck are equal
        assertEquals(cards[0],deck.getDeck()[0]);



    }

    @Test
    //draw card after initial cards are set
    public void testDrawCardAfterBeginning(){
        deck.getInitialCards();
        cards=deck.getDeck();

        //check if next drawen card is the next card, which isn't owned
        assertEquals(cards[3],deck.drawCard());
    }

    @Test
    //draw card if all cards have been drawn once (current card >=size)
    public void testDrawCardIfCurrentIsLastCard(){
        deck=new Deck(context);

        //set index to last card
         deck.setCurrentCard(16);
         deck.drawCard();

         cards=deck.getDeck();

         //next card should be at index 1
         assertEquals(cards[1],deck.drawCard());
    }

    @Test
    public void testDrawCardIfLastCardIsOwnded(){
        deck=new Deck(context);
        cards=deck.getDeck();

        deck.setCurrentCard(15);
        deck.getSpecificCard(cards[15].getId()).setOwned(true);

        deck.drawCard();
        cards=deck.getDeck();

        assertEquals(cards[1],deck.drawCard());

    }

    @Test
    public void testGetSpecificCardLowerBound(){
        deck=new Deck(context);
        assertEquals(0,deck.getSpecificCard(0).getId());
    }

    @Test
    public void testGetSpecificCardAverage(){
        assertEquals(6,deck.getSpecificCard(6).getId());
    }

    @Test
    public void testGetSpecificCardUpperBound(){
        assertEquals(15,deck.getSpecificCard(15).getId());
    }

    @Test
    public void testGetSpecificCardOutOfBounds(){
        assertNull(deck.getSpecificCard(16));
    }

    @Test
    public void testLastCardPlayed(){
        deck.setLastCardPlayed(deck.getSpecificCard(0));
        assertEquals(0,deck.getLastCardPlayed().getId());
    }

    @Test
    public void testLastCardPlayedByOponent(){
        deck.setLastCardPlayedbyOponent(true);
        assertTrue(deck.isLastCardPlayedbyOponent());
    }

}
