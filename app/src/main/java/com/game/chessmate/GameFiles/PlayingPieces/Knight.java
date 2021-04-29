package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Knight playing piece */
public class Knight implements PlayingPiece {

    private Field position;
    private Bitmap sprite;
    private PlayingPieceColour colour;

    public Knight(Field position, Resources resources, int drawableId){
        this.position=position;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        this.colour=colour;
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.KNIGHT;
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
