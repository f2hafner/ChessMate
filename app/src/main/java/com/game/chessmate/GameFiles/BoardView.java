package com.game.chessmate.GameFiles;

import android.content.Context;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

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
        board = ChessBoard.getInstance();
        board.initChessBoard(this, getResources());
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
        board.drawFields(canvas);
        board.drawPieces(canvas);
    }
}