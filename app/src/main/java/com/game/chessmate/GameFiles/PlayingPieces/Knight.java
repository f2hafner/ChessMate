package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;
import com.game.chessmate.R;

import java.util.ArrayList;

/** class implementing the Knight playing piece */
public class Knight implements PlayingPiece {

    private Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Vector offset;
    private boolean updatePosition;
    private int movementSpeed = 15;

    public Knight(Field position, Resources resources, int drawableId){
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
        return PlayingPieceType.KNIGHT;
    }

    @Override
    public Field getPosition() {
        return this.currentPosition;
    }

    @Override
    public Bitmap getDrawable() {
        return this.sprite;
    }

    @Override
    public ArrayList<Field> getLegalFields() {
        return new ArrayList<>();
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
