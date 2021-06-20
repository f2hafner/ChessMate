package com.game.chessmate.GameFiles;


import android.content.Context;

import androidx.core.app.RemoteInput;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class CardTest {
    String n0="Cowardice";
    String n1="Dark Mirror";
    String n2="Death Dance";
    String n3="Disintegration";
    String n4="Champion";
    String n5="Rebirth";
    String n6="Revelation";
    String n7="Long Jump";
    String n8="Lost Castle";
    String n9="Mystic Shield";
    String n10="Forbidden City";
    String n11="Holy Quest";
    String n12="Vulture";
    String n13="Crusade";
    String n14="Man of Straw";
    String n15="Bombard";
    String n16="Spoils of War";
    String n17="Martyr";
    String n18="Funeral Pyre";
    String n19="Hand of Fate";
    String n20="Abduction";
    String n21="Fog of War";
    String n22="Think Again";
    String n23="Coup";
    String n24="Confabulation";

    Card card;

    @Mock
    private Context context;

    @Before
    public void init(){
        context= Mockito.mock(Context.class);
        card=new Card(0,context);
    }

    @Test
    public void testInit(){

        card=new Card(0,context);
        assertEquals(n0,card.getName());

        card=new Card(1,context);
        assertEquals(n1,card.getName());

        card=new Card(2,context);
        assertEquals(n2,card.getName());

        card=new Card(3,context);
        assertEquals(n3,card.getName());

        card=new Card(4,context);
        assertEquals(n4,card.getName());

        card=new Card(5,context);
        assertEquals(n5,card.getName());

        card=new Card(6,context);
        assertEquals(n6,card.getName());

        card=new Card(7,context);
        assertEquals(n7,card.getName());

        card=new Card(8,context);
        assertEquals(n8,card.getName());

        card=new Card(9,context);
        assertEquals(n9,card.getName());

        card=new Card(10,context);
        assertEquals(n10,card.getName());

        card=new Card(11,context);
        assertEquals(n11,card.getName());

        card=new Card(12,context);
        assertEquals(n12,card.getName());

        card=new Card(13,context);
        assertEquals(n13,card.getName());

        card=new Card(14,context);
        assertEquals(n14,card.getName());

        card=new Card(15,context);
        assertEquals(n15,card.getName());

        card=new Card(16,context);
        assertEquals(n16,card.getName());

        card=new Card(17,context);
        assertEquals(n17,card.getName());

        card=new Card(18,context);
        assertEquals(n18,card.getName());

        card=new Card(19,context);
        assertEquals(n19,card.getName());

        card=new Card(20,context);
        assertEquals(n20,card.getName());

        card=new Card(21,context);
        assertEquals(n21,card.getName());

        card=new Card(22,context);
        assertEquals(n22,card.getName());

        card=new Card(23,context);
        assertEquals(n23,card.getName());

        card=new Card(24,context);
        assertEquals(n24,card.getName());
    }

    @Test
    public void testInitFailure(){
        assertThrows(IllegalArgumentException.class, () -> {new Card(25,context);});
    }

    @Test
    public void testContinuingTillEnd(){
        card=new Card(23,context);
        assertTrue(card.isContinuingUntilEnd());

        card=new Card(0,context);
        assertFalse(card.isContinuingUntilEnd());
    }

    @Test
    public void testContinuingTillCaptured(){
        card=new Card(24,context);
        assertTrue(card.isContinuingUntilCaptured());

        card=new Card(0,context);
        assertFalse(card.isContinuingUntilCaptured());
    }

    @Test
    public void testGetDesc(){
        assertEquals("Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.",card.getDesc());
    }

    @Test
    public void testGetUseCase(){
        assertEquals("[i] Play this card instead of your move",card.getUseCase());
    }

    @Test
    public void testOwned(){
        assertFalse(card.isOwned());

        card.setOwned(true);

        assertTrue(card.isOwned());
    }
}
