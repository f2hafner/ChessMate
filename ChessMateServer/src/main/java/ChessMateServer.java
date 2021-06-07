import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ChessMateServer extends Thread{
    int TCP_PORT = 53218;//54555//53216
    Server serverInstance;

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
                    Lobby lobby = new Lobby();
                    lobby._player1_join(request.getName());
                    LobbyManager.getSessions().add(lobby);
                    // Send
                    createSessionResponse response = new createSessionResponse();
                    response.setLobbyCode(lobby.getLobbycode());
                    System.out.println("Sending: " + response.getLobbyCode());
                    con.sendTCP(response);
                    LobbyManager.printAllCurrentSession();
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
