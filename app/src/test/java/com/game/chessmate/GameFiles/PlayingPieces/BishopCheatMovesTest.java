
package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.graphics.Bitmap;

import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.BoardView;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Player;
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
public class BishopCheatMovesTest {


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
        colour= null;//ChessPieceColour.WHITE;
        context= Mockito.mock(Context.class);
        field= Mockito.mock(Field.class);
        view = Mockito.mock(BoardView.class);

        sprite=Mockito.mock(Bitmap.class);
        drawableId= R.drawable.bishop_player1;

        bishop=new Bishop(field, sprite, context,null,colour);
        bishop.setColor(colour);


        //for cheat Moves tests
        expected = new ArrayList<>();
        cb = ChessBoard.getInstance();
        when(view.getContext()).thenReturn(null);
        cb.initChessBoard(view, 10);
        currentFields = cb.getBoardFields();
        bishop.setCurrentPosition(field);

    }


    @Test
    public void getCheatFieldsUpperBorderCaseTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(3);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][4].getFieldX() + ":" + currentFields[1][4].getFieldY());
        expected.add(currentFields[1][2].getFieldX() + ":" + currentFields[1][2].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }


    @Test
    public void getCheatFieldsAveragerCaseTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(3);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());
        expected.add(currentFields[1][5].getFieldX() + ":" + currentFields[1][5].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsRightBoarderCaseTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(0);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][2].getFieldX() + ":" + currentFields[1][2].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }


    @Test
    public void getCheatFieldsLeftBoarderCaseTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(7);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][5].getFieldX() + ":" + currentFields[1][5].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsLowerBoarderCaseTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(3);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsUpperRightCornerCaseTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(0);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());

        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsUpperLeftCornerCaseTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(7);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());

        expected.add(currentFields[1][6].getFieldX() + ":" + currentFields[1][6].getFieldY());

        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsLowerRightCornerCaseTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(0);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());



        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getCheatFieldsLowerLeftCornerCaseTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(7);


        expected.add(currentFields[2][0].getFieldX() + ":" + currentFields[2][0].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][3].getFieldX() + ":" + currentFields[2][3].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[2][7].getFieldX() + ":" + currentFields[2][7].getFieldY());

        expected.add(currentFields[3][0].getFieldX() + ":" + currentFields[3][0].getFieldY());
        expected.add(currentFields[3][1].getFieldX() + ":" + currentFields[3][1].getFieldY());
        expected.add(currentFields[3][2].getFieldX() + ":" + currentFields[3][2].getFieldY());
        expected.add(currentFields[3][3].getFieldX() + ":" + currentFields[3][3].getFieldY());
        expected.add(currentFields[3][4].getFieldX() + ":" + currentFields[3][4].getFieldY());
        expected.add(currentFields[3][5].getFieldX() + ":" + currentFields[3][5].getFieldY());
        expected.add(currentFields[3][6].getFieldX() + ":" + currentFields[3][6].getFieldY());
        expected.add(currentFields[3][7].getFieldX() + ":" + currentFields[3][7].getFieldY());

        expected.add(currentFields[4][0].getFieldX() + ":" + currentFields[4][0].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());
        expected.add(currentFields[4][3].getFieldX() + ":" + currentFields[4][3].getFieldY());
        expected.add(currentFields[4][4].getFieldX() + ":" + currentFields[4][4].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[4][6].getFieldX() + ":" + currentFields[4][6].getFieldY());
        expected.add(currentFields[4][7].getFieldX() + ":" + currentFields[4][7].getFieldY());

        expected.add(currentFields[5][0].getFieldX() + ":" + currentFields[5][0].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][3].getFieldX() + ":" + currentFields[5][3].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][5].getFieldX() + ":" + currentFields[5][5].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[5][7].getFieldX() + ":" + currentFields[5][7].getFieldY());


        ArrayList<Field> temp = bishop.getCheatFunctionMoves();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }
    }


