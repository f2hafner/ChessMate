import java.util.ArrayList;

public class LobbyManager {
    static ArrayList<Lobby> sessions = new ArrayList();
    static int MAX_SESSIONS = 10;

    public static int getNewFreeID(){
        for (int id = 0; id <= MAX_SESSIONS; id++)
            if(!IDexists(id)) return id;
        throw new IllegalStateException("MAX_SESSIONS LIMIT REACHED");
    }

    private static boolean IDexists(int id){
        for (Lobby s : sessions)
            if(s.lobbyID==id) return true;
        return false;
    }

    public static void printAllCurrentSession(){

        if(sessions.isEmpty()){
            System.out.println("No lobbies currently online");
        } else {
            System.out.println("Online lobbies: "+sessions.size()+"/"+MAX_SESSIONS);
            for (Lobby s : sessions)
                System.out.println(s.toString());
        }
    }

    public static ArrayList<Lobby> getSessions() {
        return sessions;
    }
}
