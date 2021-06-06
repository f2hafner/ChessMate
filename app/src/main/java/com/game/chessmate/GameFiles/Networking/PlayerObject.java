package com.game.chessmate.GameFiles.Networking;

import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;

public class PlayerObject {
    String name;
    ChessPieceColour playerColor;
    boolean isHost;

    public PlayerObject(String name, ChessPieceColour playerColour, boolean isHost) {
        this.name = name;
        this.playerColor = playerColour;
        this.isHost = isHost;
    }

    @Override
    public String toString() {
        return "PlayerObject{" +
                "name='" + name + '\'' +
                ", playerColor=" + playerColor +
                ", isHost=" + isHost +
                '}';
    }
}
