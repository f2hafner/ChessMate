package com.game.chessmate.GameFiles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ChessBoardTest {

    ChessBoard chessBoard;

    @Mock
    Field field;

    @Before
    public void init(){
        chessBoard.getInstance();
        field= Mockito.mock(Field.class);
    }

    @Test
    public void exampleTest(){

    }
}
