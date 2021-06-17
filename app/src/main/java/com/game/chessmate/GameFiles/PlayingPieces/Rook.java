package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/**
 * class implementing the Rook playing piece
 */
public class Rook extends ChessPiece {

    /**
     * Instantiates a new Rook.
     *
     * @param position the position
     * @param sprite   the sprite
     * @param context  the context
     * @param attrs    the attrs
     * @param color    the color
     */
    public Rook(Field position, Bitmap sprite, Context context, @Nullable AttributeSet attrs, ChessPieceColour color){
        super(context, attrs, position, sprite, color);
    }

    public ChessPieceType getPlayingPieceType() {
        return ChessPieceType.ROOK;
    }

    /**
     * Method determines all legal fields, that type of chess piece is allowed to move to and returns them as an ArrayList.
     * @return ArrayList of fields that are legal for the chess Piece to move to.
     */
    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        //get first moves for Bombard-Card
        if (ChessBoard.getInstance().isSpecialActivated()&&ChessBoard.getInstance().getSpecialNumber()==1){
            legalFields=getLegalMovesBombard();
        }

        else {

            int i;
            int j;
            for (int loops = 0; loops < 4; loops++) {
                i = currentPosition.getFieldX();
                j = currentPosition.getFieldY();
                opponentEncountered = false;
                while (i < 8 && i >= 0 && j < 8 && j >= 0) {
                    if (!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY()) && !opponentEncountered) {
                        if (currentFields[i][j].getCurrentPiece() == null) {
                            if (!currentFields[i][j].isBlocked())
                                legalFields.add(currentFields[i][j]);
                            else
                                break;
                        } else if (currentFields[i][j].getCurrentPiece().getColour() != this.colour && !currentFields[i][j].isProtected()) {
                            legalFields.add(currentFields[i][j]);
                            opponentEncountered = true;
                        } else {
                            break; //breaks out of while - done with current direction
                        }
                    }
                    switch (loops) {
                        case 0:
                            i++;
                            break;
                        case 1:
                            i--;
                            break;
                        case 2:
                            j--;
                            break;
                        case 3:
                            j++;
                            break;
                    }
                }
            }
        }
        return legalFields;
    }

    public ArrayList<Field> getLegalMovesBombard() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalMoves = new ArrayList<>();

        int i;
        int j;

        for (int loops = 0; loops < 4; loops++) {
            i = currentPosition.getFieldX();
            j = currentPosition.getFieldY();
            opponentEncountered = false;

            while (i < 8 && i >= 0 && j < 8 && j >= 0) {
                if (!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY()) && !opponentEncountered) {
                    if (currentFields[i][j].getCurrentPiece() == null) {
                        legalMoves.add(currentFields[i][j]);
                    }

                    switch (loops) {
                        case 0:
                            i++;
                            break;
                        case 1:
                            i--;
                            break;
                        case 2:
                            j--;
                            break;
                        case 3:
                            j++;
                            break;
                    }
                }
            }

        }
        return legalMoves;

    }

}
