package com.game.chessmate.GameFiles.Networking.NetObjects;

public class ErrorPacket {
    String ErrorMsg;

    public ErrorPacket() {}

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
