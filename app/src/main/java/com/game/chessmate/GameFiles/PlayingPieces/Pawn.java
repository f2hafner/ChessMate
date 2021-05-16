package com.game.chessmate.GameFiles.PlayingPieces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.Vector;
import com.game.chessmate.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/** class implementing the Pawn playing piece */
public class Pawn extends View implements PlayingPiece {

    private Field currentPosition;
    private Field targetPosition;
    private Bitmap sprite;
    private PlayingPieceColour colour;
    private Vector offset;
    private boolean updatePosition;
    private int movementSpeed = 15;
    private boolean update;


    public Pawn(Field position, Resources resources, int drawableId, Context context, @Nullable AttributeSet attrs){
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

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: " + " got called in pawn");
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
            // TODO Note: When the piece moves to its destination,
            //  the piece that olready occupies that destination does not get removed because it has its own canvas.
            //  Cannot set it null and rerender cause null will skip the invalidate(). piece on destination
            //  somehow needs to be removed first. maybe by setting its color to transparent.
            this.updatePosition = false;
            this.offset = new Vector(0,0);
            currentPosition.setCurrentPiece(null);
            this.currentPosition = targetPosition;
            targetPosition.setCurrentPiece(this);
            this.setUpdate(true);


            Log.d(TAG, "updateOffsets: " + targetPosition.getCurrentPiece());
            Log.d(TAG, "updateOffsets: " + targetPosition.getUpdate());
        }
    }

    public boolean isUpdatePosition() {
        return updatePosition;
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
