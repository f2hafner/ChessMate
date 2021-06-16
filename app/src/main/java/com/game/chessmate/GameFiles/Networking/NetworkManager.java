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
import com.game.chessmate.GameFiles.Networking.NetObjects.CardDataObject;
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

import static android.content.ContentValues.TAG;

/** The NetworkManager class functions as the framework for networked interaction. */
public class NetworkManager {
    private static final class InstanceHolder {static final NetworkManager INSTANCE = new NetworkManager();}
    public static NetworkManager getInstance(){ return NetworkManager.InstanceHolder.INSTANCE; }
    public static ChessPieceColour initialColor;
    public static String currentLobbyCode;
    static ExecutorService service = Executors.newFixedThreadPool(1);

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
        Log.i(TAG, "sendMove: " + "sendmove was called");

        FieldDataObject currentFieldObject = new FieldDataObject();
        currentFieldObject.setX(currentField.getFieldX());
        currentFieldObject.setY(currentField.getFieldY());

        FieldDataObject targetFieldObject = new FieldDataObject();
        targetFieldObject.setX(targetField.getFieldX());
        targetFieldObject.setY(targetField.getFieldY());

        GameDataObject gameDataObject = new GameDataObject();
        gameDataObject.setLobbyCode(NetworkManager.currentLobbyCode);
        gameDataObject.setMoved(true);
        gameDataObject.setOrigin(currentFieldObject);
        gameDataObject.setTarget(targetFieldObject);
        gameDataObject.setCheatActivated(!ChessBoard.getInstance().getwasMoveLegal());

        ChessMateClient.getInstance().getClient().sendTCP(gameDataObject);
    }

    public static void sendCard(int cardId, Field field1,Field field2){
        Log.i(TAG,"sendCard: " + "sendcard was called");

        FieldDataObject fieldObject1 = new FieldDataObject();
        fieldObject1.setX(field1.getFieldX());
        fieldObject1.setY(field1.getFieldY());

        FieldDataObject fieldObject2 = new FieldDataObject();
        fieldObject2.setX(field2.getFieldX());
        fieldObject2.setY(field2.getFieldY());

        GameDataObject gameDataObject = new GameDataObject();
        gameDataObject.setLobbyCode(NetworkManager.currentLobbyCode);
        gameDataObject.setUsedCard(true);
        gameDataObject.setCardId(cardId);
        gameDataObject.setOrigin(fieldObject1);
        gameDataObject.setTarget(fieldObject2);

        ChessMateClient.getInstance().getClient().sendTCP(gameDataObject);
    }

    public static void receiveCard(int cardId, FieldDataObject fieldObject1, FieldDataObject fieldObject2){
        Log.i(TAG, "receiveCard: receivecard was called");

        Field field1 = ChessBoard.getInstance().getBoardFields()[fieldObject1.getX()][fieldObject1.getY()];
        Field field2 = ChessBoard.getInstance().getBoardFields()[fieldObject2.getX()][fieldObject2.getY()];

        ChessBoard.getInstance().receiveCardAction(cardId, field1, field2);
    }

    public static Listener getGameCycleListener(){
        Listener gameCycleListener = new Listener(){
            public void received(Connection connection, Object object) {
                if(object instanceof GameDataObject){
                    GameDataObject gameDataObject = (GameDataObject)object;
                    Log.i("LOG","ORG: "+ gameDataObject.getOrigin().toString()+" TRG: "+gameDataObject.getTarget().toString());

                    if(gameDataObject.isWin()){
                        Log.d(TAG, "received: " + gameDataObject +" "+ gameDataObject.isWin());
                        receiveWin();
                    }
                    else if (gameDataObject.isMoved()) {
                        receiveMove(gameDataObject.getOrigin(), gameDataObject.getTarget());
                    }
                    else if (gameDataObject.isUsedCard()) {
                        receiveCard(gameDataObject.getCardId(),gameDataObject.getOrigin(),gameDataObject.getTarget());
                    }
                }
            }
        };
        return gameCycleListener;
    }

    public static void receiveMove(FieldDataObject origin, FieldDataObject target){
        Log.i(TAG, "receiveMove: receivemove was called");

        Field originField = ChessBoard.getInstance().getBoardFields()[origin.getX()][origin.getY()];
        Field targetField = ChessBoard.getInstance().getBoardFields()[target.getX()][target.getY()];
        originField.getCurrentPiece().move(targetField);
    }

    public static void receiveWin(){
        Log.d(TAG, "receiveWin: " + "was called");
        ChessBoard.getInstance().setGameState(GameState.WIN);
        ChessBoard.getInstance().redirectToEndScreen();
    }

    public static ChessPieceColour getInitialColor() {
        return initialColor;
    }

    public static void setInitialColor(ChessPieceColour initialColor) {
        NetworkManager.initialColor = initialColor;
    }
}
