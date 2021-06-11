package NetObjects;

public class PlayerDataObject {
    String name;

    public ChessPieceColour getChessPieceColour() {
        return chessPieceColour;
    }

    public void setChessPieceColour(ChessPieceColour chessPieceColour) {
        this.chessPieceColour = chessPieceColour;
    }

    ChessPieceColour chessPieceColour;

    public PlayerDataObject(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
