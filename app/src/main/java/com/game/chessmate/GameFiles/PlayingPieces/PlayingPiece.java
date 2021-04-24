package com.game.chessmate.GameFiles.PlayingPieces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

import com.game.chessmate.GameFiles.Field;

/** Interface to set the needed methods for the PlayingPieces. */
public interface PlayingPiece {

    PlayingPieceType getPlayingPieceType();
    Field getPosition();
    Drawable getDrawable();
    PlayingPieceColour getColour();

    //TODO find a way to implement the legal moves
    // Maybe asset datatype so a user can switch out the playingpiece designs (if possible)

}
