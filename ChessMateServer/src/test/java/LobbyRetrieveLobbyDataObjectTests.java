import NetObjects.ChessPieceColour;
import NetObjects.GameDataObject;
import NetObjects.LobbyDataObject;
import NetObjects.PlayerDataObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class LobbyRetrieveLobbyDataObjectTests {
    Lobby lobby;
    LobbyDataObject lobbyDataObject;
    public void initLobby(){
        lobby = new Lobby();
        LobbyManager.sessions.add(lobby);
        PlayerDataObject player1 = new PlayerDataObject();
        player1.setName("testName");
        player1.setChessPieceColour(ChessPieceColour.WHITE);
        player1.setMaxWrongCheatReveal(3);
        PlayerDataObject player2 = new PlayerDataObject();
        player2.setName("testName");
        player2.setChessPieceColour(ChessPieceColour.BLACK);
        player2.setMaxWrongCheatReveal(3);
        lobbyDataObject = new LobbyDataObject();
        lobbyDataObject.setLobbyID(lobby.lobbyID);
        lobbyDataObject.setLobbycode(lobby.lobbycode);
        lobbyDataObject.setClearLobby(lobby.clearLobby);
        lobbyDataObject.setCurrentLobbyState(lobby.currentLobbyState);
        lobbyDataObject.setCheatFuncActive(lobby.cheatFuncActive);
    }

    @DisplayName("initialRetrieveTest")
    @Test
    public void initialRetrieveTest(){
        initLobby();
        LobbyDataObject retrievedObject = lobby.retrieveLobbyDataObject();
        assertSame(lobbyDataObject.getClass(), retrievedObject.getClass());
    }

    @DisplayName("initialOnePlayerRetrieveTest")
    @Test
    public void initialOnePlayerRetrieveTest(){
        initLobby();
        lobby.player1Join(null,"testName");
        lobbyDataObject.setPlayer1(player1);
        //lobbyDataObject.setPlayer1(player2);
        LobbyDataObject retrievedObject = lobby.retrieveLobbyDataObject();
        assertEquals(lobbyDataObject.getPlayer1(), retrievedObject.getPlayer1());
        assertEquals(lobbyDataObject.getPlayer2(), retrievedObject.getPlayer2());
        assertEquals(lobbyDataObject., retrievedObject.getPlayer2());
    }


}
