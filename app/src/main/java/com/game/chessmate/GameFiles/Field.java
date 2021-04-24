package com.game.chessmate.GameFiles;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.game.chessmate.GameFiles.PlayingPieces.PlayingPiece;

import static android.content.ContentValues.TAG;

/** The Field class is an Object that represents a PlayingField on the chessboard. */
public class Field {

    /**
     * @param x x coordinate of the Field on the Chessboard Array
     * @param y y coordinate of the Field on the Chessboard Array
     * @param color color of the Rectangle that is the Field
     * @param rectangle is used to display the Field on the Chessboard for now.
     * @param playingPiece the Chess piece on this Field
     */
    private int x;
    private int y;
    private Paint color;
    private Rect rectangle;
    private PlayingPiece currentPiece;

    /**
     * Constructor of Field. Set coordinates, compute color of the Rectangle with x and y, and construct the rectangle
     */
    public Field (int x, int y){
        this.x = x;
        this.y = y;

        this.color = new Paint();
        color.setColor(getColor() ? Color.BLACK : Color.GRAY);
        rectangle = new Rect();
        setupRectangle(rectangle);
    }

    /**
     * Draw the rectangle on the canvas with @param color
     */
    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, color);
    }

    /** Returns true if the field has a playing piece on it
     *
     * @return boolean
     * */
    public boolean hasPiece(){
        //TODO implement if Field has Piece
        throw new UnsupportedOperationException();
    }

    /** Gets the current Piece that is on the Playing Field.
     *
     * @return PlayingPiece
     * */
    public PlayingPiece getCurrentPiece(){
        //TODO implement return gamepiece
        throw new UnsupportedOperationException();
    }

    /**
     * Decides the color of the Rectangle with modulo operation on x and y coordinates.
     */
    private boolean getColor() {
        return (this.x + this.y) % 2 == 0;
    }

    /**
     * Init the Rectangle by setting the coordinates for left, top, right, bottom.
     */
    public void setupRectangle(Rect rectangle) {
        int fieldSize = ChessBoard.getInstance().getFieldSize();
        this.rectangle = rectangle;
        this.rectangle.left = fieldSize * this.y;
        this.rectangle.top = fieldSize * this.x;
        this.rectangle.right = this.rectangle.left + fieldSize;
        this.rectangle.bottom = this.rectangle.top + fieldSize;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    /**
     * Use the helper class to translate the coordinates in the 2D Array to Chess coordinates.
     * @return String containing the Chess coordinates of the Field.
     */
    public String getChessCoordinates() {
        return ArrayToChessCoordinatesTranslator.translateCoordinates(this.x, this.y);
    }
}
