package com.game.chessmate.GameFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;

import com.game.chessmate.HomeActivity;
import com.game.chessmate.R;

/**
 * Resource Loader. Loads Game sprites during the splash screen.
 */
public class ResourceLoader extends AsyncTask<Void, Void, Void> {

    private static Bitmap pawnPlayer1;
    private static Bitmap bishopPlayer1;
    private static Bitmap rookPlayer1;
    private static Bitmap knightPlayer1;
    private static Bitmap queenPlayer1;
    private static Bitmap kingPlayer1;
    private static Bitmap pawnPlayer2;
    private static Bitmap bishopPlayer2;
    private static Bitmap rookPlayer2;
    private static Bitmap knightPlayer2;
    private static Bitmap queenPlayer2;
    private static Bitmap kingPlayer2;
    private static Resources resources;
    private static int fieldSize;
    private Context context;

    /**
     * Instantiates a new Resource loader.
     *
     * @param resources   the resources
     * @param screenWidth the screen width
     * @param context     the context
     */
    public ResourceLoader(Resources resources, int screenWidth, Context context) {
        this.resources = resources;
        this.context = context;
        ChessBoard board = ChessBoard.getInstance();
        fieldSize = board.calculateRectSize(screenWidth);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loadBitmapFromResources();
        return null;
    }

    /**
     * Redirect to home-screen after loading the resources.
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(context, HomeActivity.class);
            context.startActivity(homeIntent);
            ((Activity)context).finish();
        }, 500);
    }

    /**
     * Loads the sprites of the chess pieces into bitmaps.
     */
    private static void loadBitmapFromResources() {
        pawnPlayer1 = loadBitmap(R.drawable.pawn_player1);
        bishopPlayer1 = loadBitmap(R.drawable.bishop_player1);
        rookPlayer1 = loadBitmap(R.drawable.rook_player1);
        knightPlayer1 = loadBitmap(R.drawable.knight_player1);
        queenPlayer1 = loadBitmap(R.drawable.queen_player1);
        kingPlayer1 = loadBitmap(R.drawable.king_player1);

        pawnPlayer2 = loadBitmap(R.drawable.pawn_player2);
        bishopPlayer2 = loadBitmap(R.drawable.bishop_player2);
        rookPlayer2 = loadBitmap(R.drawable.rook_player2);
        knightPlayer2 = loadBitmap(R.drawable.knight_player2);
        queenPlayer2 = loadBitmap(R.drawable.queen_player2);
        kingPlayer2 = loadBitmap(R.drawable.king_player2);
    }

    /**
     * Extracts the bitmap of this ChessPiece from resources
     *
     * @param drawableId the drawable id
     * @return the bitmap
     */
    public static Bitmap loadBitmap(int drawableId){
        Bitmap bitmap = decodeFromResources(drawableId);
        return scaleBitmapToFieldSize(bitmap);
    }

    /**
     * Scales the bitmap of this PlayingPiece to the size of the rectangle container.
     */
    private static Bitmap scaleBitmapToFieldSize(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, fieldSize, fieldSize, false);
    }

    /**
     * Decodes a bitmap from resources with @param drawableId and returns the bitmap.
     * @param drawableId
     * @return
     */
    private static Bitmap decodeFromResources(int drawableId) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(resources, drawableId);
        if (bitmap == null) {
            throw new RuntimeException("Could not decode bitmap from resources. DrawableId: " + drawableId );
        }
        return bitmap;
    }

    /**
     * Gets pawn player 1.
     *
     * @return the pawn player 1
     */
    public static Bitmap getPawnPlayer1() {
        return pawnPlayer1;
    }

    /**
     * Gets bishop player 1.
     *
     * @return the bishop player 1
     */
    public static Bitmap getBishopPlayer1() {
        return bishopPlayer1;
    }

    /**
     * Gets rook player 1.
     *
     * @return the rook player 1
     */
    public static Bitmap getRookPlayer1() {
        return rookPlayer1;
    }

    /**
     * Gets knight player 1.
     *
     * @return the knight player 1
     */
    public static Bitmap getKnightPlayer1() {
        return knightPlayer1;
    }

    /**
     * Gets queen player 1.
     *
     * @return the queen player 1
     */
    public static Bitmap getQueenPlayer1() {
        return queenPlayer1;
    }

    /**
     * Gets king player 1.
     *
     * @return the king player 1
     */
    public static Bitmap getKingPlayer1() {
        return kingPlayer1;
    }

    /**
     * Gets pawn player 2.
     *
     * @return the pawn player 2
     */
    public static Bitmap getPawnPlayer2() {
        return pawnPlayer2;
    }

    /**
     * Gets bishop player 2.
     *
     * @return the bishop player 2
     */
    public static Bitmap getBishopPlayer2() {
        return bishopPlayer2;
    }

    /**
     * Gets rook player 2.
     *
     * @return the rook player 2
     */
    public static Bitmap getRookPlayer2() {
        return rookPlayer2;
    }

    /**
     * Gets knight player 2.
     *
     * @return the knight player 2
     */
    public static Bitmap getKnightPlayer2() {
        return knightPlayer2;
    }

    /**
     * Gets queen player 2.
     *
     * @return the queen player 2
     */
    public static Bitmap getQueenPlayer2() {
        return queenPlayer2;
    }

    /**
     * Gets king player 2.
     *
     * @return the king player 2
     */
    public static Bitmap getKingPlayer2() {
        return kingPlayer2;
    }
}
