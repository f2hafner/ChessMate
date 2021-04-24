package com.game.chessmate.GameFiles;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import static android.content.ContentValues.TAG;

/**
 * The ChessBoard class handles creation and maintenance of the ChessBoard
 */
public class ChessBoard {
    // Thread-Save Singleton
    private static final class InstanceHolder {
        static final ChessBoard INSTANCE = new ChessBoard();
    }
    public static ChessBoard getInstance(){ return ChessBoard.InstanceHolder.INSTANCE; }


    /**
     * @param boardFields 2D Array that contains all Fields of the Chessboard
     * @param fieldSize The size of one Field one the Chessboard, which is actually used to define the size
     *                  of the Rectangle each Field consists of.
     */
    private Field[][] boardFields;
    private int fieldSize;

    /**
     * Initializes the 2D Array of Fields and calculates the Field size(Rectangle size) with the width of the canvas
     * that is being drawn on.
     *
     * @param size   chessboard (default size 8)
     * @param canvas the canvas which contains the Chessboard
     */
    public void create(byte size, Canvas canvas){
        this.boardFields = new Field[size][size];
        this.fieldSize = calculateRectSize(canvas, size);
        initFields();
    }

    /**
     * This method will initialize all chess pieces
     */
    private void initializePieces(){
        //TODO puts the ChestPieces in starting position
    }

    private void initFields() {
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                boardFields[i][j] = new Field(i, j);
            }
        }
    }

    /**
     * Cycles through all Fields of the Chessboard and draws them to the canvas.
     */
    public void drawFields(Canvas canvas) {
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                boardFields[i][j].draw(canvas);
            }
        }
    }

    /**
     * Handles onTouchEvent fired by the BoardView (onTouchEvent) when the View is clicked on.
     * Cycles through the Chessboard fields and checks if the coordinates from the touchEvent match with
     * the coordinates from one of the Fields (Rectangles); it then translates the coordinates from the 2D Array
     * to chessboard coordinates and logs them (for now)
     *
     * @param event the event with the x and y coordinates of the touch event.
     */
    public void handleFieldClick(MotionEvent event) {
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();
        Rect rect;

        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                rect = boardFields[i][j].getRectangle();
                if (rect.contains(touchX, touchY)) {
                    Log.d(TAG, "handleChessBoardClick: " + boardFields[i][j].getChessCoordinates());
                }
            }
        }
    }

    /**
     * Calculates the size of a Rectangle in a Field with the help of the canvas width.
     * A Rectangle takes integer but the division of the canvas width delivers float, so the offset
     * for must be considered.
     *
     * @param canvas Used to get the size of the canvas
     * @param size Chessboard n x n size
     * @return returns the size 1 Rectangle should have
     */

    private int calculateRectSize(Canvas canvas, byte size) {
        float canvasWidth = canvas.getWidth();
        float offset = canvasWidth % 8;
        int rectSize = (int)canvasWidth / size - (int)offset;
        return rectSize;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Field[][] getBoardFields() {
        return boardFields;
    }
}
