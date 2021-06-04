import java.util.ArrayList;

public class LobbyManager {
    static ArrayList<Session> sessions = new ArrayList();
    static int sessionAmountLimit = 10;

    public static int checkForFreeID() {
        for (int i = 0; i <= sessionAmountLimit; i++) {
            for (Session s : sessions) {
                if(s.lobbyID!=i) return i;
            }
        }
        return -1;
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

    public static void printAllSession(){
        for (Session s : sessions) {
            System.out.println(s.toString());
        }
    }
}
