package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/**
 * class implementing the King playing piece
 */
public class King extends ChessPiece {


    /**
     * Instantiates a new King.
     *
     * @param position     the position
     */
    public King(Field position, Bitmap sprite, Context context, @Nullable AttributeSet attrs, ChessPieceColour color){
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
     * @return ArrayList of fields that are legal for the chess Piece to move to.
     */
    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        for(int i = currentPosition.getFieldX()-1; i <= currentPosition.getFieldX()+1; i++){
            for(int j = currentPosition.getFieldY()-1; j <= currentPosition.getFieldY()+1; j++){
                if(i>=0 && i<=7 && j>=0 && j<=7){
                    if(!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())){
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















}
