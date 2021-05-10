package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.game.chessmate.GameFiles.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PawnTest {
    PlayingPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Resources resources;

 //   @Mock
    Bitmap sprite;


    Pawn pawn;


    @Before
    public void init(){
        colour= PlayingPieceColour.WHITE;


        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        resources=Mockito.mock(Resources.class);
        sprite=null;

        pawn=new Pawn(position,resources,drawableId,colour);

        pawn.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(PlayingPieceType.PAWN,pawn.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,pawn.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(sprite,pawn.getDrawable());
    }

    @Test
    public void getColour(){
        assertEquals(colour,pawn.getColour());

        colour=PlayingPieceColour.BLACK;
        pawn.setColor(colour);

        assertEquals(colour,pawn.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,pawn.getLegalFields());

    }*/

}
