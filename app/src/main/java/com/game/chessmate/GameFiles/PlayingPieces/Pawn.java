package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/** class implementing the Pawn playing piece */
public class Pawn implements PlayingPiece {

    private Field currentPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;

    public Pawn(Field position, Resources resources, int drawableId){
        this.currentPosition=position;
 //       this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        this.colour=colour;
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.PAWN;
    }

    @Override
    public Field getPosition() {
        return this.currentPosition;
    }

    @Override
    public Bitmap getDrawable() {
        return this.sprite;
    }

    @Override
    public ArrayList<Field> getLegalFields() {
        return new ArrayList<>();
    }


    @Override
    public PlayingPieceColour getColour() {
        return this.colour;
    }

    @Override
    public void setColor(PlayingPieceColour colour) {
        this.colour=colour;
    }

}
