package com.game.chessmate.GameFiles.Networking;

import android.util.Log;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionRequest;
import com.game.chessmate.GameFiles.Networking.NetObjects.createSessionResponse;

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
}

/*    public class NetworkTask implements Callable<Object> {
        private Object object;
        public NetworkTask(Object o) {
            this.object = o;
        }
        @Override
        public Object call() {
            return null;
        }
    }*/



        /*
        Log.i("NETWORK","[T:"+Thread.currentThread().getName()+"] "+"creating Session");
        createSessionRequest r = new createSessionRequest();
        r.setName(name);
        ChessMateClient.getInstance().createSession(r);
        //Thread.sleep(100);
        String lobbycode = (String) ChessMateClient.getInstance().receive();
        Log.i("NETWORK","[T:"+Thread.currentThread().getName()+"] "+"LC: "+ lobbycode);
        return lobbycode;
            /*do {
                lobbycode = ChessMateClient.getInstance().getLobbyCode();

            }while(lobbycode == null);*/
