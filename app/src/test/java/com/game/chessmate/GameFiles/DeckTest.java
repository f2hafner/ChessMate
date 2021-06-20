package com.game.chessmate.GameFiles;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
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
        deck=new Deck(context);
        card= Mockito.mock(Card.class);

    }

    @Test
    public void testInitialCards(){

        //the first card of the initial card array should be the next card not owned
        cards=deck.getDeck();
        Card[]initial=deck.getInitialCards();

        assertEquals(cards[0],initial[0]);
    }

    @Test
    public void testSize(){
        assertEquals(16,deck.getSize());
        deck.setSize(26);
        assertEquals(26,deck.getSize());
    }

    @Test
    public void testShuffle(){
        Card [] cards=deck.getDeck();
        deck.shuffle();

        assertNotEquals(cards,deck);

    }

    @Test
    public void testDrawCard(){
        cards=deck.getDeck();

        //check if deck and reference deck are equal
        assertEquals(cards[0],deck.getDeck()[0]);

        deck.getInitialCards();

        //check if next drawen card is the next card, which isn't owned
        assertEquals(cards[3],deck.drawCard());

        //if the current card is the last card in the set, the next card drawn should be the first again
       // deck.setCurrentCard(15);
     //   assertNotEquals(cards[0],deck.drawCard());
    }
}
