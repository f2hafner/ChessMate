package com.game.chessmate.GameFiles.PlayingPieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;

import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/** Interface to set the needed methods for the PlayingPieces. */
public interface PlayingPiece {

    PlayingPieceType getPlayingPieceType();
    Field getPosition();
    Bitmap getDrawable();
    ArrayList<Field> getLegalFields();
    PlayingPieceColour getColour();
    void setCurrentPosition(Field field);
    void draw(Canvas canvas);
    void move(Field ToField);
    boolean updateMovementOffset();
    void updateOffsets();
    boolean getUpdateView();
    void setUpdateView(boolean update);
}
