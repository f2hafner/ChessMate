package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.*;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

import java.util.ArrayList;

/** class implementing the Pawn playing piece */
public class Pawn implements PlayingPiece {

    private Field currentPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private boolean firstMove = true;

    public Pawn(Field position, Resources resources, int drawableId, PlayingPieceColour colour){
        this.currentPosition=position;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        scaleBitmapToFieldSize();
        this.colour=colour;
    }

    private void scaleBitmapToFieldSize() {
        Rect rectangle = this.currentPosition.getRectangle();
        int width = rectangle.width();
        int height = rectangle.height();
        this.sprite = Bitmap.createScaledBitmap(this.sprite, width, height, false);
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
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();
        int i = currentPosition.getFieldX();
        int j = currentPosition.getFieldY();

        if(i-1 < 8) {
            if (currentFields[i - 1][j].getCurrentPiece() == null) {
                legalFields.add(currentFields[i - 1][j]);
            } else if (currentFields[i - 1][j].getCurrentPiece().getColour() != this.colour) {
                legalFields.add(currentFields[i - 1][j]);
            } else {
                return legalFields;
            }
            if (firstMove && i - 2 < 8) {
                if (currentFields[i - 2][j].getCurrentPiece() == null) {
                    legalFields.add(currentFields[i - 2][j]);
                } else if (currentFields[i - 2][j].getCurrentPiece().getColour() != this.colour) {
                    legalFields.add(currentFields[i - 2][j]);
                } else {
                    return legalFields;
                }
            }
        }
        return legalFields;
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
