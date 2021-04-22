package com.game.chessmate.GameFiles;
import com.game.chessmate.GameFiles.PlayingPieces.PlayingPiece;

/** The Field class is an Object that represents a PlayingField on the chessboard. */
public class Field {
    private int x; // required to get X value for the Field
    private int y; // required to get Y value for the Field
    PlayingPiece currentPiece; //current PlayingPiece on the Field
    public Field (){
        //TODO Field constructor
        throw new UnsupportedOperationException();
    }

    /** Returns true if the field has a playing piece on it
     * @return boolean
     * */
    public boolean hasPiece(){
        //TODO implement if Field has Piece
        throw new UnsupportedOperationException();
    }

    /** Gets the current Piece that is on the Playing Field.
     * @return PlayingPiece
     * */
    public PlayingPiece getCurrentPiece(){
        //TODO implement return gamepiece
        throw new UnsupportedOperationException();
    }
}
