package com.game.chessmate.GameFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import com.game.chessmate.HomeActivity;
import com.game.chessmate.R;

public class ResourceLoader extends AsyncTask<Void, Void, Void> {

    private static Bitmap pawn_player1;
    private static Bitmap bishop_player1;
    private static Bitmap rook_player1;
    private static Bitmap knight_player1;
    private static Bitmap queen_player1;
    private static Bitmap king_player1;
    private static Bitmap pawn_player2;
    private static Bitmap bishop_player2;
    private static Bitmap rook_player2;
    private static Bitmap knight_player2;
    private static Bitmap queen_player2;
    private static Bitmap king_player2;
    Resources resources;
    int screenWidth;
    int fieldSize;
    Context context;

    public ResourceLoader(Resources resources, int screenWidth, Context context) {
        this.resources = resources;
        this.screenWidth = screenWidth;
        this.context = context;
        ChessBoard board = ChessBoard.getInstance();
        fieldSize = board.calculateRectSize(screenWidth);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        pawn_player1 = loadBitmap(R.drawable.pawn_player1);
        bishop_player1 = loadBitmap(R.drawable.bishop_player1);
        rook_player1 = loadBitmap(R.drawable.rook_player1);
        knight_player1 = loadBitmap(R.drawable.knight_player1);
        queen_player1 = loadBitmap(R.drawable.queen_player1);
        king_player1 = loadBitmap(R.drawable.king_player1);

        pawn_player2 = loadBitmap(R.drawable.pawn_player2);
        bishop_player2 = loadBitmap(R.drawable.bishop_player2);
        rook_player2 = loadBitmap(R.drawable.rook_player2);
        knight_player2 = loadBitmap(R.drawable.knight_player2);
        queen_player2 = loadBitmap(R.drawable.queen_player2);
        king_player2 = loadBitmap(R.drawable.king_player2);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(context, HomeActivity.class);
            context.startActivity(homeIntent);
            ((Activity)context).finish();
        }, 500);
    }

    /**
     * Extracts the bitmap of this ChessPiece from Ressources
     */
    public Bitmap loadBitmap(int drawableId){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(resources, drawableId);
        bitmap = scaleBitmapToFieldSize(bitmap);
        return bitmap;
    }

    /**
     * Scales the bitmap of this PlayingPiece to the size of the rectangle container.
     */
    private Bitmap scaleBitmapToFieldSize(Bitmap bitmap) {
        Rect rectangle = new Rect();

        rectangle.left = 0;
        rectangle.top = 0;
        rectangle.right = rectangle.left + fieldSize;
        rectangle.bottom = rectangle.top + fieldSize;

        int width = rectangle.width();
        int height = rectangle.height();

        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        return bitmap;
    }

    public static Bitmap getPawn_player1() {
        return pawn_player1;
    }

    public static Bitmap getBishop_player1() {
        return bishop_player1;
    }

    public static Bitmap getRook_player1() {
        return rook_player1;
    }

    public static Bitmap getKnight_player1() {
        return knight_player1;
    }

    public static Bitmap getQueen_player1() {
        return queen_player1;
    }

    public static Bitmap getKing_player1() {
        return king_player1;
    }

    public static Bitmap getPawn_player2() {
        return pawn_player2;
    }

    public static Bitmap getBishop_player2() {
        return bishop_player2;
    }

    public static Bitmap getRook_player2() {
        return rook_player2;
    }

    public static Bitmap getKnight_player2() {
        return knight_player2;
    }

    public static Bitmap getQueen_player2() {
        return queen_player2;
    }

    public static Bitmap getKing_player2() {
        return king_player2;
    }
}
