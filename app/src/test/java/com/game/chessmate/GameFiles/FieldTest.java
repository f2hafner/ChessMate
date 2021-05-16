package com.game.chessmate.GameFiles;

import android.content.Context;
import android.graphics.Rect;

import com.game.chessmate.GameFiles.PlayingPieces.Bishop;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldTest {
    Field field;

    @Mock
    Context context;

    @Mock
    ChessPiece bishop;

    @Mock
    Rect rectangle;



    @Before
    public void init(){
        context= Mockito.mock(Context.class);
        bishop=Mockito.mock(Bishop.class);
        rectangle=Mockito.mock(Rect.class);

        field = new Field(0, 0, context, null);
    }

    @Test
    public void hasPieceTrueTest() {
        field.setCurrentPiece(bishop);

        assertTrue(field.hasPiece());
    }

    @Test
    public void hasPieceFalseTest(){
        field.setCurrentPiece(null);
        assertFalse(field.hasPiece());
    }

    @Test
    public void getCurrentPieceTest(){
        field.setCurrentPiece(bishop);

        assertEquals(bishop,field.getCurrentPiece());

        field.setCurrentPiece(null);
        assertEquals(null,field.getCurrentPiece());
    }

    @Test
    public void getChessCoordinatesTest(){

        assertEquals("A1",field.getChessCoordinates());
    }

    @Test
    public void getXY(){
        assertEquals(0,field.getFieldX());
        assertEquals(0,field.getFieldY());
    }

  /*  @Test
    public void setLegalMoveColourTest(){
        field.setAsLegal();

        assertEquals(Color.YELLOW,field.color);
  */

}
