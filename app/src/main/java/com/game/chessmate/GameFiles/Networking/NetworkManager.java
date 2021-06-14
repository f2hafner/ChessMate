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
        gameDataObject.setCheatActivated(ChessBoard.getInstance().getwasMoveLegal());
        ChessMateClient.getInstance().getClient().sendTCP(gameDataObject);
    }

    public static void sendCard(int cardId, Field field1,Field field2){
        Log.i(TAG,"sendCard: " + "sendcard was called");

        FieldDataObject field1Object = new FieldDataObject();
        field1Object.setX(field1.getFieldX());
        field1Object.setY(field1.getFieldY());

        FieldDataObject field2Object = new FieldDataObject();

        if (field2!=null) {
            field2Object.setX(field2.getFieldX());
            field2Object.setY(field2.getFieldY());
        }

        CardDataObject cardDataObject= new CardDataObject();
        cardDataObject.setField1(field1Object);
        cardDataObject.setField2(field2Object);
        cardDataObject.setId(cardId);

        GameDataObject gameDataObject = new GameDataObject();
        gameDataObject.setLobbyCode(NetworkManager.currentLobbyCode);
        gameDataObject.setUsedCard(true);
        gameDataObject.setCardObject(cardDataObject);

        ChessMateClient.getInstance().getClient().sendTCP(gameDataObject);
    }

    public static void receiveCard(CardDataObject cardDataObject){
        Log.i(TAG,"receiveCard:" + "receivecard was called");

        int cardId=cardDataObject.getId();
        Field currentField1 = ChessBoard.getInstance().getBoardFields()[cardDataObject.getField1().getX()][cardDataObject.getField1().getY()];
        Field currentField2 = ChessBoard.getInstance().getBoardFields()[cardDataObject.getField2().getX()][cardDataObject.getField2().getY()];

        ChessBoard.getInstance().receiveCardAction(cardId,currentField1,currentField2);
    }

    public static Listener getGameCycleListener(){
        Listener gameCycleListener = new Listener(){
            public void received(Connection connection, Object object) {
                if(object instanceof GameDataObject){
                    GameDataObject gameDataObject = (GameDataObject)object;
                    Log.i("LOG","ORG: "+ gameDataObject.getOrigin().toString()+" TRG: "+gameDataObject.getTarget().toString());

                    if (gameDataObject.isMoved()) {
                        receiveMove(gameDataObject.getOrigin(), gameDataObject.getTarget());
                    }
                    else if (gameDataObject.isUsedCard()) {
                        receiveCard(gameDataObject.getCardObject());
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
