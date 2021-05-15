package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;

import java.util.ArrayList;

/**
 * class implementing the King playing piece
 */
public class King implements PlayingPiece {

    private Field currentPosition;
    private Bitmap sprite;    //TODO Maybe we can use Drawable for the svg. Could not figure out.
    private PlayingPieceColour colour;
    private Resources resources;
    int drawableId;

    /**
     * Instantiates a new King.
     *
     * @param resources the resource name
     * @param position     the position
     */
    public King(Field position, Resources resources, int drawableId, PlayingPieceColour colour){
        this.currentPosition = position;
        this.resources=resources;
        this.drawableId=drawableId;
        this.colour=colour;
    }

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

    @Override
    public PlayingPieceColour getColour() {
        return this.colour;
    }

    @Override
    public void setColor(PlayingPieceColour colour) {
        this.colour=colour;
    }

}
