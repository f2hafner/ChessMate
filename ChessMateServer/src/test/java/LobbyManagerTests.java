public class LobbyManagerTests {
    Lobby lobby;
    public void initLobby(){
        lobby = new Lobby();
        LobbyManager.sessions.add(lobby);
    }

}
