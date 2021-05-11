package com.game.chessmate.GameFiles.Networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionRequest;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionResponse;

import java.io.IOException;

/**
 * The ChessMateClient class implements a Client that allows players to connect to a Server.
 */
public class ChessMateClient {
    // Thread-Save Singleton
    private static final class InstanceHolder {
        static final ChessMateClient INSTANCE = new ChessMateClient();
    }
    public static ChessMateClient getInstance(){ return ChessMateClient.InstanceHolder.INSTANCE; }

    // Local Variables
    private int TCP_PORT    = 54555;
    private int UDP_PORT    = 54777;
    private String HOST_IP  = "192.168.0.4";
    private int TIMEOUT     = 5000; // milliseconds
    private Client clientInstance;

    // Constructors
    ChessMateClient(){
        clientInstance = new Client();
        registerClasses();
    }

    // Class Methods

    /**
     * registers the Request and Response Classes to kryonet
     */
    private void registerClasses(){
        Kryo kryo = clientInstance.getKryo();
        //TODO add Requests and Responses
        kryo.register(createSessionRequest.class);
        kryo.register(createSessionResponse.class);
    }

    /**
     * starts the Client
     */
    public void start() throws IOException {
        //TODO implement specific Start function
        clientInstance.start();
        clientInstance.connect(TIMEOUT, HOST_IP, TCP_PORT, UDP_PORT);

        /*
        SomeRequest request = new SomeRequest();
        request.text = "Request Text";
        clientInstance.sendTCP(request);
        */
        clientInstance.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                /*
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    System.out.println(response.text);
                }
                */
            }
        });
    }

    // Getter and Setter
    public int getTCP_PORT(){
        return TCP_PORT;
    }

    public void setTCP_PORT(int port){
        TCP_PORT = port;
    }

    public int getUDP_PORT(){
        return UDP_PORT;
    }

    public void setUDP_PORT(int port){
        UDP_PORT = port;
    }
}
