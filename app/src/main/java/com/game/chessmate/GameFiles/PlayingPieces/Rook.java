package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Rook playing piece */
public class Rook implements PlayingPiece {
    private Field position;
    private Bitmap sprite;
    private PlayingPieceColour colour;

    public Rook(String ResourceName, Field position, Resources resources, PlayingPieceColour colour){
        this.position=position;
        this.sprite = BitmapFactory.decodeResource(resources, R.drawable.ic_pawn);  //TODO replace ic_launcher_background with rook vectordrawable
        this.colour=colour;
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.ROOK;
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
