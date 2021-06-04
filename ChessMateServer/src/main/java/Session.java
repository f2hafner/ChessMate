import java.util.Random;

public class Session {
    long lobbyID;
    //TODO states enum
    String lobbycode;
    byte playercount;
    String player1_name;
    String player2_name;
    boolean cheatFuncActive;
    //TODO moveList

    Session(long lobbyID){
        this.lobbyID = lobbyID;
        this.lobbycode = generateLobbyKey();
        //TODO generate add Playercount
    }

    public String generateLobbyKey(){
        Random ran = new Random(System.currentTimeMillis());
        return Integer.toString(ran.nextInt(99999)+100000);
    }

    public void setPlayer1_name(String player1_name) {
        this.player1_name = player1_name;
    }

    public void setPlayer2_name(String player2_name) {
        this.player2_name = player2_name;
    }

    public String getLobbycode() {
        return lobbycode;
    }

    @Override
    public String toString() {
        return "Session{" +
                "lobbyID=" + lobbyID +
                ", lobbycode='" + lobbycode + '\'' +
                ", playercount=" + playercount +
                ", player1_name='" + player1_name + '\'' +
                ", player2_name='" + player2_name + '\'' +
                ", cheatFuncActive=" + cheatFuncActive +
                '}';
    }
}
