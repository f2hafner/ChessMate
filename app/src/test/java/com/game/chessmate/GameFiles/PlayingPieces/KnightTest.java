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
public class KnightTest {
    PlayingPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Resources resources;

  //  @Mock
    Bitmap sprite;


    Knight knight;


    @Before
    public void init(){
        colour= PlayingPieceColour.WHITE;


        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        resources=Mockito.mock(Resources.class);
        sprite=null;

        knight=new Knight(position,resources,drawableId);//,colour);

        knight.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(PlayingPieceType.KNIGHT,knight.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,knight.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(sprite,knight.getDrawable());
    }

    @Test
    public void getColour(){
        assertEquals(colour,knight.getColour());

        colour=PlayingPieceColour.BLACK;
        knight.setColor(colour);

        assertEquals(colour,knight.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,knight.getLegalFields());

    }*/

}
