package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.res.ResourcesCompat;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/**
 * class implementing the King playing piece
 */
public class King implements PlayingPiece {

    private Field position;
    private Bitmap sprite;    //TODO Maybe we can use Drawable for the svg. Could not figure out.
    private PlayingPieceColour colour;

    /**
     * Instantiates a new King.
     *
     * @param resourceName the resource name
     * @param position     the position
     */
    public King(Field position, Resources resources, int drawableId) {
        this.position = position;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        this.colour=colour;
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.KING;
    }

    @Override
    public Field getPosition() {
        return this.position;
    }

    @Override
    public Bitmap getDrawable() {
        return this.sprite;
    }

    @Override
    public Field[] canMove() {
        return new Field[0];
    }

    @Override
    public PlayingPieceColour getColour() {
        return this.colour;
    }

}
