package com.game.chessmate.GameFiles;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class RenderThread implements Runnable {

    private ChessBoard board;
    private boolean running;
    private int targetFPS = 30;
    private double averageFPS;

    public RenderThread(BoardView view) {
        this.board = ChessBoard.getInstance();
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while(running) {
            startTime = System.nanoTime();

            Field[][] fields = this.board.getBoardFields();
            for (Field[] rowFields : fields) {
                for (Field f : rowFields) {
                    if (f.getUpdate()) {
                        f.setUpdate(false);
                        if (f.getCurrentPiece() != null && f.getCurrentPiece().isUpdatePosition()) {
                            f.getCurrentPiece().updateOffsets();
                        }
                        f.invalidate();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                Thread.sleep(waitTime);
            }catch (Exception e) {
                Log.w(TAG, "run: RenderThread", e);
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == targetFPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d(TAG, "run: FPS: "  + averageFPS);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
