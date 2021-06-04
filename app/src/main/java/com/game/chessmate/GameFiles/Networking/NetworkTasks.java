package com.game.chessmate.GameFiles.Networking;

import android.util.Log;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionRequest;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionResponse;
import com.game.chessmate.GameFiles.Networking.NetObjects.joinSessionRequest;
import com.game.chessmate.GameFiles.Networking.NetObjects.leaveLobbyRequest;

import java.util.concurrent.Callable;

public class NetworkTasks {
    public static class CreateSession implements Callable<String> {
        private final String name;
        public CreateSession(String name) {
            this.name = name;
        }
        @Override
        public String call() {
            ChessMateClient.getInstance(); //creates and starts Client
            createSessionRequest req = new createSessionRequest();
            req.setName(this.name);
            Log.i("NETWORK","[C]>SessionRequest: " + req.getName());
            ChessMateClient.getInstance().getClient().sendTCP(req);

            final String[] lobbycode = new String[1];
            ChessMateClient.getInstance().getClient().addListener(new Listener() {
                public void received(Connection connection, Object object) {
                    if (object instanceof createSessionResponse) {
                        createSessionResponse req = (createSessionResponse) object;
                        Log.i("NETWORK", "[T:" + Thread.currentThread().getName() + "] " + "[C]>SessionResponse: " + req.getLobbyCode());
                        lobbycode[0] = req.getLobbyCode();
                    }
                }
            });
            while(lobbycode[0]==null){}
            return lobbycode[0];
        }
    }

    public static class joinSession implements Callable<String> {
        private final String lobbycode;
        public joinSession(String lobbycode) {
            this.lobbycode = lobbycode;
        }
        @Override
        public String call() {
            ChessMateClient.getInstance(); //creates and starts Client
            joinSessionRequest req = new joinSessionRequest();
            req.setLobbycode(this.lobbycode);
            Log.i("NETWORK","[C]>SessionRequest: " + req.getLobbycode());
            ChessMateClient.getInstance().getClient().sendTCP(req);

            final String[] lobbycode = new String[1];
            ChessMateClient.getInstance().getClient().addListener(new Listener() {
                public void received(Connection connection, Object object) {
                    if (object instanceof createSessionResponse) {
                        createSessionResponse req = (createSessionResponse) object;
                        Log.i("NETWORK", "[T:" + Thread.currentThread().getName() + "] " + "[C]>SessionResponse: " + req.getLobbyCode());
                        lobbycode[0] = req.getLobbyCode();
                    }
                }
            });
            while(lobbycode[0]==null){}
            return lobbycode[0];
        }
    }

    public static void leaveSession() {
        ChessMateClient.getInstance(); //creates and starts Client
        leaveLobbyRequest req = new leaveLobbyRequest();
        ChessMateClient.getInstance().getClient().sendTCP(req);
    }
}