package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

/** class implementing the Knight playing piece */
public class Knight implements PlayingPiece {

    private Field position;
    private Drawable sprite;

    public Knight(String ResourceName, Field position, Resources resources){
        this.position=position;
        this.sprite = resources.getDrawable(R.drawable.ic_launcher_background);  //TODO replace ic_launcher_background with knight vectordrawable
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
    public Drawable getDrawable() {
        return this.sprite;
    }

}
