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
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RookTest {
    ChessPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Bitmap sprite;

    @Mock
    private Context context;

    Rook rook;


    @Before
    public void init(){
        colour= ChessPieceColour.WHITE;

        context= Mockito.mock(Context.class);
        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        sprite=Mockito.mock(Bitmap.class);
        sprite=null;

        rook=new Rook(position,sprite,context,null,colour);

        rook.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.ROOK,rook.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,rook.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(rook.getDrawable(),sprite);
    }

    @Test
    public void getColour(){
        assertEquals(colour,rook.getColour());

        colour= ChessPieceColour.BLACK;
        rook.setColor(colour);

        assertEquals(colour,rook.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,rook.getLegalFields());

    }*/

}
