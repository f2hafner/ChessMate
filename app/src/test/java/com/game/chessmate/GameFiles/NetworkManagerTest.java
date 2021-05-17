package com.game.chessmate.GameFiles;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NetworkManagerTest {

    NetworkManager manager;

    @Before
    public void init(){
        manager=NetworkManager.getInstance();
    }

  /*  @Test
    public void createSessionTest() {
        manager.createSession("");
    }

    @Test
    public void joinSessionTest(){
        manager.joinSession("");
    }*/
}
