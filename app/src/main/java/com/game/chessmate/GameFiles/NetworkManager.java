package com.game.chessmate.GameFiles;

import android.util.Log;

import com.game.chessmate.GameFiles.Networking.ChessMateClient;
import com.game.chessmate.GameFiles.Networking.ChessMateServer;

import java.io.IOException;

/** The NetworkManager class functions as the framework for networked interaction. */
public class NetworkManager {
    // Thread-Save Singleton
    private static final class InstanceHolder {
        static final NetworkManager INSTANCE = new NetworkManager();
    }
    public static NetworkManager getInstance(){ return NetworkManager.InstanceHolder.INSTANCE; }

    // Local Variables
    private ChessMateServer serverInstance;
    private ChessMateClient clientInstance;

    // Constructors
    NetworkManager() {
        // test.xml Server creation
        serverInstance = ChessMateServer.getInstance();
        try { serverInstance.start(); }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("NETWORK","FAILED TO START SERVER");
        }
        // Client creation
        clientInstance = ChessMateClient.getInstance();
        try { clientInstance.start(); }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("NETWORK","FAILED TO START CLIENT");
        }
    }

    // Class Methods
    public void createSession(String playerName){
        Log.i("NETWORK","CREATING SESSION...");
        //TODO create a Session [Network Code]
    }

    public void joinSession(String playerName){
        Log.i("NETWORK","JOINING SESSION...");
        //TODO join a Session [Network Code]
    }

    // Getter and Setter
}
