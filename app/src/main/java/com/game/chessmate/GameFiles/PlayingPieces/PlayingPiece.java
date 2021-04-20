package com.game.chessmate.GameFiles.PlayingPieces;
/** Interface to set the needed methods for the PlayingPieces. */
public interface PlayingPiece {

    PlayingPieceType type = PlayingPieceType.EMPTY; // PlayingPiece Type

    //TODO find a way to implement the legal moves
    // Maybe asset datatype so a user can switch out the playingpiece designs (if possible)
}
