import NetObjects.ObjectSender;

import java.util.Random;

public class Lobby {
    long lobbyID;
    String lobbycode;
    byte playercount;
    GameStates currentLobbyState;

    String player1_name;
    String player2_name;
    boolean cheatFuncActive;
    boolean clearLobby;
    //TODO moveList

    Lobby(){
        currentLobbyState = GameStates.INITIALIZING;
        this.lobbyID = LobbyManager.getNewFreeID();
        this.lobbycode = generateLobbyCode();
        ObjectSender.createLobbyResponse();
        playercount = 0;
        clearLobby=false;
    }

    public String generateLobbyCode(){
        Random ran = new Random(System.currentTimeMillis());
        return Integer.toString(ran.nextInt(99999)+100000);
    }

    public void setPlayer1_name(String player1_name) {
        this.player1_name = player1_name;
    }

    public void setPlayer2_name(String player2_name) {
        this.player2_name = player2_name;
    }
    // Player 1
    public void _player1_join(String name){
        if(player1_name==null){
            this.player1_name = name;
            playercount++;
        }
    }

    public void _player1_leave(){
        if(player1_name!=null){
            this.player1_name = null;
            playercount--;
            removeLobbyIfEmpty();
        }
    }
    // Player 2
    public void _player2_join(String name){
        if(player2_name==null){
            this.player2_name = name;
            playercount++;
        }
    }

    public void _player2_leave(){
        if(player1_name!=null){
            this.player1_name = null;
            playercount--;
            removeLobbyIfEmpty();
        }
    }

    private void removeLobbyIfEmpty(){
        if(playercount==0){
            this.clearLobby = true;
        }
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
