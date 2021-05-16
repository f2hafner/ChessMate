package com.game.chessmate.GameFiles;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
     * @view the ViewGroup (BoardView) containing all its children (Fields)
     * @boardFields the 2D Field array resembling the chessboard.
     * @fieldSize calculated size of 1 Field, calculation is done with the screen metrics.
     * @boardSize size of the chessboard, n x n
     * @piecesPlayer1 the PlayingPieces that belong to player1
     * @piecesPlayer2 the PlayingPieces that belong to player2
     * @legalMoves contains the Fields that the selected PlayingPiece is allowed to move to.
     */
    private BoardView view;
    private Field[][] boardFields;
    private int fieldSize;
    private final int boardSize = 8;
    private ArrayList<PlayingPiece> piecesPlayer1;
    private ArrayList<PlayingPiece> piecesPlayer2;
    private ArrayList<Field> legalMoves;

    private ChessBoard() {
        this.boardFields = new Field[8][8];
        piecesPlayer1 = new ArrayList<>();
        piecesPlayer2 = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }

    /**
     * Initializes the 2D Array of Fields, its PlayingPieces and calculates the Field size(Rectangle size) with the width of the canvas
     * that is being drawn on.
     *
     * @param view      the canvas which contains the Chessboard
     * @param resources the resources
     * @param width     the width
     */
    public void initChessBoard(BoardView view, Resources resources, int width){
        this.view = view;
        this.fieldSize = calculateRectSize(width);
        initFields();
        initPiecesPlayer1(resources);
        initPiecesPlayer2(resources);
    }

    /**
     * Initializes the Field Views of the Chessboard and adds them to them ViewGroup (BoardView).
     * The currentPlayingPiece on the Fields will be null at the start.
     */
    private void initFields() {
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                Field field = new Field(i, j, view.getContext(), null);
                boardFields[i][j] = field;
                view.addView(field);
                boardFields[i][j].setCurrentPiece(null);
            }
        }
    }

    /**
     * Initializes the pieces for player1. See initPieces for details on the creation.
     *
     * @param resources the resource context for the PlayingPiece Sprites
     */
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

    /**
     * Initializes the pieces for player2. See initPieces for details on the creation.
     *
     * @param resources the resource context for the PlayingPiece Sprites
     */
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
                    Field clickedField = boardFields[i][j];
                    ArrayList<Field> fieldsToMove = new ArrayList<>();
                    if (clickedField.getCurrentPiece() != null) {
                        fieldsToMove = clickedField.getCurrentPiece().getLegalFields();
                        boardFields[i][j].getCurrentPiece().move(boardFields[i - 1][j + 1]);  // TODO Delete cause this is a test call to test PlayingPiece.move()
                    }

                    if(!fieldsToMove.isEmpty()){
                        drawLegalMoves(fieldsToMove);
                    }
                }
            }
        }
    }

    /**
     * Sets all fields in ArrayList fieldsToMove as legal to move to. Then calls redraw of view to mark the fields as legal.
     * When a new fieldsToMove Array is passed, the old Fields will be reset to their original color.
     * @param fieldsToMove ArrayList of Fields that are legal for the currently selected ChessPiece to move to
     */
    private void drawLegalMoves(ArrayList<Field> fieldsToMove) {

        for(Field f : legalMoves) {
            f.setRectangleDefaultColor();
            f.setUpdate(true);
        }

        for(Field f : fieldsToMove){
            f.setAsLegal();
            f.setUpdate(true);
        }

        this.legalMoves = fieldsToMove;
    }

    /**
     * Calculates the size of a Rectangle in a Field width the help of the screen size metrics width
     * A Rectangle takes integer but the division of the width width delivers float, so the offset
     * must be considered.
     *
     * @param width The size of the screen
     * @return returns the size 1 Rectangle should have
     */
    private int calculateRectSize(int width) {
        float canvasWidth = width;
        float offset = canvasWidth % 8;
        int rectSize = (int)canvasWidth / this.boardSize - (int)offset;
        return rectSize;
    }

    /**
     *
     * @param type the type of PlayingPiece to init. ex. PlayingPieceType.Pawn.
     * @param resources the resource context for the PlayingPiece sprite.
     * @param row the row in which the PlayingPieces should be placed.
     * @param offset the startPoint in the 2D Array boardFields at which the placement of the PlayingPieces should begin.
     *               the row param is the the row, the offset param is the column.
     * @param length specifies the amount of playingPieces of the param @type that should be placed next to each other. (ex. pawns 1-8).
     * @param piecesPlayer the array that contains the players pieces.
     * @param drawableId the id of the drawable resource.
     */
    private void initPieces(PlayingPieceType type, Resources resources, int row, int offset, int length, ArrayList<PlayingPiece> piecesPlayer, int drawableId) {
        for (int j = offset; j < offset + length; j++) {
            Field field = boardFields[row][j];
            PlayingPiece piece = null;
            switch(type) {
                case PAWN: piece = new Pawn(field, resources, drawableId, view.getContext(), null); break;
                case ROOK: piece = new Rook(field, resources, drawableId, view.getContext(), null); break;
                case BISHOP: piece = new Bishop(field, resources, drawableId, view.getContext(), null); break;
                case KNIGHT: piece = new Knight(field, resources, drawableId, view.getContext(), null); break;
                case QUEEN: piece = new Queen(field, resources, drawableId, view.getContext(), null); break;
                case KING: piece = new King(field, resources, drawableId, view.getContext(), null); break;
            }

            piecesPlayer.add(piece);
            field.setCurrentPiece(piece);
            view.addView((View)piece);
        }
    }

    /**
     * Gets field size.
     *
     * @return the field size
     */
    public int getFieldSize() {
        return fieldSize;
    }

    /**
     * Get board fields field [ ] [ ].
     *
     * @return the field [ ] [ ]
     */
    public Field[][] getBoardFields() {
        return boardFields;
    }
}
