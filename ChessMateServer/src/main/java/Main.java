
import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server serverInstance;
        int TCP_PORT = 54555;
        int UDP_PORT = 54777;

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
                    createSessionRequest request = (createSessionRequest)o;
                    System.out.println("Request Arg: " + request.getName());

                    createSessionResponse response = new createSessionResponse();
                    response.setLobbyCode("XYZXYZ");
                    System.out.println("Sending: " + response.getLobbyCode());
                    con.sendTCP(response);
                }
            }
        });
    }
}
