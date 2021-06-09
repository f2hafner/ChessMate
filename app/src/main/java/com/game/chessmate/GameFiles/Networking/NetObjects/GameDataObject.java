package com.game.chessmate.GameFiles.Networking.NetObjects;

public class GameDataObject {
    FieldDataObject origin;
    FieldDataObject target;

    public GameDataObject() {}

    public FieldDataObject getOrigin() {
        return origin;
    }

    public void setOrigin(FieldDataObject origin) {
        this.origin = origin;
    }

    public FieldDataObject getTarget() {
        return target;
    }

    public void setTarget(FieldDataObject target) {
        this.target = target;
    }
}
