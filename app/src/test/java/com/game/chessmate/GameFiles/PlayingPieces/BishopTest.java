package com.game.chessmate.GameFiles.PlayingPieces;


import android.content.Context;
import android.graphics.Bitmap;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BishopTest {
    ChessPieceColour colour;

    @Mock
    private Field position;

    @Mock
    private Bitmap sprite;

    @Mock
    private Context context;
    int drawableId;

    Bishop bishop;

    @Before
    public void init(){
        colour= ChessPieceColour.WHITE;

        context= Mockito.mock(Context.class);
        position= Mockito.mock(Field.class);
        //when(position.getX()).thenReturn(0);
        //when(position.getY()).thenReturn(0);

        sprite=Mockito.mock(Bitmap.class);
        drawableId=R.drawable.bishop_player1;

        bishop=new Bishop(position, sprite, context,null,colour);
        bishop.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.BISHOP,bishop.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(position,bishop.getPosition());
    }

    @Test
    public void getDrawableTest(){
        assertEquals(sprite,bishop.getDrawable());
    }

    @Test
    public void getColour(){
        assertEquals(colour,bishop.getColour());

        colour= ChessPieceColour.BLACK;
        bishop.setColor(colour);

        assertEquals(colour,bishop.getColour());
    }

    @Test
    public void getLegalFieldsTest(){
        /*
        ArrayList<Field> createdList = new ArrayList<>();
        ChessBoard cb = ChessBoard.getInstance();

        testcases - one normal testcase when piece is in the middle of the chessboard,
        one testcase for each chessboard border (4),
        one testcase for each chessboard corner (4)

        //normal testcase
        when(position.getFieldX()).thenReturn(4);
        when(position.getFieldY()).thenReturn(4);
        bishop.setCurrentPosition(position);

        //field.getCurrentPiece() muss auch jedes mal gemockt werden und field.getCurrentPiece.getColour() vllt auch damit man einmal schwarz einmal weiß hat und die dinge dann nicht in der ArrayList sind

        //assertEquals(createdList,bishop.getLegalFields());

        //assertEquals(createdList,bishop.getLegalFields());
        */
    }
}
