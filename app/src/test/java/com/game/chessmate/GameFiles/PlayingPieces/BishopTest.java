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
import static org.junit.Assert.assertTrue;
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

    //for legal moves tests
    Bishop bishop;
    ArrayList<String> expected;
    ChessBoard cb;
    Field[][] currentFields;

    @Before
    public void init(){
        colour= ChessPieceColour.WHITE;
        context= Mockito.mock(Context.class);
        field= Mockito.mock(Field.class);
        view = Mockito.mock(BoardView.class);

        sprite=Mockito.mock(Bitmap.class);
        drawableId=R.drawable.bishop_player1;

        bishop=new Bishop(field, sprite, context,null,colour);
        bishop.setColor(colour);


        //for legal Moves tests
        expected = new ArrayList<>();
        cb = ChessBoard.getInstance();
        when(view.getContext()).thenReturn(null);
        cb.initChessBoard(view, 10);
        currentFields = cb.getBoardFields();
        bishop.setCurrentPosition(field);

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
    //NOTE - all test seem to fail and have very unexpected actual results.
    // However, if the testcases are manually tested via the app, then there are no problems in the legalMoves()-method. It seems like the testing environment or something is not set up correctly yet


    /*Testcases do not inlcude interaction with opponent yet, as interaction with opponent has not been developed yet
       testcases - one average testcase when piece is in the middle of the chessboard - legal moves should be restricted by pieces of same colour (later also by opponent),
       one testcase per chessboard border left and right (2) - legal moves should be restricted by pieces of same colour and border (later also by opponent),
       one testcase per chessboard corner of own side (2) - legal moves should be restricted by border twice and own pieces - legal moves should be zero
       one testcase per chessboard corner of own side (2) with restricting piece of own colour moved - legal moves should only be restricted by borders twice (and later opponent)
    */
    @Test
    public void getLegalFieldsAverageCaseTest(){//-- test case fails because of one faulty fields 6,0 expected, 0,6 actual
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(3);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());
        expected.add(currentFields[0][0].getFieldX() + ":" + currentFields[0][0].getFieldY());//must be removed once opponent functionality exists - not a legal move field anymore as there is an opponent on it
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[1][5].getFieldX() + ":" + currentFields[1][5].getFieldY());
        expected.add(currentFields[6][0].getFieldX() + ":" + currentFields[6][0].getFieldY());//must be removed once opponent functionality exists -- legal Moves does not work??? - tests seem to fail but execution with app works!!

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }



    @Test
    public void getLegalFieldsRightBorderTest(){//-- test fails because actual seems to be empty
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(3);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        expected.add(currentFields[6][2].getFieldX() + ":" + currentFields[6][2].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());//must be removed once opponent functionality exists
        expected.add(currentFields[6][4].getFieldX() + ":" + currentFields[6][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }


    @Test
    public void getLegalFieldsLeftBorderTest(){// -- test fails because own pieces are not restricting
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(3);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        expected.add(currentFields[1][4].getFieldX() + ":" + currentFields[1][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[1][2].getFieldX() + ":" + currentFields[1][2].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());//must be removed once opponent functionality exists

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsLeftCornerTest(){//-- test fails because actual seems to not be restricted by own colour but by opponent??
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(7);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsRightCornerTest(){//-- somehow works?? why not left corner?
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(7);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }



    @Test
    public void getLegalFieldsLeftCorner_PieceMovedTest(){//-- would only be correct if opponent restriction were to already exist? This testcase is restricted by opponents pieces? The others arent?
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(7);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        currentFields[1][6].getCurrentPiece().move(currentFields[1][5]);

        expected.add(currentFields[1][6].getFieldX() + ":" + currentFields[1][6].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[6][1].getFieldX() + ":" + currentFields[6][1].getFieldY());
        expected.add(currentFields[7][0].getFieldX() + ":" + currentFields[7][0].getFieldY());//must be removed once opponent functionality exists

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsRightCorner_PieceMovedTest(){//actual seems to be empty again. Just like right border test.. :(
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(7);
        //when(field.getCurrentPiece()).thenReturn(null); //-- ??

        currentFields[6][6].getCurrentPiece().move(currentFields[6][5]);

        expected.add(currentFields[6][6].getFieldX() + ":" + currentFields[6][6].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());
        expected.add(currentFields[0][0].getFieldX() + ":" + currentFields[0][0].getFieldY());//must be removed once opponent functionality exists

        ArrayList<Field> temp = bishop.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }
}
