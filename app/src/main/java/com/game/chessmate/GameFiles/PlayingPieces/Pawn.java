package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.*;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Pawn playing piece */
public class Pawn implements PlayingPiece {

    private Field position;
    private Bitmap sprite;
    private PlayingPieceColour colour;

    public Pawn(Field position, Resources resources, int drawableId){
        this.position=position;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);

    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.PAWN;
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
