import NetObjects.*;
import com.esotericsoftware.kryonet.Connection;

import java.util.Random;

public class Lobby {
    // GENERIC INFO
    boolean clearLobby;
    int lobbyID;
    String lobbycode;
    byte playercount;
    GameStates currentLobbyState;

    // PLAYER INFO
    PlayerObject player1;
    PlayerObject player2;

    // CHEATFUNCTION
    boolean cheatFuncActive;


    // HISTORY INFO
    FieldDataObject lastMoveOrigin;
    FieldDataObject lastMoveTarget;

    Lobby(){
        currentLobbyState = GameStates.INITIALIZING;
        clearLobby = false;
        player1 = null;
        player2 = null;
        playercount = 0;
        this.lobbyID = LobbyManager.getNewFreeID();
        this.lobbycode = generateLobbyCode();
        currentLobbyState = GameStates.WAITING_FOR_PLAYER;
    }

    public String generateLobbyCode(){
        Random ran = new Random(System.currentTimeMillis());
        return Integer.toString(ran.nextInt(99999)+100000);
    }

    // Player 1
    public void player1Join(Connection con, String name){
        if(this.player1==null){
            player1 = new PlayerObject(con, name, ChessPieceColour.WHITE);
            playercount++;
            updateLobby();
        }
    }

    public void player1Leave(){
        if(player1!=null){
            player1 = null;
            playercount--;
            updateLobby();
        }
    }
    // Player 2
    public void player2Join(Connection con, String name){
        if(this.player2==null){
            player2 = new PlayerObject(con, name, ChessPieceColour.BLACK);
            playercount++;
            updateLobby();
        }
    }

    public void player2Leave(){
        if(player2!=null){
            player2 = null;
            playercount--;
            updateLobby();
        }
    }

    public void player1turn(){

    }

    public void player2turn(){

    }

    public LobbyDataObject retrieveLobbyDataObject(){
        LobbyDataObject lobbyDataObject = new LobbyDataObject();
        lobbyDataObject.setLobbyID(this.lobbyID);
        lobbyDataObject.setLobbycode(this.lobbycode);

        PlayerDataObject p1 = new PlayerDataObject();
        p1.setName(this.player1.getName());
        lobbyDataObject.setPlayer1(p1);

        PlayerDataObject p2 = new PlayerDataObject();
        p2.setName(this.player2.getName());
        lobbyDataObject.setPlayer2(p2);

        lobbyDataObject.setPlayercount(this.playercount);
        lobbyDataObject.setCurrentLobbyState(this.currentLobbyState);
        return lobbyDataObject;
    }

    public String getLobbycode() {
        return lobbycode;
    }

    public void printInfo() {
        Log.i("LOBBY","LobbyID="+"\t"+lobbyID);
        Log.i("LOBBY","LobbyCODE="+"\t"+lobbycode);
        Log.i("LOBBY","PlayersCOUNT="+"\t"+playercount);
        Log.i("LOBBY","Player1="+"\t"+player1);
        Log.i("LOBBY","Player2="+"\t"+player2);
        Log.i("LOBBY","Player2="+"\t"+cheatFuncActive);
    }

    public void updateLobby(){
        if(playercount==0){ this.clearLobby = true; } // removeLobbyIfEmpty
        if(playercount==2){ currentLobbyState = GameStates.READY; } // lobby can be started
    }

    public static FieldDataObject mirrorFunc(FieldDataObject field){
        FieldDataObject mirroredField = new FieldDataObject();

        int[][] board = new int[8][8];
        int[][] invertedBoard = new int[8][8];
        board[field.getX()][field.getY()] = 1;
        int k = -1;
        for (int i = board.length-1; i >= 0; i--) {
            int h = -1;
            k++;
            for (int j = board[0].length-1; j >= 0; j--) {
                h++;
                invertedBoard[k][h] = board[i][j];
            }
        }

        int resultX = 0;
        int resultY = 0;
        for (int i = 0; i < invertedBoard.length; i++) {
            for (int j = 0; j < invertedBoard[0].length; j++) {
                if (invertedBoard[i][j] == 1) {
                    resultX = i;
                    resultY = j;
                }
            }
        }

        mirroredField.setX(resultX);
        mirroredField.setY(resultY);
        return mirroredField;
    }
}
