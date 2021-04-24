package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Queen playing piece */
public class Queen implements PlayingPiece {

    private Field position;
    private Drawable sprite;
    private PlayingPieceColour colour;

    public Queen(String ResourceName, Field position, Resources resources,PlayingPieceColour colour){
        this.position=position;
        this.sprite = resources.getDrawable(R.drawable.ic_launcher_background);  //TODO replace ic_launcher_background with queen vectordrawable
        this.colour=colour;
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.QUEEN;
    }

    @Override
    public Field getPosition() {
        return this.position;
    }

    @Override
    public Drawable getDrawable() {
        return this.sprite;
    }

    @Override
    public PlayingPieceColour getColour() {
        return this.colour;
    }

}
