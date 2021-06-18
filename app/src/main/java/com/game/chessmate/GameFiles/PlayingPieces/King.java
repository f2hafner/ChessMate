package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * class implementing the King playing piece
 */
public class King extends ChessPiece {

    /**
     * Instantiates a new King.
     *
     * @param position the position
     * @param sprite   the sprite
     * @param context  the context
     * @param attrs    the attrs
     * @param color    the color
     */
    public King(Field position, Bitmap sprite, Context context, @Nullable AttributeSet attrs, ChessPieceColour color) {
        super(context, attrs, position, sprite, color);
    }

    /**
     * Scales the bitmap of this PlayingPiece to the size of the rectangle container.
     */
    public ChessPieceType getPlayingPieceType() {
        return ChessPieceType.KING;
    }

    /**
     * Method determines all legal fields, that type of chess piece is allowed to move to and returns them as an ArrayList.
     *
     * @return ArrayList of fields that are legal for the chess Piece to move to.
     */
    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        for (int i = currentPosition.getFieldX() - 1; i <= currentPosition.getFieldX() + 1; i++) {
            for (int j = currentPosition.getFieldY() - 1; j <= currentPosition.getFieldY() + 1; j++) {
                if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
                    if (!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())) {
                        if (currentFields[i][j].getCurrentPiece() == null) {
                            legalFields.add(currentFields[i][j]);
                        } else if (currentFields[i][j].getCurrentPiece().getColour() != this.colour) {
                            legalFields.add(currentFields[i][j]);
                        }
                    }
                }
            }
        }
        return legalFields;
    }

    /**
     * Method determines whether king is checked, by looking through legalMoves of enemy pieces and determining whether king is in legalMoves of enemy.
     * Also sets ArrayList isChecking to all enemy pieces that are checking king.
     *
     * @param fields current layout of fields and their pieces from chessboard
     * @return boolean whether king is in check
     */
    public boolean isChecked(Field[][] fields) {
        boolean result = false;
        isChecking = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j].getCurrentPiece() != null) {//must be in seperate if - else possible null pointer exception
                    if (fields[i][j].getCurrentPiece().getColour() != this.getColour()) {
                        for (Field f : fields[i][j].getCurrentPiece().getLegalFields()) {
                            if (f.equals(currentPosition)) {
                                isChecking.add(fields[i][j]);
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    /**
     * Method determines whether king would still be checked, if king were to move to Field f.
     *
     * @param currentFields current layout of fields and their pieces from chessboard
     * @param f field that is theoretically moved to
     * @return boolean whether king would be in check if king moved to field f
     */
    @Override
    public boolean wouldbeChecked(Field [][] currentFields, Field f) {
        Field realPosition = currentPosition;
        currentPosition = f; //what would happen if king had this field as position
        boolean result = isChecked(currentFields); //would this piece / king still be in check?
        currentPosition = realPosition; //resetting to real position
        return result;
    }
}

