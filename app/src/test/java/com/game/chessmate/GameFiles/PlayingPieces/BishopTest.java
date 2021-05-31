package com.game.chessmate.GameFiles.PlayingPieces;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.game.chessmate.GameFiles.BoardView;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BishopTest {
    ChessPieceColour colour;

    @Mock
    private Field field;

    @Mock
    private BoardView view;

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
        field= Mockito.mock(Field.class);
        view = Mockito.mock(BoardView.class);
        //chessboard = Mockito.mock(ChessBoard.class);
        //when(chessboard.getBoardFields())

        sprite=Mockito.mock(Bitmap.class);
        drawableId=R.drawable.bishop_player1;

        bishop=new Bishop(field, sprite, context,null,colour);
        bishop.setColor(colour);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.BISHOP,bishop.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(field,bishop.getPosition());
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
        ArrayList<Field> createdList = new ArrayList<>();
        ChessBoard cb = ChessBoard.getInstance();
        when(view.getContext()).thenReturn(null);
        cb.initChessBoard(view, 10);
        Field[][] currentFields = cb.getBoardFields();
        bishop.setCurrentPosition(field);

        /*Testcases do not inlcude interaction with opponent yet, as interaction with opponent has not been developed yet
        testcases - one normal testcase when piece is in the middle of the chessboard - legal moves should be restricted by pieces of same colour (later also by opponent),
        one testcase per chessboard border left and right (2) - legal moves should be restricted by pieces of same colour and border (later also by opponent),
        one testcase per chessboard corner of own side (2) - legal moves should be restricted by border twice and own pieces - legal moves should be zero
        one testcase per chessboard corner of own side (2) with restricting piece of own colour moved - legal moves should only be restricted by borders twice (and later opponent)
         */
        //normal testcase
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(3);
        when(field.getCurrentPiece()).thenReturn(null);


        //chessBoard is not mocked, as there is only one instance of it that is worked with the whole time, so nothing of ....getCurrentPiece() and ...getCurrentPiece().getColour() is mocked. Furthermore, they are only getters
        createdList.add(currentFields[0][0]);//must be removed once opponent functionality exists - not a legal move field anymore as there is an opponent on it
        createdList.add(currentFields[1][1]);
        createdList.add(currentFields[2][2]);
        createdList.add(currentFields[4][4]);
        createdList.add(currentFields[5][5]);
        createdList.add(currentFields[4][2]);
        createdList.add(currentFields[1][5]);
        createdList.add(currentFields[2][4]); //does the order matter??
        assertEquals(createdList,bishop.getLegalFields());


        //left border testcase
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldX()).thenReturn(3);

        createdList.add(currentFields[6][2]);
        createdList.add(currentFields[5][1]);
        createdList.add(currentFields[4][0]);//must be removed once opponent functionality exists
        createdList.add(currentFields[6][4]);
        createdList.add(currentFields[5][5]);
        assertEquals(createdList,bishop.getLegalFields());


        //right border testcase
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldX()).thenReturn(3);

        createdList.add(currentFields[1][2]);
        createdList.add(currentFields[2][1]);
        createdList.add(currentFields[3][0]);//must be removed once opponent functionality exists
        createdList.add(currentFields[1][4]);
        createdList.add(currentFields[2][5]);
        assertEquals(createdList,bishop.getLegalFields());


        //left corner testcase - no piece moved
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldX()).thenReturn(7);
        assertEquals(createdList,bishop.getLegalFields());


        //right corner testcase - no piece moved
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldX()).thenReturn(7);
        assertEquals(createdList,bishop.getLegalFields());


        //left corner testcase - piece moved
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldX()).thenReturn(7);
        currentFields[1][6].getCurrentPiece().move(currentFields[1][5]);
        createdList.add(currentFields[1][6]);
        createdList.add(currentFields[2][5]);
        createdList.add(currentFields[3][4]);
        createdList.add(currentFields[4][3]);
        createdList.add(currentFields[5][2]);
        createdList.add(currentFields[6][1]);
        createdList.add(currentFields[7][0]);//must be removed once opponent functionality exists
        assertEquals(createdList,bishop.getLegalFields());

        //right corner testcase - piece moved
        createdList = new ArrayList<>();
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldX()).thenReturn(7);
        currentFields[6][6].getCurrentPiece().move(currentFields[6][5]);
        createdList.add(currentFields[6][6]);
        createdList.add(currentFields[5][5]);
        createdList.add(currentFields[4][4]);
        createdList.add(currentFields[3][3]);
        createdList.add(currentFields[2][2]);
        createdList.add(currentFields[1][1]);
        createdList.add(currentFields[0][0]);//must be removed once opponent functionality exists
        assertEquals(createdList,bishop.getLegalFields());
    }
}
