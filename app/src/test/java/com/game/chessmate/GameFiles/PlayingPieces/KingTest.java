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
public class KingTest {
    PlayingPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Resources resources;

   // //@Mock
    Bitmap sprite;


    King king;


    @Before
    public void init(){
        colour= PlayingPieceColour.WHITE;


        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        resources=Mockito.mock(Resources.class);
        sprite=null;

        king=new King(position,resources,drawableId);//,colour);

        king.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(PlayingPieceType.KING,king.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,king.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(king.getDrawable(),sprite);
    }

    @Test
    public void getColour(){
        assertEquals(colour,king.getColour());

        colour=PlayingPieceColour.BLACK;
        king.setColor(colour);

        assertEquals(colour,king.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,king.getLegalFields());

    }*/

}
