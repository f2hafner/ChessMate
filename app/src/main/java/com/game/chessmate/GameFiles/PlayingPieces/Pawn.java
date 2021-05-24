package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/**
 * class implementing the Pawn playing piece
 */
public class Pawn extends ChessPiece {

    private boolean firstMove;

    /**
     * Instantiates a new Pawn.
     *
     * @param resources the resource name
     * @param position  the position
     */
    public Pawn(Field position, Resources resources, int drawableId, Context context, @Nullable AttributeSet attrs, ChessPieceColour color) {
        super(context, attrs, position, resources, drawableId, color);
        this.firstMove = true;
    }

    public ChessPieceType getPlayingPieceType() {
        return ChessPieceType.PAWN;
    }

    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();
        int i = currentPosition.getFieldX();
        int j = currentPosition.getFieldY();

        if (i - 1 < 8) {
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


    public ArrayList<Field> cheatMoves() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
       /* for (Field[] f: currentFields){
            Log.d("currentFields", f.toString());
        }
        String size = String.valueOf(currentFields.length);
        Log.d("CURRENTFIELDSIZE", size); // 8*/

        ArrayList<Field> cheatFields = new ArrayList<>();
       // int i = currentPosition.getFieldX();
        //int j = currentPosition.getFieldY();


        for (int fieldX = 0; fieldX < currentFields.length; fieldX++) {
            for (int fieldY = 0; fieldY < currentFields[fieldX].length; fieldY++) {
                if (!currentFields[fieldX][fieldY].hasPiece()) {
                    cheatFields.add(currentFields[fieldX][fieldY]);
                }

            }
        }
       // Log.d("CHEATFIELD>SIZE", cheatFields.toString()) ;
        return cheatFields;
    }

    public ArrayList<Field> getCheatFunctionMoves() {

        ArrayList<Field> legalMoves = new ArrayList<>();
        ArrayList<Field> cheatMoves = new ArrayList<>();
        legalMoves = getLegalFields();
        cheatMoves = cheatMoves();

        int size = cheatMoves.size();
        for (int i = 0; i < legalMoves.size(); i++) {
            if (!cheatMoves.contains(legalMoves)) {
                size++;
            }

        }
        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = 0; i < cheatMoves.size(); i++) {
            result.add(cheatMoves.get(i));
            for (int j = 0; j < legalMoves.size(); j++) {
                if (!cheatMoves.contains(legalMoves.get(j))) {
                    result.add(legalMoves.get(j));
                }
            }
        }
        Log.d("foreach", "wir sind hier");
        for (Field f: result){
        Log.d("Pawn cheat Moves",f.getChessCoordinates());}
        return result;
    }
}
