package com.game.chessmate.GameFiles;
/** The GameHandler class handles gameRelated events and methodcalls
 */
public class GameHandler {
    // Thread-Save Singleton
    private static final class InstanceHolder { static final GameHandler INSTANCE = new GameHandler(); }
    public static GameHandler getInstance(){ return GameHandler.InstanceHolder.INSTANCE; }
    private GameHandler () {
        //TODO gameHandler CODE on initialize
    }

}
