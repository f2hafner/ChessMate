package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/** class implementing the Knight playing piece */
public class Knight extends ChessPiece {

    /**
     * Instantiates a new Knight.
     *
     * @param resources the resource name
     * @param position     the position
     */
    public Knight(Field position, Resources resources, int drawableId, Context context, @Nullable AttributeSet attrs, ChessPieceColour color){
        super(context, attrs, position, resources, drawableId, color);
    }

    public ChessPieceType getPlayingPieceType() {
        return ChessPieceType.KNIGHT;
    }

    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        int i = currentPosition.getFieldX();
        int j = currentPosition.getFieldY();
        for(int loops = 0; loops <8; loops++){
            if(i<8 && i>=0 && j<8 && j>=0){
                if(!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())){
                    if (currentFields[i][j].getCurrentPiece() == null) {
                        legalFields.add(currentFields[i][j]);
                    } else if (currentFields[i][j].getCurrentPiece().getColour() != this.colour) {
                        legalFields.add(currentFields[i][j]);
                    }
                }
                i = currentPosition.getFieldX();
                j = currentPosition.getFieldY();
                switch(loops){
                    case 0:
                        i-=2;
                        j++;
                        break;
                    case 1:
                        i-=2;
                        j--;
                        break;
                    case 2:
                        i+=2;
                        j--;
                        break;
                    case 3:
                        i+=2;
                        j++;
                        break;
                    case 4:
                        i--;
                        j+=2;
                        break;
                    case 5:
                        i++;
                        j+=2;
                        break;
                    case 6:
                        i--;
                        j-=2;
                        break;
                    case 7:
                        i++;
                        j-=2;
                        break;

                }
            }
        }
        return legalFields;
    }
}
