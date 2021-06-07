import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ChessMateServer extends Thread{
    int TCP_PORT = 53216;//54555
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
            serverInstance.bind(TCP_PORT);
            new Thread(serverInstance).start();
        } catch (IOException e) {
            System.err.println("[S]>Error starting server!");
        }

        serverInstance.addListener(new Listener() {
            @Override
            public void received (Connection con, Object o) {
                if (o instanceof createSessionRequest) {
                    System.out.println("[CREATE_SESSION_REQUEST]");
                    // Receive
                    createSessionRequest request = (createSessionRequest)o;
                    System.out.println("Request Arg: " + request.getName());
                    // Process
                    Lobby lobby = new Lobby(LobbyManager.getNewID());
                    lobby.setPlayer1_name(request.getName());
                    LobbyManager.getSessions().add(lobby);
                    // Send
                    createSessionResponse response = new createSessionResponse();
                    response.setLobbyCode(lobby.getLobbycode());
                    System.out.println("Sending: " + response.getLobbyCode());
                    con.sendTCP(response);
                    LobbyManager.printAllSession();
                }
                System.out.println(con.toString() +"\t"+ o.toString() +"\n");
            }

            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                System.out.println("Connected: " +connection.toString());
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                System.out.println("Disconnected: " +connection.toString());
            }
        });
    }

}
