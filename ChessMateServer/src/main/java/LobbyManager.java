import java.util.ArrayList;

public class LobbyManager {
    static ArrayList<Lobby> sessions = new ArrayList();
    static int sessionAmountLimit = 10;

    public static ArrayList<Lobby> getSessions() {
        return sessions;
    }

    public static void printAllSession(){
        for (Lobby s : sessions) {
            System.out.println(s.toString());
        }
    }

    public static int getNewID(){
        for (int i = 0; i <= sessionAmountLimit; i++) {
            for (Lobby s : sessions) {
                if(s.lobbyID!=i) return i;
            }
        }
        return -1;
    }
}
