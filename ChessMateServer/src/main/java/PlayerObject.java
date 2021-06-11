import NetObjects.ChessPieceColour;
import com.esotericsoftware.kryonet.Connection;

public class PlayerObject {
    String name;
    Connection connection;
    ChessPieceColour chessPieceColour;

    public PlayerObject(Connection connection, String name,ChessPieceColour colour){
        this.connection = connection;
        this.name = name;
        this.chessPieceColour = colour;
    }

    @Override
    public String toString() {
        return "["+name+"]<"+connection+"> Color:"+chessPieceColour;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ChessPieceColour getChessPieceColour() {
        return chessPieceColour;
    }

    public void setChessPieceColour(ChessPieceColour chessPieceColour) {
        this.chessPieceColour = chessPieceColour;
    }
}
