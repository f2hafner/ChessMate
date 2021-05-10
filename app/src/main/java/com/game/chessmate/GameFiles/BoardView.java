package com.game.chessmate.GameFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.game.chessmate.HomeActivity;
import com.game.chessmate.MainActivity;

import static android.content.ContentValues.TAG;

/**
 * The View in which the Chessboard is embedded.
 */
public class BoardView extends ViewGroup {

    private ChessBoard board;

    /**
     * Instantiates a new Board view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        this.setOnTouchListener(boardClickListener);
        board = ChessBoard.getInstance();
        board.initChessBoard(this, getResources(), width);
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
     * onLayout needs to call viewChild.layout() on every child of the ViewGroup to set their size
     *
     * @param changed
     * @param l left coordinates
     * @param t top coordinates
     * @param r right coordinates
     * @param b bottom coordinates
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < this.getChildCount(); i++) {
            Field field = (Field)this.getChildAt(i);
            this.getChildAt(i).layout(l,t,r,b);
        }
    }
}