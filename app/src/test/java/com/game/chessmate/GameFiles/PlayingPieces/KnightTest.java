package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.graphics.Bitmap;

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
public class KnightTest {
    ChessPieceColour colour;
    int drawableId;


    @Mock
    private Bitmap sprite;

    @Mock
    private Context context;

    @Mock
    private BoardView view;

    @Mock
    private Field field;

    Knight knight;
    //for legal moves tests
    ArrayList<String> expected;
    ChessBoard cb;
    Field[][] currentFields;


    @Before
    public void init(){
        colour= null;//ChessPieceColour.WHITE;
        context= Mockito.mock(Context.class);
        field= Mockito.mock(Field.class);
        sprite =Mockito.mock(Bitmap.class);

        field= Mockito.mock(Field.class);
        view = Mockito.mock(BoardView.class);
        drawableId= R.drawable.bishop_player1;

        knight=new Knight(field, sprite,context,null,colour);
        knight.setColor(colour);
        //for legal Moves tests
        expected = new ArrayList<>();
        cb = ChessBoard.getInstance();
        when(view.getContext()).thenReturn(null);
        cb.initChessBoard(view, 10);
        currentFields = cb.getBoardFields();
        knight.setCurrentPosition(field);

    }


    @Test
    public void returnPlayingPieceTypeTest() {
        assertEquals(ChessPieceType.KNIGHT,knight.getPlayingPieceType());
    }

    @Test
    public void getPositionTest(){
        assertEquals(field,knight.getPosition());
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

    //NOTE - in the testcase environment, the position of the black and white pieces is different than in the app. The position of the pieces (but not the chessboard) is changed as if the chessboard were rotated against the clock once - so black pieces are on the left and white pieces on the right.
    /*Testcases do include interaction with opponent, as interaction with knight jumps on specific fields that are not restricted by a blocking opponent
       testcases - one average testcase when piece is in the middle of the chessboard - legal moves should be restricted by pieces of same colour (later also by opponent),
       one testcase per chessboard border (4) - legal moves should be restricted by pieces of same colour and border (later also by opponent),
       one testcase per chessboard corner (4) - legal moves should be restricted by border twice and pieces
    */

    @Test
    public void getLegalFieldsAverageCaseTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(3);

        expected.add(currentFields[1][4].getFieldX() + ":" + currentFields[1][4].getFieldY());
        expected.add(currentFields[1][2].getFieldX() + ":" + currentFields[1][2].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());
        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());
        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[4][1].getFieldX() + ":" + currentFields[4][1].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }



    @Test
    public void getLegalFieldsUpperBorderTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(0);

        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());
        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());
        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[4][2].getFieldX() + ":" + currentFields[4][2].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }


    @Test
    public void getLegalFieldsLeftBorderTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(3);

        expected.add(currentFields[2][2].getFieldX() + ":" + currentFields[2][2].getFieldY());
        expected.add(currentFields[2][4].getFieldX() + ":" + currentFields[2][4].getFieldY());
        expected.add(currentFields[1][5].getFieldX() + ":" + currentFields[1][5].getFieldY());
        expected.add(currentFields[1][1].getFieldX() + ":" + currentFields[1][1].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsRightBorderTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(3);

        expected.add(currentFields[5][4].getFieldX() + ":" + currentFields[5][4].getFieldY());
        expected.add(currentFields[5][2].getFieldX() + ":" + currentFields[5][2].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsLowerBorderTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(7);

        expected.add(currentFields[1][6].getFieldX() + ":" + currentFields[1][6].getFieldY());
        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());
        expected.add(currentFields[2][5].getFieldX() + ":" + currentFields[2][5].getFieldY());
        expected.add(currentFields[4][5].getFieldX() + ":" + currentFields[4][5].getFieldY());


        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsLeftLowerCornerTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(7);

        expected.add(currentFields[2][6].getFieldX() + ":" + currentFields[2][6].getFieldY());
        expected.add(currentFields[1][5].getFieldX() + ":" + currentFields[1][5].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsRightLowerCornerTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(7);

        expected.add(currentFields[5][6].getFieldX() + ":" + currentFields[5][6].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsLeftUpperCornerTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(0);

        expected.add(currentFields[2][1].getFieldX() + ":" + currentFields[2][1].getFieldY());
        expected.add(currentFields[1][2].getFieldX() + ":" + currentFields[1][2].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }

    @Test
    public void getLegalFieldsRightUpperCornerTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(0);

        expected.add(currentFields[5][1].getFieldX() + ":" + currentFields[5][1].getFieldY());

        ArrayList<Field> temp = knight.getLegalFields();
        ArrayList<String> actual = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            actual.add(temp.get(i).getFieldX() + ":" + temp.get(i).getFieldY());
        }
        assertEquals(expected, actual);//-- to see mistake of this test
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));//better solution - order does not matter
    }


    @Test
    public void isChampionTest(){
        knight.setChampion();
        assertTrue(knight.isChampion());
    }

    @Test
    public void getLegalMovesChampionAverageCaseTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(3);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[0][7]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }



    @Test
    public void getLegalMovesChampionUpperBorderTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(0);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[0][4]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }


    @Test
    public void getLegalMovesChampionLeftBorderTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(3);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[4][6]);
        expected.add(currentFields[3][7]);
        expected.add(currentFields[4][0]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionRightBorderTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(3);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[3][6]);
        expected.add(currentFields[3][0]);
        expected.add(currentFields[4][7]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionLowerBorderTest(){
        when(field.getFieldX()).thenReturn(3);
        when(field.getFieldY()).thenReturn(7);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[0][3]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionLeftLowerCornerTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(7);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[3][4]);
        expected.add(currentFields[4][3]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionRightLowerCornerTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(7);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[4][3]);
        expected.add(currentFields[3][4]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionLeftUpperCornerTest(){
        when(field.getFieldX()).thenReturn(0);
        when(field.getFieldY()).thenReturn(0);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[4][3]);
        expected.add(currentFields[3][4]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void getLegalMovesChampionRightUpperCornerTest(){
        when(field.getFieldX()).thenReturn(7);
        when(field.getFieldY()).thenReturn(0);

        ArrayList<Field>expected=new ArrayList<>();
        knight.setChampion();

        expected.add(currentFields[3][4]);
        expected.add(currentFields[4][3]);

        assertEquals(expected.size(), knight.getLegalFields().size());
    }

    @Test
    public void testBlocked(){
        currentFields[6][7].setBlocked();
        assertTrue(knight.testJumpedFields(7,7,4,true,3,true,currentFields));

        currentFields[6][0].setBlocked();
        assertTrue(knight.testJumpedFields(7,0,4,true,3,false,currentFields));

        currentFields[1][0].setBlocked();
        assertTrue(knight.testJumpedFields(0,0,4,false,3,false,currentFields));

        currentFields[1][7].setBlocked();
        assertTrue(knight.testJumpedFields(0,7,4,false,3,true,currentFields));
    }

    @Test
    public void testLegalMovesRevelation(){
        //for black knights
        ArrayList<Field>expected=new ArrayList<>();
        expected.add(currentFields[0][2]);
        expected.add(currentFields[0][5]);

        assertEquals(expected.size(), knight.getLegalMovesRevelation().size());

        //for white knights
        knight.setColor(ChessPieceColour.WHITE);
        expected=new ArrayList<>();
        expected.add(currentFields[7][2]);
        expected.add(currentFields[7][5]);

        assertEquals(expected.size(),knight.getLegalMovesRevelation().size());
    }

}
