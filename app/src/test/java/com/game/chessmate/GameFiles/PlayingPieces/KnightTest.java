package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
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
    ChessPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Bitmap sprite;

    @Mock
    private Context context;

    Knight knight;


    @Before
    public void init(){
        colour= ChessPieceColour.WHITE;

        context= Mockito.mock(Context.class);
        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        sprite=Mockito.mock(Bitmap.class);


        knight=new Knight(position,sprite,context,null,colour);

        knight.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.KNIGHT,knight.getPlayingPieceType());
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

        colour= ChessPieceColour.BLACK;
        knight.setColor(colour);

        assertEquals(colour,knight.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,knight.getLegalFields());

    }*/

}
