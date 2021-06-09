package com.game.chessmate.GameFiles;
import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.CheatFunktion;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Networking.NetObjects.PlayerDataObject;


public class CheatFunktion {

    private static boolean cheatFunction;
    private int playerLocalWrongCheatReveal = 0;
    private int playerEnemyWrongCheatReveal = 0;

    Player playerLocal = ChessBoard.getInstance().getLocalPlayer();
    Player playerEnemy = ChessBoard.getInstance().getEnemyPlayer();


    public  void playerDidCheat() {
        Field startPosition = ChessBoard.getInstance().getStartPossition();
        if (playerEnemy.getCheatOn() && !ChessBoard.getInstance().getwasMoveLegal()) {
            ChessBoard.getInstance().getMovedPiece().move(startPosition);
        } else if (playerLocal.getCheatOn() && !ChessBoard.getInstance().getwasMoveLegal())
        ChessBoard.getInstance().getMovedPiece().move(startPosition);
    }

    public  void playerDidNotCheat(){
        if (playerEnemy.getCheatOn() && ChessBoard.getInstance().getwasMoveLegal()) {
            playerLocalWrongCheatReveal++;
        }
        if (playerLocal.getCheatOn() && ChessBoard.getInstance().getwasMoveLegal()) {
            playerEnemyWrongCheatReveal++;
        }


    }



    public static void setCheatFunction(boolean cheatFunction) {
        CheatFunktion.cheatFunction = cheatFunction;
    }

    public static boolean isCheatFunction() {
        return cheatFunction;
    }
}
