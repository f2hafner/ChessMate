package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Bishop playing piece */
public class Bishop implements PlayingPiece {

    private Field position;
    private Drawable sprite;

    public Bishop(String ResourceName, Field position, Resources resources){
        this.position=position;
        this.sprite = resources.getDrawable(R.drawable.ic_launcher_background);  //TODO replace ic_launcher_background with bishop vectordrawable
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.BISHOP;
    }

    @Override
    public Field getPosition() {
        return this.position;
    }

    @Override
    public Drawable getDrawable() {
        return this.sprite;
    }


}
