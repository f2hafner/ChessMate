package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;

import java.util.ArrayList;

abstract public class ChessPiece extends View {

    /**
     * The Current position.
     *
     * @currentPosition current Field of this PlayingPiece
     * @targetPosition Target Field of this PlayingPiece when moving.
     * @sprite Bitmap of this PlayingPiece
     * @colour Color of this PlayingPiece
     * @offset Offset Vector when the PlayingPiece is moving from Field A to B
     * @updateMovementOffset true if the RenderThread should update the position of this PlayingPiece when moving
     * @movementSpeed movement Speed of the Playing Piece. The higher the slower.
     * @updateView true if this view should be invalidated in the future by the RenderThread.
     */
    protected Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    protected ChessPieceColour colour;
    private Vector offset;
    private boolean updateMovementOffset;
    private int movementSpeed = 15;
    private boolean updateView;
    private boolean firstMove = true;

    public ChessPiece(Context context, Field position, Bitmap sprite, ChessPieceColour colour) {
        super(context);
        this.currentPosition = position;
        this.targetPosition = null;
        this.colour = colour;
        this.offset = new Vector(0,0);
        this.updateMovementOffset = false;
        this.updateView = false;
        this.sprite = sprite;
    }

    public ChessPiece(Context context, @Nullable AttributeSet attrs, Field position, Bitmap sprite, ChessPieceColour colour) {
        super(context, attrs);
        this.currentPosition = position;
        this.targetPosition = null;
        this.colour = colour;
        this.offset = new Vector(0,0);
        this.updateMovementOffset = false;
        this.updateView = false;
        this.sprite = sprite;
    }

    public ChessPiece(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Field position, Bitmap sprite, ChessPieceColour colour) {
        super(context, attrs, defStyleAttr);
        this.currentPosition = position;
        this.targetPosition = null;
        this.colour = colour;
        this.offset = new Vector(0,0);
        this.updateMovementOffset = false;
        this.updateView = false;
        this.sprite = sprite;
    }

    abstract public ChessPieceType getPlayingPieceType();
    abstract public ArrayList<Field> getLegalFields();

    public Field getPosition() {
        return this.currentPosition;
    }

    public Bitmap getDrawable() {
        return this.sprite;
    }

    public ChessPieceColour getColour() {
        return this.colour;
    }

    public void setCurrentPosition(Field field) {
        this.currentPosition = field;
    }

    public void setColor(ChessPieceColour colour) {
        this.colour=colour;
    }

    /**
     * Draws the bitmap of this PlayingPiece to the canvas in the position of the containing rectangle.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Field field = this.currentPosition;
        canvas.drawBitmap(this.sprite, field.getRectangle().left + (int)offset.getX(), field.getRectangle().top + (int)offset.getY(), null);
    }

    /**
     * Marks this PlayingPiece for the Render-thread to update and start moving to @targetField
     * @param targetField
     */
    public void move(Field targetField) {
        this.targetPosition = targetField;
        this.updateMovementOffset = true;
        this.setUpdateView(true);
    }

    /**
     * Calculates the Vector between Field A and Field B and updates the position of the PlayingPiece
     * with @offset until it reaches its destination Field B.
     */
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

    public boolean updateMovementOffset() {
        return this.updateMovementOffset;
    }

    public boolean getUpdateView() {
        return this.updateView;
    }

    public void setUpdateView(boolean update) {
        this.updateView = update;
    }

    public void setFirstMove(boolean value){
        this.firstMove = value;
    }
    public boolean getFirstMove(){
        return this.firstMove;
    }
}
