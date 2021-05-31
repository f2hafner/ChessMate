package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
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
    ChessPieceColour colour;
    int drawableId;

    @Mock
    private Field position;

    @Mock
    private Bitmap sprite;

    @Mock
    private Context context;

    Pawn pawn;


    @Before
    public void init(){
        colour= ChessPieceColour.WHITE;

        context= Mockito.mock(Context.class);
        position= Mockito.mock(Field.class);
        //       when(position.getX()).thenReturn(0);
        //     when(position.getY()).thenReturn(0);

        sprite=Mockito.mock(Bitmap.class);

        pawn=new Pawn(position,sprite,context,null,colour);

        pawn.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.PAWN,pawn.getPlayingPieceType());
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

        colour= ChessPieceColour.BLACK;
        pawn.setColor(colour);

        assertEquals(colour,pawn.getColour());
    }

    /*@Test
    public void getLegalFieldsTest(){


        ArrayList<Field> list=new ArrayList<>();

        assertEquals(list,pawn.getLegalFields());

    }*/

}
