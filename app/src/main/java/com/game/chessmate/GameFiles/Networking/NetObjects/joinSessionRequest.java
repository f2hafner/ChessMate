package com.game.chessmate.GameFiles.Networking.NetObjects;

public class joinSessionRequest {
    String lobbycode;
    public joinSessionRequest(){}
    public String getLobbycode() { return lobbycode; }
    public void setLobbycode(String lobbycode) { this.lobbycode = lobbycode; }
}
