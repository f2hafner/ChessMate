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
public class QueenTest {
    PlayingPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Resources resources;

//    @Mock
    Bitmap sprite;


    Queen queen;


    @Before
    public void init(){
        colour= PlayingPieceColour.WHITE;


        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        resources=Mockito.mock(Resources.class);
        sprite=null;

        queen=new Queen(position,resources,drawableId);//,colour);

        queen.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(PlayingPieceType.QUEEN,queen.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,queen.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(sprite,queen.getDrawable());
    }

    @Test
    public void getColour(){
        assertEquals(colour,queen.getColour());

        colour=PlayingPieceColour.BLACK;
        queen.setColor(colour);

        assertEquals(colour,queen.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,queen.getLegalFields());

    }*/

}
