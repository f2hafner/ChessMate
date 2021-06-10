package com.game.chessmate.GameFiles;

import android.content.Context;
import android.widget.Toast;

public class CheatFunktion {

    public CheatFunktion(Context context){
        this.context = context;
    }

    private static boolean cheatFunction;
    private int playerLocalWrongCheatReveal = 0;
    private int playerEnemyWrongCheatReveal = 0;
    private static Context  context;

    Player playerLocal = ChessBoard.getInstance().getLocalPlayer();
    Player playerEnemy = ChessBoard.getInstance().getEnemyPlayer();


    public void playerDidCheat() {
        Field startPosition = ChessBoard.getInstance().getStartPossition();
        if (playerEnemy.getCheatOn() && !ChessBoard.getInstance().getwasMoveLegal()) {
            ChessBoard.getInstance().getMovedPiece().move(startPosition);
        } else if (playerLocal.getCheatOn() && !ChessBoard.getInstance().getwasMoveLegal())
            ChessBoard.getInstance().getMovedPiece().move(startPosition);
    }

    public void playerDidNotCheat() {
        if (playerLocalWrongCheatReveal <= 3) {
            if (playerEnemy.getCheatOn() && ChessBoard.getInstance().getwasMoveLegal()) {
                playerLocalWrongCheatReveal++;
            } else {    Toast.makeText(context, "You wrongly accused the other player of cheating more than 3 times", Toast.LENGTH_SHORT).show();
            }
        }
        if (playerEnemyWrongCheatReveal <= 3) {
            if (playerLocal.getCheatOn() && ChessBoard.getInstance().getwasMoveLegal()) {
                playerEnemyWrongCheatReveal++;
            } else {
                  Toast.makeText(context, "You wrongly accused the other player of cheating more than 3 times", Toast.LENGTH_SHORT).show();

            }
        }
    }


    public static void setCheatFunction(boolean cheatFunction) {
        CheatFunktion.cheatFunction = cheatFunction;
    }

    public static boolean isCheatFunction() {
        return cheatFunction;
    }
}
