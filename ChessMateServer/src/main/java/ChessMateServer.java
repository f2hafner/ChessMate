import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ChessMateServer extends Thread{
    int TCP_PORT = 54555;
    int UDP_PORT = 54777;
    int MAX_SESSIONS = 10;
    Server serverInstance;

    ChessMateServer(int TCP_PORT, int UDP_PORT, int MAX_SESSIONS){
        this.TCP_PORT = TCP_PORT;
        this.UDP_PORT = UDP_PORT;
        this.MAX_SESSIONS = MAX_SESSIONS;
    }
    ChessMateServer(int MAX_SESSIONS){
        this.MAX_SESSIONS = MAX_SESSIONS;
    }
    ChessMateServer(){}

    @Override
    public void run() {
        start();
    }

    public void start(){
        serverInstance = new Server();
        NetObjectRegistrator.register(serverInstance.getKryo());

        try {
            serverInstance.bind(TCP_PORT, UDP_PORT);
            serverInstance.start();
        } catch (IOException e) {
            System.err.println("[S]>Error starting server!");
        }

        serverInstance.addListener(new Listener() {
            public void received (Connection con, Object o) {
                if (o instanceof createSessionRequest) {
                    System.out.println("[CREATE_SESSION_REQUEST]");
                    // Receive
                    createSessionRequest request = (createSessionRequest)o;
                    System.out.println("Request Arg: " + request.getName());
                    // Process
                    Session session = new Session(LobbyManager.checkForFreeID());
                    session.setPlayer1_name(request.getName());
                    LobbyManager.getSessions().add(session);
                    // Send
                    createSessionResponse response = new createSessionResponse();
                    response.setLobbyCode(session.getLobbycode());
                    System.out.println("Sending: " + response.getLobbyCode());
                    con.sendTCP(response);
                    LobbyManager.printAllSession();
                }
            }
        });
    }

}
