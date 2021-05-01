package com.game.chessmate.GameFiles;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.game.chessmate.GameFiles.PlayingPieces.Bishop;
import com.game.chessmate.GameFiles.PlayingPieces.King;
import com.game.chessmate.GameFiles.PlayingPieces.Knight;
import com.game.chessmate.GameFiles.PlayingPieces.Pawn;
import com.game.chessmate.GameFiles.PlayingPieces.PlayingPiece;
import com.game.chessmate.GameFiles.PlayingPieces.PlayingPieceType;
import com.game.chessmate.GameFiles.PlayingPieces.Queen;
import com.game.chessmate.GameFiles.PlayingPieces.Rook;
import com.game.chessmate.R;

import java.util.ArrayList;

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
    private BoardView view;
    private Field[][] boardFields;
    private int fieldSize;
    private final int boardSize = 8;
    ArrayList<PlayingPiece> piecesPlayer1;
    ArrayList<PlayingPiece> piecesPlayer2;

    private ChessBoard() {
        this.boardFields = new Field[8][8];
        piecesPlayer1 = new ArrayList<>();
        piecesPlayer2 = new ArrayList<>();
    }

    /**
     * Initializes the 2D Array of Fields and calculates the Field size(Rectangle size) with the width of the canvas
     * that is being drawn on.
     *
     * @param view the canvas which contains the Chessboard
     */
    public void initChessBoard(BoardView view, Resources resources){
        this.view = view;
        this.fieldSize = calculateRectSize(1130);
        initFields();
        initPiecesPlayer1(resources);
        initPiecesPlayer2(resources);
    }

    private void initFields() {
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                boardFields[i][j] = new Field(i, j);
                boardFields[i][j].setCurrentPiece(null);
            }
        }
    }

    private void initPiecesPlayer1(Resources resources) {
        initPieces(PlayingPieceType.PAWN, resources, 6, 0, 8, this.piecesPlayer1, R.drawable.pawn_player1);
        initPieces(PlayingPieceType.ROOK, resources, 7, 0, 1, this.piecesPlayer1, R.drawable.rook_player1);
        initPieces(PlayingPieceType.ROOK, resources, 7, 7, 1, this.piecesPlayer1, R.drawable.rook_player1);
        initPieces(PlayingPieceType.KNIGHT, resources, 7, 1, 1, this.piecesPlayer1, R.drawable.knight_player1);
        initPieces(PlayingPieceType.KNIGHT, resources, 7, 6, 1, this.piecesPlayer1, R.drawable.knight_player1);
        initPieces(PlayingPieceType.BISHOP, resources, 7, 2, 1, this.piecesPlayer1, R.drawable.bishop_player1);
        initPieces(PlayingPieceType.BISHOP, resources, 7, 5, 1, this.piecesPlayer1, R.drawable.bishop_player1);
        initPieces(PlayingPieceType.QUEEN, resources, 7, 4, 1, this.piecesPlayer1, R.drawable.queen_player1);
        initPieces(PlayingPieceType.KING, resources, 7, 3, 1, this.piecesPlayer1, R.drawable.king_player1);
    }

    private void initPiecesPlayer2(Resources resources) {
        initPieces(PlayingPieceType.PAWN, resources, 1, 0, 8, this.piecesPlayer2, R.drawable.pawn_player2);
        initPieces(PlayingPieceType.ROOK, resources, 0, 0, 1, this.piecesPlayer2, R.drawable.rook_player2);
        initPieces(PlayingPieceType.ROOK, resources, 0, 7, 1, this.piecesPlayer2, R.drawable.rook_player2);
        initPieces(PlayingPieceType.KNIGHT, resources, 0, 1, 1, this.piecesPlayer2, R.drawable.knight_player2);
        initPieces(PlayingPieceType.KNIGHT, resources, 0, 6, 1, this.piecesPlayer2, R.drawable.knight_player2);
        initPieces(PlayingPieceType.BISHOP, resources, 0, 2, 1, this.piecesPlayer2, R.drawable.bishop_player2);
        initPieces(PlayingPieceType.BISHOP, resources, 0, 5, 1, this.piecesPlayer2, R.drawable.bishop_player2);
        initPieces(PlayingPieceType.QUEEN, resources, 0, 4, 1, this.piecesPlayer2, R.drawable.queen_player2);
        initPieces(PlayingPieceType.KING, resources, 0, 3, 1, this.piecesPlayer2, R.drawable.king_player2);
    }

    public void drawPieces(Canvas canvas) {
        for (int i = 0; i < piecesPlayer1.size(); i++) {

            Bitmap spritePlayer1 = piecesPlayer1.get(i).getDrawable();
            Bitmap spritePlayer2 = piecesPlayer2.get(i).getDrawable();
            Rect fieldPlayer1 = piecesPlayer1.get(i).getPosition().getRectangle();
            Rect fieldPlayer2 = piecesPlayer2.get(i).getPosition().getRectangle();
            canvas.drawBitmap(spritePlayer1,null, fieldPlayer1, null);
            canvas.drawBitmap(spritePlayer2,null, fieldPlayer2, null);
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
     * to chessboard coordinates and logs them.
     * Uses Rectangle that was clicked on to determine field and with that chess piece that was clicked on. Then calls Method
     * to determine legalMoves of ChessPiece and calls method to draw legalMoves.
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
                    Field clickedField = boardFields[i][j];
                    ArrayList<Field> fieldsToMove = clickedField.getCurrentPiece().getLegalFields();
                    if(!fieldsToMove.isEmpty()){
                        drawLegalMoves(fieldsToMove);
                    }
                }
            }
        }
    }

    /**
     * Sets all fields in ArrayList fieldsToMove as legal to move to. Then calls redraw of view.
     * @param fieldsToMove ArrayList of Fields that are legal for the currently selected ChessPiece to move to
     */
    private void drawLegalMoves(ArrayList<Field> fieldsToMove) {
        for(Field f : fieldsToMove){
            f.setAsLegal();
        }
        view.invalidate();

    }

    /**
     * Calculates the size of a Rectangle in a Field with the help of the canvas width.
     * A Rectangle takes integer but the division of the canvas width delivers float, so the offset
     * for must be considered.
     *
     * @param canvas Used to get the size of the canvas
     * @return returns the size 1 Rectangle should have
     */
    private int calculateRectSize(int canvas) {
        float canvasWidth = canvas;
        float offset = canvasWidth % 8;
        int rectSize = (int)canvasWidth / this.boardSize - (int)offset;
        return rectSize;
    }

    private void initPieces(PlayingPieceType type, Resources resources, int row, int offset, int length, ArrayList<PlayingPiece> piecesPlayer, int drawableId) {
        for (int j = offset; j < offset + length; j++) {
            Field field = boardFields[row][j];
            PlayingPiece piece = null;
            switch(type) {
                case PAWN: piece = new Pawn(field, resources, drawableId); break;
                case ROOK: piece = new Rook(field, resources, drawableId); break;
                case BISHOP: piece = new Bishop(field, resources, drawableId); break;
                case KNIGHT: piece = new Knight(field, resources, drawableId); break;
                case QUEEN: piece = new Queen(field, resources, drawableId); break;
                case KING: piece = new King(field, resources, drawableId); break;
            }

            piecesPlayer.add(piece);
            field.setCurrentPiece(piece);
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Field[][] getBoardFields() {
        return boardFields;
    }
}
