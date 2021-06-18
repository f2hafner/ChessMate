package com.game.chessmate.GameFiles;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;

/**
 * The Field class is an Object that represents a PlayingField on the chessboard.
 */
public class Field extends View {

    /**
     * @param x x coordinate of the Field on the Chessboard Array
     * @param y y coordinate of the Field on the Chessboard Array
     * @param color color of the Rectangle that is the Field
     * @param rectangle is used to display the Field on the Chessboard for now.
     * @param playingPiece the Chess piece on this Field
     */
    private int x;
    private int y;
    /**
     * The Color.
     */
    public Paint color;
    /**
     * The Color legal.
     */
    public Paint colorLegal;
    private Rect rectangle;
    private ChessPiece currentPiece;
    private boolean update;
    private boolean blocked=false;
    private boolean legal = false;
    private boolean isProtected=false;

    /**
     * Constructor of Field. Set coordinates, compute color of the Rectangle with x and y, and construct the rectangle
     *
     * @param x       the x
     * @param y       the y
     * @param context the context
     * @param attrs   the attrs
     */
    public Field (int x, int y, Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.x = x;
        this.y = y;

        this.color = new Paint();
        this.colorLegal = new Paint();
        this.colorLegal.setColor(Color.parseColor("#74ff52"));
        setRectangleDefaultColor();
        rectangle = new Rect();
        setupRectangle(rectangle);
    }

    /**
     * onDraw inherited from View. call invalidate on the field to redraw the rectangle.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rectangle, color);
        if(legal){
            int fieldSize = ChessBoard.getInstance().getFieldSize();
            canvas.drawCircle(fieldSize*this.y+fieldSize/2,fieldSize*this.x+fieldSize/2, (float)(fieldSize/2.7), colorLegal);
            Log.d("debug", "I drew a circle");
        }
    }

    /**
     * Returns true if the field has a playing piece on it
     *
     * @return boolean boolean
     */
    public boolean hasPiece(){
        if (currentPiece == null) {
            return false;
        }
        else return true;
    }

    /**
     * Gets the current Piece that is on the Playing Field.
     *
     * @return PlayingPiece chess piece
     */
    public ChessPiece getCurrentPiece(){
        return this.currentPiece;
    }

    /**
     * Init the Rectangle by setting the coordinates for left, top, right, bottom.
     *
     * @param rectangle the rectangle
     */
    public void setupRectangle(Rect rectangle) {
        int fieldSize = ChessBoard.getInstance().getFieldSize();
        this.rectangle = rectangle;
        this.rectangle.left = fieldSize * this.y;
        this.rectangle.top = fieldSize * this.x;
        this.rectangle.right = this.rectangle.left + fieldSize;
        this.rectangle.bottom = this.rectangle.top + fieldSize;
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rect getRectangle() {
        return rectangle;
    }

    /**
     * Use the helper class to translate the coordinates in the 2D Array to Chess coordinates.
     *
     * @return String containing the Chess coordinates of the Field.
     */
    public String getChessCoordinates() {
        return ArrayToChessCoordinatesTranslator.translateCoordinates(this.x, this.y);
    }

    /**
     * Sets the rectangle to its default color based on the chessboard layout.
     */
    public void setRectangleDefaultColor(){
        if(this.isBlocked()==false)
            color.setColor((this.x + this.y) % 2 == 0 ? Color.parseColor("#838381") : Color.parseColor("#d5d8db"));
    }

    /**
     * Sets current piece.
     *
     * @param currentPiece the current piece
     */
    public void setCurrentPiece(ChessPiece currentPiece) {
        this.currentPiece = currentPiece;
    }

    /**
     * Get field x int.
     *
     * @return the int
     */
    public int getFieldX(){
        return this.x;
    }

    /**
     * Get field y int.
     *
     * @return the int
     */
    public int getFieldY(){
        return this.y;
    }

    /**
     * Set as legal.
     */
    public void setAsLegal(){
        this.legal = true;
    }

    /**
     * Set as illegal.
     */
    public void setAsIllegal(){
        this.legal = false;
    }

    /**
     * Gets update.
     *
     * @return the update
     */
    public boolean getUpdate() {
        return this.update;
    }

    /**
     * Sets update.
     *
     * @param update the update
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * Set playing piece shield.
     */
    public  void setPlayingPieceShield(){
        setProtected(true);
        color.setColor(Color.BLUE);
    }

    /**
     * Sets blocked.
     */
    public void setBlocked() {
        this.blocked = true;
        color.setColor(Color.rgb(218,165,32));
    }

    /**
     * Is blocked boolean.
     *
     * @return the boolean
     */
    public boolean isBlocked(){return this.blocked;}

    /**
     * Mark champion.
     */
    public void markChampion(){
        color.setColor(Color.YELLOW);
    }

    /**
     * Is even boolean.
     *
     * @return the boolean
     */
    public boolean isEven (){
        if ((this.x+this.y)%2==0)
            return true;
        return false;
    }

    /**
     * Is protected boolean.
     *
     * @return the boolean
     */
    public boolean isProtected(){return this.isProtected;}

    /**
     * Set protected.
     *
     * @param isProtected the is protected
     */
    public void setProtected(boolean isProtected){this.isProtected=isProtected;}

    /**
     * Sets as checking.
     */
    public void setAsChecking() {
        this.color.setColor(Color.RED);
    }
}
