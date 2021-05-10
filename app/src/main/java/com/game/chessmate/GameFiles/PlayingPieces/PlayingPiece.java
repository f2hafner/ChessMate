package com.game.chessmate.GameFiles.PlayingPieces;

import android.graphics.Bitmap;

import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/** Interface to set the needed methods for the PlayingPieces. */
public interface PlayingPiece {

    PlayingPieceType getPlayingPieceType();
    Field getPosition();
    Bitmap getDrawable();
    ArrayList<Field> getLegalFields();
    PlayingPieceColour getColour();
    void setColor(PlayingPieceColour colour);

    //TODO find a way to implement the legal moves
    // Maybe asset datatype so a user can switch out the playingpiece designs (if possible)
}
