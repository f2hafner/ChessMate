package com.game.chessmate.GameFiles;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import com.game.chessmate.GameFiles.PlayingPieces.Bishop;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceType;
import com.game.chessmate.GameFiles.PlayingPieces.King;
import com.game.chessmate.GameFiles.PlayingPieces.Knight;
import com.game.chessmate.GameFiles.PlayingPieces.Pawn;
import com.game.chessmate.GameFiles.PlayingPieces.Queen;
import com.game.chessmate.GameFiles.PlayingPieces.Rook;
import com.game.chessmate.R;
import java.util.ArrayList;

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
     * @piecesPlayer1 the ChessPieces that belong to player1
     * @piecesPlayer2 the ChessPieces that belong to player2
     * @legalMoves contains the Fields that the selected ChessPiece is allowed to move to.
     */
    private BoardView view;
    private Field[][] boardFields;
    private int fieldSize;
    private final int boardSize = 8;
    private ArrayList<ChessPiece> piecesPlayer1;
    private ArrayList<ChessPiece> piecesPlayer2;
    private ArrayList<Field> legalMovesSelected;
    private Field lastSelectedField = null;

    private ChessBoard() {
        this.boardFields = new Field[8][8];
        piecesPlayer1 = new ArrayList<>();
        piecesPlayer2 = new ArrayList<>();
        legalMovesSelected = new ArrayList<>();
    }

    /**
     * Initializes the 2D Array of Fields, its ChessPieces and calculates the Field size(Rectangle size) with the width of the canvas
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
        initPiecesPlayer1(resources, ChessPieceColour.WHITE);
        initPiecesPlayer2(resources, ChessPieceColour.BLACK);
    }

    /**
     * Initializes the Field Views of the Chessboard and adds them to them ViewGroup (BoardView).
     * The currentChessPiece on the Fields will be null at the start.
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
     * @param resources the resource context for the ChessPiece Sprites
     */
    private void initPiecesPlayer1(Resources resources, ChessPieceColour color) {
        initPieces(ChessPieceType.PAWN,  6, 0, 8, this.piecesPlayer1, ResourceLoader.getPawn_player1(), color);
        initPieces(ChessPieceType.ROOK,  7, 0, 1, this.piecesPlayer1, ResourceLoader.getRook_player1(),  color);
        initPieces(ChessPieceType.ROOK,  7, 7, 1, this.piecesPlayer1, ResourceLoader.getRook_player1(),  color);
        initPieces(ChessPieceType.KNIGHT,  7, 1, 1, this.piecesPlayer1, ResourceLoader.getKnight_player1(), color);
        initPieces(ChessPieceType.KNIGHT,  7, 6, 1, this.piecesPlayer1, ResourceLoader.getKnight_player1(), color);
        initPieces(ChessPieceType.BISHOP,  7, 2, 1, this.piecesPlayer1, ResourceLoader.getBishop_player1(), color);
        initPieces(ChessPieceType.BISHOP,  7, 5, 1, this.piecesPlayer1, ResourceLoader.getBishop_player1(), color);
        initPieces(ChessPieceType.QUEEN,  7, 4, 1, this.piecesPlayer1, ResourceLoader.getQueen_player1(), color);
        initPieces(ChessPieceType.KING,  7, 3, 1, this.piecesPlayer1, ResourceLoader.getKing_player1(), color);
    }

    /**
     * Initializes the pieces for player2. See initPieces for details on the creation.
     *
     * @param resources the resource context for the ChessPiece Sprites
     */
    private void initPiecesPlayer2(Resources resources, ChessPieceColour color) {
        initPieces(ChessPieceType.PAWN,  1, 0, 8, this.piecesPlayer2, ResourceLoader.getPawn_player2(), color);
        initPieces(ChessPieceType.ROOK,  0, 0, 1, this.piecesPlayer2, ResourceLoader.getRook_player2(), color);
        initPieces(ChessPieceType.ROOK,  0, 7, 1, this.piecesPlayer2, ResourceLoader.getRook_player2(), color);
        initPieces(ChessPieceType.KNIGHT,  0, 1, 1, this.piecesPlayer2, ResourceLoader.getKnight_player2(), color);
        initPieces(ChessPieceType.KNIGHT,  0, 6, 1, this.piecesPlayer2, ResourceLoader.getKnight_player2(), color);
        initPieces(ChessPieceType.BISHOP,  0, 2, 1, this.piecesPlayer2, ResourceLoader.getBishop_player2(), color);
        initPieces(ChessPieceType.BISHOP,  0, 5, 1, this.piecesPlayer2, ResourceLoader.getBishop_player2(), color);
        initPieces(ChessPieceType.QUEEN,  0, 4, 1, this.piecesPlayer2, ResourceLoader.getQueen_player2(), color);
        initPieces(ChessPieceType.KING,  0, 3, 1, this.piecesPlayer2, ResourceLoader.getKing_player2(), color);
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

                    if(clickedField.getCurrentPiece() != null){
                        //TODO - differentiate between piece of opponent and mine - only set to null if it is my color (user selected different piece to move)
                        lastSelectedField = null;
                        resetLegalMoves();
                    }
                    if(lastSelectedField == null){//this is the first click on a field
                        if (clickedField.getCurrentPiece() != null) {
                            lastSelectedField = clickedField;
                            this.legalMovesSelected = clickedField.getCurrentPiece().getLegalFields();
                            if(!legalMovesSelected.isEmpty()){
                                drawLegalMoves(legalMovesSelected);
                            }
                        }
                    }else{//this is the second click
                        if(legalMovesSelected.contains(clickedField)){
                            lastSelectedField.getCurrentPiece().move(clickedField);
                            lastSelectedField = null;
                            resetLegalMoves();
                        }else{
                            lastSelectedField = null;
                        }
                    }

                }
            }
        }
    }

    private void resetLegalMoves() {
        for(Field f : legalMovesSelected) {
            f.setRectangleDefaultColor();
            f.setUpdate(true);
        }
    }

    /**
     * Sets all fields in ArrayList fieldsToMove as legal to move to. Then calls redraw of view to mark the fields as legal.
     * When a new fieldsToMove Array is passed, the old Fields will be reset to their original color.
     * @param fieldsToMove ArrayList of Fields that are legal for the currently selected ChessPiece to move to
     */
    private void drawLegalMoves(ArrayList<Field> fieldsToMove) {
        resetLegalMoves();

        if(!fieldsToMove.isEmpty()){
            for(Field f : fieldsToMove){
                f.setAsLegal();
                f.invalidate();
            }
        }
    }

    /**
     * Calculates the size of a Rectangle in a Field width the help of the screen size metrics width
     * A Rectangle takes integer but the division of the width width delivers float, so the offset
     * must be considered.
     *
     * @param width The size of the screen
     * @return returns the size 1 Rectangle should have
     */
    public int calculateRectSize(int width) {
        float canvasWidth = width;
        float offset = canvasWidth % 8;
        int rectSize = (int)canvasWidth / this.boardSize - (int)offset;
        return rectSize;
    }

    /**
     *
     * @param type the type of ChessPiece to init. ex. ChessPieceType.Pawn.
     * @sprite the bitmap of this ChessPiece.
     * @param row the row in which the ChessPieces should be placed.
     * @param offset the startPoint in the 2D Array boardFields at which the placement of the ChessPieces should begin.
     *               the row param is the the row, the offset param is the column.
     * @param length specifies the amount of ChessPieces of the param @type that should be placed next to each other. (ex. pawns 1-8).
     * @param piecesPlayer the array that contains the players pieces.
     */
    private void initPieces(ChessPieceType type, int row, int offset, int length, ArrayList<ChessPiece> piecesPlayer, Bitmap sprite, ChessPieceColour colour) {
        for (int j = offset; j < offset + length; j++) {
            Field field = boardFields[row][j];
            ChessPiece piece = null;

            switch(type) {
                case PAWN: piece = new Pawn(field, sprite, view.getContext(), null, colour); break;
                case ROOK: piece = new Rook(field, sprite, view.getContext(), null, colour); break;
                case BISHOP: piece = new Bishop(field, sprite, view.getContext(), null, colour); break;
                case KNIGHT: piece = new Knight(field, sprite, view.getContext(), null, colour); break;
                case QUEEN: piece = new Queen(field, sprite, view.getContext(), null, colour); break;
                case KING: piece = new King(field, sprite, view.getContext(), null, colour); break;
                default: throw new UnsupportedOperationException();
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
