package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;

import java.util.ArrayList;

/** class implementing the Rook playing piece */
public class Rook extends View implements PlayingPiece {

    private Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Vector offset;
    private boolean updatePosition;
    private int movementSpeed = 15;
    private boolean update;

    public Rook(Field position, Resources resources, int drawableId, Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.currentPosition=position;
        this.targetPosition = null;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        scaleBitmapToFieldSize();
        this.colour=colour;
        this.offset = new Vector(0,0);
        this.updatePosition = false;
        this.update = false;
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
        return PlayingPieceType.ROOK;
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
            while(i<8 && i>=0 && j<8 && j>=0 ){
                if(!(i == currentPosition.getFieldX() && j == currentPosition.getFieldY())){
                    legalFields.add(currentFields[i][j]);
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
    public void setCurrentPosition(Field currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Field field = this.currentPosition;
        canvas.drawBitmap(this.sprite, field.getRectangle().left + (int)offset.getX(), field.getRectangle().top + (int)offset.getY(), null);
    }

    @Override
    public void move(Field targetField) {
        this.targetPosition = targetField;
        this.updatePosition = true;
        this.setUpdate(true);
    }

    public void updateOffsets() {
        Vector start = new Vector(currentPosition.getRectangle().left, currentPosition.getRectangle().top);
        Vector target = new Vector(targetPosition.getRectangle().left, targetPosition.getRectangle().top);
        Vector vector = target.sub(start);

        if((offset.getX() != vector.getX()) || (offset.getY() != vector.getY())){
            offset = offset.add(vector.div(this.movementSpeed));
            this.setUpdate(true);
        }
        else {
            this.updatePosition = false;
            currentPosition.setCurrentPiece(null);
            targetPosition.setCurrentPiece(this);
            this.offset = new Vector(0,0);
            this.setCurrentPosition(targetPosition);
            this.setUpdate(true);
            targetPosition.getCurrentPiece().setUpdate(true);
        }
    }


    public boolean isUpdatePosition() {
        return this.updatePosition;
    }

    @Override
    public boolean getUpdate() {
        return this.update;
    }

    @Override
    public void setUpdate(boolean update) {
        this.update = update;
    }
}
