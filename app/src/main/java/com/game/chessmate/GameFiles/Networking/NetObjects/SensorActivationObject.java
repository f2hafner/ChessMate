package com.game.chessmate.GameFiles.Networking.NetObjects;

public class SensorActivationObject {
    String lobbyCode;
    SensorActivationObject(){}

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }
}
