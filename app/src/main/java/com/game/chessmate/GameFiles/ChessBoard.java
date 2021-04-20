package com.game.chessmate.GameFiles;
/** The ChessBoard class handles creation and maintainance of the ChessBoard
 */
public class ChessBoard {
    // Thread-Save Singleton
    private static final class InstanceHolder { static final ChessBoard INSTANCE = new ChessBoard(); }
    public static ChessBoard getInstance(){ return ChessBoard.InstanceHolder.INSTANCE; }
    private ChessBoard () {
        create((byte) 8);
        //TODO constructor CODE on ChessBoard initialization
    }

    private Field[][] board = null;

    /** Creates a 2D Array of Fields which form the chess board.
     * @param size chessboard (default size 8)
     * */
    public void create(byte size){
        //TODO ChessBoard creation
        this.board = new Field[size][size];
    }
    /** Creates a 2D Array of Fields which form the chess board. */
    public void initializePieces(){
        //TODO puts the ChestPieces in starting position
    }

}
