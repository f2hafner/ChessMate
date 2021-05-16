package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/** class implementing the Pawn playing piece */
public class Pawn extends View implements PlayingPiece {

    /**
     * @currentPosition current Field of this PlayingPiece
     * @targetPosition Target Field of this PlayingPiece when moving.
     * @sprite Bitmap of this PlayingPiece
     * @colour Color of this PlayingPiece
     * @offset Offset Vector when the PlayingPiece is moving from Field A to B
     * @updateMovementOffset true if the RenderThread should update the position of this PlayingPiece when moving
     * @movementSpeed movement Speed of the Playing Piece. The higher the slower.
     * @updateView true if this view should be invalidated in the future by the RenderThread.
     */
    private Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Vector offset;
    private boolean updateMovementOffset;
    private int movementSpeed = 15;
    private boolean updateView;


    public Pawn(Field position, Resources resources, int drawableId, Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.currentPosition=position;
        this.targetPosition = null;
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
        scaleBitmapToFieldSize();
        this.colour=colour;
        this.offset = new Vector(0,0);
        this.updateMovementOffset = false;
        this.updateView = false;
    }

    /**
     * Scales the bitmap of this PlayingPiece to the size of the rectangle container.
     */
    private void scaleBitmapToFieldSize() {
        Rect rectangle = this.currentPosition.getRectangle();
        int width = rectangle.width();
        int height = rectangle.height();
        this.sprite = Bitmap.createScaledBitmap(this.sprite, width, height, false);
    }

    //TODO implement Interface methods
    @Override
    public PlayingPieceType getPlayingPieceType() {
        return PlayingPieceType.PAWN;
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

    /**
     * Draws the bitmap of this PlayingPiece to the canvas in the position of the containing rectangle.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: " + " got called in pawn");
        Field field = this.currentPosition;
        canvas.drawBitmap(this.sprite, field.getRectangle().left + (int)offset.getX(), field.getRectangle().top + (int)offset.getY(), null);
    }

    /**
     * Marks this PlayingPiece for the Renderthread to update and start moving to @targetField
     * @param targetField
     */
    @Override
    public void move(Field targetField) {
        this.targetPosition = targetField;
        this.updateMovementOffset = true;
        this.setUpdateView(true);
    }

    /**
     * Calculates the Vector between Field A and Field B and updates the position of the PlayingPiece
     * with @offset until it reaches its destination Field B.
     */
    @Override
    public void updateOffsets() {
        Vector start = new Vector(currentPosition.getRectangle().left, currentPosition.getRectangle().top);
        Vector target = new Vector(targetPosition.getRectangle().left, targetPosition.getRectangle().top);
        Vector vector = target.sub(start);

        if((offset.getX() != vector.getX()) || (offset.getY() != vector.getY())){
            offset = offset.add(vector.div(this.movementSpeed));
            this.setUpdateView(true);
        }
        else {
            // TODO Note: When the piece moves to its destination,
            //  the piece that olready occupies that destination does not get removed because it has its own canvas.
            //  Cannot set it null and rerender cause null will skip the invalidate(). piece on destination
            //  somehow needs to be removed first. maybe by setting its color to transparent.
            this.updateMovementOffset = false;
            this.offset = new Vector(0,0);
            currentPosition.setCurrentPiece(null);
            this.currentPosition = targetPosition;
            targetPosition.setCurrentPiece(this);
            this.setUpdateView(true);
        }
    }

    @Override
    public boolean updateMovementOffset() {
        return updateMovementOffset;
    }

    @Override
    public boolean getUpdateView() {
        return this.updateView;
    }

    @Override
    public void setUpdateView(boolean update) {
        this.updateView = update;
    }
}
