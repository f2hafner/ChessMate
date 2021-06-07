import java.util.Random;

public class Lobby {
    long lobbyID;
    String lobbycode;
    byte playercount;
    GameStates currentLobbyState;

    String player1_name;
    String player2_name;
    boolean cheatFuncActive;
    //TODO moveList

    Lobby(long lobbyID){
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
        return "["+lobbyID+"]<"+playercount+"> code="+lobbycode+"\n"
                +"\t"+"state="+currentLobbyState+"\n"
                +"\t"+"player1="+player1_name+"\n"
                +"\t"+"player2="+player2_name+"\n"
                +"\t"+"cheatFuncActive="+cheatFuncActive+"\n";
    }
}
