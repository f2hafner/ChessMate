package com.game.chessmate.GameFiles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class DeckTest {
    Deck deck;

    @Mock
    Card card;

    Card [] cards;

    @Before
    public void init(){
        deck=new Deck();
        card= Mockito.mock(Card.class);

    }

    @Test
    public void testInitialCards(){

        cards=deck.getInitialCards();

        assertEquals("Cowardice",cards[0].getName());
        assertEquals("Crusade",cards[1].getName());
        assertEquals("Dark Mirror",cards[2].getName());
    }

    @Test
    public void testSize(){
        assertEquals(25,deck.getSize());
        deck.setSize(26);
        assertEquals(26,deck.getSize());
    }

    @Test
    public void testShuffle(){
        deck.shuffle();
        assertNotEquals("Cowardice",deck.getDeck()[0].getName());
    }

    @Test
    public void testDrawCard(){
        assertEquals("Cowardice",deck.drawCard().getName());
        deck.getDeck()[0].setOwned();
        assertNotEquals("Cowardice",deck.drawCard().getName());

        deck.setCurrentCard(25);
        assertNotEquals("Cowardice",deck.drawCard());
    }
}
