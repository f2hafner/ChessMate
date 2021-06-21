package com.game.chessmate.GameFiles;


import android.content.Context;

import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceType;
import com.game.chessmate.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardTest {
    String n0="Cowardice";
    String n1="Dark Mirror";
    String n2="Death Dance";
    String n3="Disintegration";
    String n4="Champion";
    String n5="Rebirth";
    String n6="Revelation";
    String n7="Long Jump";
    String n8="Lost Castle";
    String n9="Mystic Shield";
    String n10="Forbidden City";
    String n11="Holy Quest";
    String n12="Vulture";
    String n13="Crusade";
    String n14="Man of Straw";
    String n15="Bombard";
    String n16="Spoils of War";
    String n17="Martyr";
    String n18="Funeral Pyre";
    String n19="Hand of Fate";
    String n20="Abduction";
    String n21="Fog of War";
    String n22="Think Again";
    String n23="Coup";
    String n24="Confabulation";

    int id0= R.drawable.cowardice;
    int id1=R.drawable.dark_mirror;
    int id2=R.drawable.death_dance;
    int id3=R.drawable.disintegration;
    int id4=R.drawable.champion;
    int id5=R.drawable.rebirth;
    int id6=R.drawable.revelation;
    int id7=R.drawable.long_jump;
    int id8=R.drawable.lost_castle;
    int id9=R.drawable.mystic_shield;
    int id10=R.drawable.forbidden_city;
    int id11=R.drawable.holy_quest;
    int id12=R.drawable.vulture;
    int id13=R.drawable.crusade;
    int id14=R.drawable.man_of_straw;
    int id15=R.drawable.bombard;
    int id16=R.drawable.spoils_of_war;
    int id17=R.drawable.martyr;
    int id18=R.drawable.funeral_pyre;
    int id19=R.drawable.hand_of_fate;
    int id20=R.drawable.abduction;
    int id21=R.drawable.fog_of_war;
    int id22=R.drawable.think_again;
    int id23=R.drawable.coup;
    int id24=R.drawable.confabulation;

    Card card;
    ArrayList<Field> expected;
    ChessBoard board;
    Field[][] currentFields;

    @Mock
    private Context context;

    @Mock
    private Field field1;

    @Mock
    private BoardView view;

    @Mock
    private ChessPiece piece1;


    @Before
    public void init(){
        context= Mockito.mock(Context.class);
        field1=Mockito.mock(Field.class);
        piece1=Mockito.mock(ChessPiece.class);

        card=new Card(0,context);
        expected=new ArrayList<>();
        board=ChessBoard.getInstance();
        when(view.getContext()).thenReturn(null);
        board.initChessBoard(view, 10);
        currentFields = board.getBoardFields();
    }

    @Test
    public void testInit(){

        card=new Card(0,context);
        assertEquals(n0,card.getName());

        card=new Card(1,context);
        assertEquals(n1,card.getName());

        card=new Card(2,context);
        assertEquals(n2,card.getName());

        card=new Card(3,context);
        assertEquals(n3,card.getName());

        card=new Card(4,context);
        assertEquals(n4,card.getName());

        card=new Card(5,context);
        assertEquals(n5,card.getName());

        card=new Card(6,context);
        assertEquals(n6,card.getName());

        card=new Card(7,context);
        assertEquals(n7,card.getName());

        card=new Card(8,context);
        assertEquals(n8,card.getName());

        card=new Card(9,context);
        assertEquals(n9,card.getName());

        card=new Card(10,context);
        assertEquals(n10,card.getName());

        card=new Card(11,context);
        assertEquals(n11,card.getName());

        card=new Card(12,context);
        assertEquals(n12,card.getName());

        card=new Card(13,context);
        assertEquals(n13,card.getName());

        card=new Card(14,context);
        assertEquals(n14,card.getName());

        card=new Card(15,context);
        assertEquals(n15,card.getName());

        card=new Card(16,context);
        assertEquals(n16,card.getName());

        card=new Card(17,context);
        assertEquals(n17,card.getName());

        card=new Card(18,context);
        assertEquals(n18,card.getName());

        card=new Card(19,context);
        assertEquals(n19,card.getName());

        card=new Card(20,context);
        assertEquals(n20,card.getName());

        card=new Card(21,context);
        assertEquals(n21,card.getName());

        card=new Card(22,context);
        assertEquals(n22,card.getName());

        card=new Card(23,context);
        assertEquals(n23,card.getName());

        card=new Card(24,context);
        assertEquals(n24,card.getName());
    }

    @Test
    public void testInitFailure(){
        assertThrows(IllegalArgumentException.class, () -> new Card(25,context));
    }

    @Test
    public void testContinuingTillEnd(){
        card=new Card(23,context);
        assertTrue(card.isContinuingUntilEnd());

        card=new Card(0,context);
        assertFalse(card.isContinuingUntilEnd());
    }

    @Test
    public void testContinuingTillCaptured(){
        card=new Card(24,context);
        assertTrue(card.isContinuingUntilCaptured());

        card=new Card(0,context);
        assertFalse(card.isContinuingUntilCaptured());
    }

    @Test
    public void testGetDesc(){
        assertEquals("Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.",card.getDesc());
    }

    @Test
    public void testGetUseCase(){
        assertEquals("[i] Play this card instead of your move",card.getUseCase());
    }

    @Test
    public void testOwned(){
        assertFalse(card.isOwned());

        card.setOwned(true);

        assertTrue(card.isOwned());
    }

    @Test
    public void testGetId(){
        card=new Card(0,context);
        assertEquals(0,card.getId());

        card=new Card(10,context);
        assertEquals(10,card.getId());

        card=new Card(15,context);
        assertEquals(15,card.getId());

        card=new Card(20,context);
        assertEquals(20,card.getId());

        card=new Card(24,context);
        assertEquals(24,card.getId());
    }

    @Test
    public void getDrawableTest(){
        card=new Card(0,context);
        assertEquals(id0,card.getDrawableId());

        card=new Card(1,context);
        assertEquals(id1,card.getDrawableId());

        card=new Card(2,context);
        assertEquals(id2,card.getDrawableId());

        card=new Card(3,context);
        assertEquals(id3,card.getDrawableId());

        card=new Card(4,context);
        assertEquals(id4,card.getDrawableId());

        card=new Card(5,context);
        assertEquals(id5,card.getDrawableId());

        card=new Card(6,context);
        assertEquals(id6,card.getDrawableId());

        card=new Card(7,context);
        assertEquals(id7,card.getDrawableId());

        card=new Card(8,context);
        assertEquals(id8,card.getDrawableId());

        card=new Card(9,context);
        assertEquals(id9,card.getDrawableId());

        card=new Card(10,context);
        assertEquals(id10,card.getDrawableId());

        card=new Card(11,context);
        assertEquals(id11,card.getDrawableId());

        card=new Card(12,context);
        assertEquals(id12,card.getDrawableId());

        card=new Card(13,context);
        assertEquals(id13,card.getDrawableId());

        card=new Card(14,context);
        assertEquals(id14,card.getDrawableId());

        card=new Card(15,context);
        assertEquals(id15,card.getDrawableId());

        card=new Card(16,context);
        assertEquals(id16,card.getDrawableId());

        card=new Card(17,context);
        assertEquals(id17,card.getDrawableId());

        card=new Card(18,context);
        assertEquals(id18,card.getDrawableId());

        card=new Card(19,context);
        assertEquals(id19,card.getDrawableId());

        card=new Card(20,context);
        assertEquals(id20,card.getDrawableId());

        card=new Card(21,context);
        assertEquals(id21,card.getDrawableId());

        card=new Card(22,context);
        assertEquals(id22,card.getDrawableId());

        card=new Card(23,context);
        assertEquals(id23,card.getDrawableId());

        card=new Card(24,context);
        assertEquals(id24,card.getDrawableId());
    }

    @Test
    public void cowardiceTestLowerCase(){
        when(field1.getFieldX()).thenReturn(0);

        //should be empty, since index is out of bounds
        assertEquals(expected,card.cowardice(1,piece1,field1));
    }

    @Test
    public void cowardiceTestAverageCase(){
        when(field1.getFieldX()).thenReturn(4);
        when(field1.getFieldY()).thenReturn(4);

        expected.add(currentFields[3][4]);
        expected.add(currentFields[2][4]);

        assertEquals(expected,card.cowardice(1,piece1,field1));
    }

    @Test
    public void cowardiceTestUpperCase(){
        when(field1.getFieldX()).thenReturn(7);
        when(field1.getFieldY()).thenReturn(4);

        //should be empty, since there are pieces in the way
        assertEquals(expected,card.cowardice(1,piece1,field1));
    }

    @Test
    public void darkMirrorTestLowerCase(){
        when(field1.getFieldX()).thenReturn(0);
        when(field1.getFieldY()).thenReturn(4);

        expected.add(currentFields[1][3]);
        expected.add(currentFields[1][5]);


        //there should be two pawns to beat
        assertEquals(expected,card.darkMirror(1,piece1,field1));
    }

    @Test
    public void darkMirrorTestAverageCase(){
        when(field1.getFieldX()).thenReturn(4);
        when(field1.getFieldY()).thenReturn(4);


        //should be empty, since there are no pieces to capture
        assertEquals(expected,card.darkMirror(1,piece1,field1));
    }

    @Test
    public void darkMirrorTestUpperBound(){
        when(field1.getFieldX()).thenReturn(7);

        //should be empty, since index out of bounds
        assertEquals(expected,card.darkMirror(1,piece1,field1));
    }

    @Test
    public void rebirthTestPawn(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.PAWN);

        expected.add(currentFields[1][0]);
        expected.add(currentFields[1][1]);
        expected.add(currentFields[1][2]);
        expected.add(currentFields[1][3]);
        expected.add(currentFields[1][4]);
        expected.add(currentFields[1][5]);
        expected.add(currentFields[1][6]);
        expected.add(currentFields[1][7]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void rebirthTestRook(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.ROOK);

        expected.add(currentFields[0][0]);
        expected.add(currentFields[0][7]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void rebirthTestBishop(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.BISHOP);

        expected.add(currentFields[0][2]);
        expected.add(currentFields[0][5]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void rebirthTestKnight(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.KNIGHT);

        expected.add(currentFields[0][1]);
        expected.add(currentFields[0][6]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void rebirthTestKing(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.KING);

        expected.add(currentFields[0][4]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void rebirthTestQueen(){
        when(piece1.getPlayingPieceType()).thenReturn(ChessPieceType.QUEEN);

        expected.add(currentFields[0][3]);

        assertEquals(expected,card.rebirth(1,piece1,null));
    }

    @Test
    public void longJumpTestBlackField(){
        when(piece1.getPosition()).thenReturn(field1);
        when(field1.isEven()).thenReturn(true);

        expected.add(currentFields[2][1]);
        expected.add(currentFields[2][3]);
        expected.add(currentFields[2][5]);
        expected.add(currentFields[2][7]);
        expected.add(currentFields[3][1]);
        expected.add(currentFields[3][3]);
        expected.add(currentFields[3][5]);
        expected.add(currentFields[3][7]);
        expected.add(currentFields[4][1]);
        expected.add(currentFields[4][3]);
        expected.add(currentFields[4][5]);
        expected.add(currentFields[4][7]);
        expected.add(currentFields[5][1]);
        expected.add(currentFields[5][3]);
        expected.add(currentFields[5][5]);
        expected.add(currentFields[5][7]);

        assertEquals(expected.size(),card.longJump(1,piece1,null).size());
    }

    @Test
    public void longJumpTestWhiteField(){
        when(piece1.getPosition()).thenReturn(field1);
        when(field1.isEven()).thenReturn(false);

        expected.add(currentFields[2][0]);
        expected.add(currentFields[2][2]);
        expected.add(currentFields[2][4]);
        expected.add(currentFields[2][6]);
        expected.add(currentFields[3][0]);
        expected.add(currentFields[3][2]);
        expected.add(currentFields[3][4]);
        expected.add(currentFields[3][6]);
        expected.add(currentFields[4][0]);
        expected.add(currentFields[4][2]);
        expected.add(currentFields[4][4]);
        expected.add(currentFields[4][6]);
        expected.add(currentFields[5][0]);
        expected.add(currentFields[5][2]);
        expected.add(currentFields[5][4]);
        expected.add(currentFields[5][6]);

        assertEquals(expected.size(),card.longJump(1,piece1,null).size());
    }


}
