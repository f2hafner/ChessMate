package com.game.chessmate.GameFiles.Networking;

public class PlayerObject {
    String name;
    PlayerColor playerColor;
    boolean isHost;

    public PlayerObject(String name, PlayerColor playerColor, boolean isHost) {
        this.name = name;
        this.playerColor = playerColor;
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
