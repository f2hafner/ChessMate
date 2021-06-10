package com.game.chessmate.GameFiles.Networking;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.ChessBoard;
import com.game.chessmate.GameFiles.Field;
import com.game.chessmate.GameFiles.GameState;
import com.game.chessmate.GameFiles.Networking.NetObjects.FieldDataObject;
import com.game.chessmate.GameFiles.Networking.NetObjects.GameDataObject;
import com.game.chessmate.GameFiles.Networking.NetObjects.LobbyDataObject;
import com.game.chessmate.GameFiles.Networking.NetObjects.startGameParameters;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;
import com.game.chessmate.Lobby;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** The NetworkManager class functions as the framework for networked interaction. */
public class NetworkManager {
    private static final class InstanceHolder {static final NetworkManager INSTANCE = new NetworkManager();}
    public static NetworkManager getInstance(){ return NetworkManager.InstanceHolder.INSTANCE; }
    public static ChessPieceColour initialColor;
    public static String currentLobbyCode;
    static ExecutorService service = Executors.newFixedThreadPool(10);

    public static String createSession(String name) {
        Future<String> future = service.submit(new NetworkTasks.CreateSession(name));
        try{
            String lobbycode = future.get();
            Log.i("NETWORK","LobbyCode: "+lobbycode);
            NetworkManager.currentLobbyCode = lobbycode;
            return lobbycode;
        } catch (InterruptedException | ExecutionException e){
            Log.e("NETWORK","Couldnt get Value from Future");
            //Thread.currentThread().interrupt();
        }
        return null;
    }

    public static LobbyDataObject joinSession(String lobbycode, String name) {
        Future<LobbyDataObject> future = service.submit(new NetworkTasks.JoinSession(lobbycode, name));
        try{
            LobbyDataObject lobbyDataObject = future.get();
            Log.i("NETWORK","LobbyCode: "+lobbyDataObject);
            NetworkManager.currentLobbyCode = lobbycode;
            return lobbyDataObject;
        } catch (InterruptedException | ExecutionException e){
            Log.e("NETWORK","Couldnt get Value from Future");
            //Thread.currentThread().interrupt();
        }
        return null;
    }

    public static void startGame(String lobbycode) {
        Future<startGameParameters> future = service.submit(new NetworkTasks.startGame(lobbycode));
        try{
            startGameParameters parameters = future.get();
            Log.i("COLOR","COLOR: "+parameters.getInitColour());
            NetworkManager.currentLobbyCode = lobbycode;
            NetworkManager.initialColor = parameters.getInitColour();
            ChessMateClient.getInstance().getClient().addListener(getGameCycleListener());
        } catch (InterruptedException | ExecutionException e){
            Log.e("NETWORK","Couldnt get Value from Future");
            //Thread.currentThread().interrupt();
        }
    }

    public static void sendMove(Field currentField, Field targetField){
        FieldDataObject currentFieldObject = new FieldDataObject();
        currentFieldObject.setX(currentField.getFieldX());
        currentFieldObject.setY(currentField.getFieldY());
        FieldDataObject targetFieldObject = new FieldDataObject();
        targetFieldObject.setX(targetField.getFieldX());
        targetFieldObject.setY(targetField.getFieldY());
        GameDataObject gameDataObject = new GameDataObject();
        gameDataObject.setLobbyCode(NetworkManager.currentLobbyCode);
        ChessBoard.getInstance().setGameState(GameState.WAITING);
        gameDataObject.setMoved(true);
        gameDataObject.setOrigin(currentFieldObject);
        gameDataObject.setTarget(targetFieldObject);
        ChessMateClient.getInstance().getClient().sendTCP(gameDataObject);
    }

    private static Listener getGameCycleListener(){
        Listener gameCycleListener = new Listener(){
            public void received(Connection connection, Object object) {
                if(object instanceof GameDataObject){
                    GameDataObject gameDataObject = (GameDataObject)object;
                    Log.i("LOG","ORG: "+ gameDataObject.getOrigin().toString()+" TRG: "+gameDataObject.getTarget().toString());
                    receiveMove(gameDataObject.getOrigin(), gameDataObject.getTarget());
                }
            }
        };
        return gameCycleListener;
    }

    public static void receiveMove(FieldDataObject origin, FieldDataObject target){
        Field originField = ChessBoard.getInstance().getBoardFields()[origin.getX()][origin.getY()];
        Field targetField = ChessBoard.getInstance().getBoardFields()[target.getX()][target.getY()];
        originField.getCurrentPiece().move(targetField);
        NetworkManager.sendMove(originField,targetField);
        ChessBoard.getInstance().setGameState(GameState.ACTIVE);
    }

    public static ChessPieceColour getInitialColor() {
        return initialColor;
    }

    public static void setInitialColor(ChessPieceColour initialColor) {
        NetworkManager.initialColor = initialColor;
    }

    /*public static void leaveSession() {
        NetworkTasks.leaveSession();
    }
    public static void startGame() {
    }*/
}
