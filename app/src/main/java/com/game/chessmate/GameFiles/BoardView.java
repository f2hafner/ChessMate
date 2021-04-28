package com.game.chessmate.GameFiles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

/**
 * The View in which the Chessboard is embedded.
 */
public class BoardView extends View {

    private ChessBoard board;

    /**
     * Instantiates a new Board view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(boardClickListener);
    }

    /**
     * @param boardClickListener This is used to catch onTouchEvents on the Chessboard.
     */
    private View.OnTouchListener boardClickListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               board.handleFieldClick(event);
            }
            return true;
        }
    };

    /**
     * Creates the instance of the Chessboard, inits the Chessboard and draws the Chessboard
     * fields to the canvas
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        byte boardSize = 8;
        board = ChessBoard.getInstance();
        board.create(boardSize, canvas, getResources());
        board.drawFields(canvas);
        board.drawPieces(canvas);
    }
}