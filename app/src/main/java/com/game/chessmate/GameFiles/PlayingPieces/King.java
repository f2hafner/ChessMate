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
    private Drawable sprite;    //TODO Maybe we can use Drawable for the svg. Could not figure out.

    /**
     * Instantiates a new King.
     *
     * @param resourceName the resource name
     * @param position     the position
     */
    public King(String resourceName, Field position, Resources resources) {
        this.position = position;
        this.sprite = resources.getDrawable(R.drawable.ic_launcher_background);  //TODO replace ic_launcher_background with king vectordrawable
    }

    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.KING;
    }

    @Override
    public Field getPosition() {
        return this.position;
    }

    @Override
    public Drawable getDrawable() {
        return this.sprite;
    }

    //TODO implement Interface methods
}
