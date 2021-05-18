package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/** class implementing the Queen playing piece */
public class Queen implements PlayingPiece {

    private boolean isProtected=false;
    private Field currentPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Resources resources;
    int drawableId;

    public Queen(Field position, Resources resources, int drawableId, PlayingPieceColour colour){
        this.currentPosition=position;
        this.resources=resources;
        this.drawableId=drawableId;
        this.colour=colour;
    }

    /**
     * Scale the bitmap of the PlayingPiece to the size of its rectangle container.
     */
    private void scaleBitmapToFieldSize() {
        Rect rectangle = this.currentPosition.getRectangle();
        int width = rectangle.width();
        int height = rectangle.height();
        this.sprite = Bitmap.createScaledBitmap(this.sprite, width, height, false);
    }

    public void createBitmap(){
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        scaleBitmapToFieldSize();
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.QUEEN;
    }

    @Override
    public Field getPosition() {
        return this.currentPosition;
    }

    @Override
    public void setPosition(Field position) {
        this.currentPosition=position;
    }

    @Override
    public void setProtected() {
        isProtected=true;
    }

    @Override
    public boolean isProtected() {
        return this.isProtected;
    }

    @Override
    public Bitmap getDrawable() {
        return this.sprite;
    }

    @Override
    public ArrayList<Field> getLegalFields() {
        Field[][] currentFields = ChessBoard.getInstance().getBoardFields();
        ArrayList<Field> legalFields = new ArrayList<>();

        for(int loops = 0; loops <8; loops++){
            int i = currentPosition.getFieldX();
            int j = currentPosition.getFieldY();
            while(i<8 && i>=0 && j<8 && j>=0){
                if(!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())){
                    if (currentFields[i][j].getCurrentPiece() == null) {
                        legalFields.add(currentFields[i][j]);
                    } else if (currentFields[i][j].getCurrentPiece().getColour() != this.colour) {
                        legalFields.add(currentFields[i][j]);
                    }else{
                        break;
                    }
                }
                switch(loops){
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
                    case 4:
                        i--;
                        j--;
                        break;
                    case 5:
                        i++;
                        j++;
                        break;
                    case 6:
                        i--;
                        j++;
                        break;
                    case 7:
                        i++;
                        j--;
                        break;

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
        this.colour=colour;}

}
