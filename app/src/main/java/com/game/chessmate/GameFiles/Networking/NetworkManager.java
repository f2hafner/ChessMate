package com.game.chessmate.GameFiles.Networking;

import android.content.Intent;
import android.util.Log;

import com.game.chessmate.Lobby;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** The NetworkManager class functions as the framework for networked interaction. */
public class NetworkManager {
    private static final class InstanceHolder {static final NetworkManager INSTANCE = new NetworkManager();}
    public static NetworkManager getInstance(){ return NetworkManager.InstanceHolder.INSTANCE; }

    static ExecutorService service = Executors.newFixedThreadPool(10);

    public static String createSession(String name) {
        Future<String> future = service.submit(new NetworkTasks.CreateSession(name));
        try{
            String lobbycode = future.get();
            Log.i("NETWORK","LobbyCode: "+lobbycode);

            return lobbycode;
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void leaveSession() {
        NetworkTasks.leaveSession();
    }

    public static String joinSession(String lobbycode) {
        Future<String> future = service.submit(new NetworkTasks.joinSession(lobbycode));
        try{
            String name = future.get();
            Log.i("NETWORK","LobbyCode: "+lobbycode);

            return lobbycode;
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void startGame() {
    }
}
