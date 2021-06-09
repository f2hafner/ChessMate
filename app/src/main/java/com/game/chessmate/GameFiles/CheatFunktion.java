package com.game.chessmate.GameFiles;
import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.CheatFunktion;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Networking.NetObjects.PlayerDataObject;


public class CheatFunktion {

    private static boolean cheatFunction;

    Player player1;
    Player player2;



    public static void setCheatFunction(boolean cheatFunction) {
        CheatFunktion.cheatFunction = cheatFunction;
    }

    public static boolean isCheatFunction() {
        return cheatFunction;
    }
}
