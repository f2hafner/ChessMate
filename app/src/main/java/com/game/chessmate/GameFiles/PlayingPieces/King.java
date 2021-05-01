package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.res.ResourcesCompat;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.R;

import java.util.ArrayList;

/**
 * class implementing the King playing piece
 */
public class King implements PlayingPiece {

    private Field currentPosition;
    private Bitmap sprite;    //TODO Maybe we can use Drawable for the svg. Could not figure out.
    private PlayingPieceColour colour;

    /**
     * Instantiates a new King.
     *
     * @param resources the resource name
     * @param position     the position
     */
    public King(Field position, Resources resources, int drawableId) {
        this.currentPosition = position;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        this.colour=colour;
    }

    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.KING;
    }

    @Override
    public Field getPosition() {
        return this.currentPosition;
    }

    @Override
    public Bitmap getDrawable() {
        return this.sprite;
    }

    /**
     * Method determines all legal fields, that type of chess piece is allowed to move to and returns them as an ArrayList.
     * @return ArrayList of fields that are legal for the chess Piece to move to.
     */
    @Override
    public ArrayList<Field> getLegalFields() {
        ChessBoard cb = ChessBoard.getInstance();
        Field[][] currentFields = cb.getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        for(int i = currentPosition.getX()-1; i <= currentPosition.getX()+1; i++){
            for(int j = currentPosition.getY()-1; j <= currentPosition.getY()+1; j++){
                if(i>=0 && i<=7 && j>=0 && j<=7){
                    if(!(i == currentPosition.getX() && j == currentPosition.getY())){
                        legalFields.add(currentFields[i][j]);
                    }
                }
            }
        }
        return legalFields;
    }

    @Override
    public PlayingPieceColour getColour() {
        return this.colour;
    }

}
