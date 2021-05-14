package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;
import com.game.chessmate.R;

import java.util.ArrayList;

/** class implementing the Bishop playing piece */
public class Bishop implements PlayingPiece {

    private Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Vector offset;
    private boolean updatePosition;
    private int movementSpeed = 15;

    public Bishop(Field position, Resources resources, int drawableId){
        this.currentPosition=position;
        this.targetPosition = null;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        scaleBitmapToFieldSize();
        this.colour=colour;
        this.offset = new Vector(0,0);
        this.updatePosition = false;
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
        return PlayingPieceType.BISHOP;
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

        int i;
        int j;
        for(int loops = 0; loops <4; loops++){
            i = currentPosition.getFieldX();
            j = currentPosition.getFieldY();

            while(i<8 && i>=0 && j<8 && j>=0){
                if(!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())){
                    legalFields.add(currentFields[i][j]);
                }
                switch(loops){
                    case 0:
                        i++;
                        j++;
                        break;
                    case 1:
                        i--;
                        j--;
                        break;
                    case 2:
                        i++;
                        j--;
                        break;
                    case 3:
                        i--;
                        j++;
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

    public void setCurrentPosition(Field currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public void draw(Canvas canvas) {
        Field field = this.currentPosition;
        canvas.drawBitmap(this.sprite, field.getRectangle().left + (int)offset.getX(), field.getRectangle().top + (int)offset.getY(), null);
    }

    @Override
    public void move(Field targetField) {
        this.targetPosition = targetField;
        this.updatePosition = true;
        this.currentPosition.setUpdate(true);
    }

    public void updateOffsets() {
        Vector start = new Vector(currentPosition.getRectangle().left, currentPosition.getRectangle().top);
        Vector target = new Vector(targetPosition.getRectangle().left, targetPosition.getRectangle().top);
        Vector vector = target.sub(start);

        if((offset.getX() != vector.getX()) || (offset.getY() != vector.getY())){
            offset = offset.add(vector.div(this.movementSpeed));
            currentPosition.setUpdate(true);
        }
        else {
            this.updatePosition = false;
            currentPosition.setCurrentPiece(null);
            targetPosition.setCurrentPiece(this);
            this.offset = new Vector(0,0);
            this.setCurrentPosition(targetPosition);
            currentPosition.setUpdate(true);
            targetPosition.setUpdate(true);
        }
    }

    @Override
    public boolean isUpdatePosition() {
        return this.updatePosition;
    }
}

