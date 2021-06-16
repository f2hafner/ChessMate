import NetObjects.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ChessMateServer extends Thread{
    int TCP_PORT = 53216;
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
            Log.accept("SERVER","Server started!");
        } catch (IOException e) {
            Log.e("SERVER","Error starting server!");
        }

        serverInstance.addListener(new Listener() {
            @Override
            public void received (Connection con, Object o) {
                if (o instanceof createSessionRequest) {
                    Log.i("CREATE_SESSION_REQUEST","Received CREATE REQUEST");
                    // Receive
                    createSessionRequest request = (createSessionRequest)o;
                    Log.i("CREATE_SESSION_REQUEST","Lobbycode: " + request.getName());
                    // Process
                    Lobby lobby = new Lobby();
                    lobby.player1Join(con, request.getName());
                    LobbyManager.getSessions().add(lobby);
                    // Send
                    ObjectSender.createLobbyResponse(con, lobby);
                }

                if (o instanceof joinSessionRequest) {
                    Log.i("JOIN_SESSION_REQUEST","Received JOIN REQUEST");
                    // Receive
                    joinSessionRequest request = (joinSessionRequest)o;
                    System.out.println("Name: " + request.getName());
                    System.out.println("LobbyCode: " + request.getLobbycode());
                    // Process
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbycode());
                    if(lobby!=null){
                        lobby.player2Join(con,request.getName());
                        // Send
                        ObjectSender.sendLobbyDataObject(con,lobby);
                        ObjectSender.sendLobbyDataObject(lobby.player1.connection,lobby);
                    } else {
                        //TODO lobby doesn't exist
                    }
                }

                if (o instanceof startGameRequest) {
                    Log.i("START_GAME_REQUEST","Received START GAME REQUEST");
                    // Receive
                    startGameRequest request = (startGameRequest)o;
                    // Process
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbycode());
                    if(lobby!=null){
                        // Send
                        lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_INPUT;
                        ObjectSender.sendStartGameParameters(con,lobby.player1);
                        ObjectSender.sendStartGameParameters(lobby.player2.connection,lobby.player2);
                    } else {
                        //TODO lobby doesn't exist
                    }
                }

                if(o instanceof SensorActivationObject){
                    SensorActivationObject request = (SensorActivationObject)o;
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbyCode());
                    if(lobby!=null){
                        if(lobby.cheatFuncActive){
                            Log.i("SENSOR_PACKET","Received SENSOR PACKET");
                            if(lobby.player1.connection == con) {
                                if (lobby.lastMoveOrigin != null) {
                                  FieldDataObject originField = lobby.lastMoveOrigin;
                                  FieldDataObject targetField = lobby.lastMoveTarget;
                                  GameDataObject moveBackToOrigin = new GameDataObject();
                                  moveBackToOrigin.setOrigin(originField);
                                  moveBackToOrigin.setTarget(targetField);
                                  Log.i("SENSOR_PACKET","Player1 revealed the cheat");
                                  //TODO tell player 2 that his cheat was reveald
                                  ObjectSender.sendGameDataObject(lobby.player2.connection,lobby, moveBackToOrigin);
                                  lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_INPUT;
                                }
                            }

                            if(lobby.player2.connection==con) {
                                if (lobby.lastMoveOrigin != null) {
                                    FieldDataObject originField = lobby.lastMoveOrigin;
                                    FieldDataObject targetField = lobby.lastMoveTarget;
                                    GameDataObject moveBackToOrigin = new GameDataObject();
                                    moveBackToOrigin.setOrigin(originField);
                                    moveBackToOrigin.setTarget(targetField);
                                    Log.i("SENSOR_PACKET", "Player2 revealed the cheat");
                                    //TODO tell player 1 that his cheat was reveald
                                    ObjectSender.sendGameDataObject(lobby.player1.connection, lobby, moveBackToOrigin);
                                    lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER2_INPUT;
                                }
                            }
                            Log.v("SENSOR_PACKET","Sensor got activated and other player cheated");
                            lobby.cheatFuncActive = false;
                        } else {
                            Log.v("SENSOR_PACKET","Sensor got activated and other player did not cheat");
                            if(lobby.player1.connection == con) {
                                if (lobby.player1.maxWrongCheatReveal > 0) {
                                    lobby.player1.maxWrongCheatReveal--;
                                    lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER2_INPUT;
                                   // ObjectSender.sendGameDataObject(lobby.player2.connection, lobby, request);
                                } else {
                                    Log.i("SENSOR_PACKET","Player1 ran out of reveals!");
                                    //TODO anzeigen wieviele Aufdeckversuche noch vorhanden sind
                                    // lobby.currentLobbyState = GameStates.GAMEOVER;
                                }
                            }

                            if(lobby.player2.connection == con){
                                if(lobby.player2.maxWrongCheatReveal > 0){
                                    lobby.player2.maxWrongCheatReveal--;
                                    lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_INPUT;
                                } else {
                                    Log.i("SENSOR_PACKET", "Player2 ran out of reveals!");
                                    //TODO anzeigen wieviele Aufdeckversuche noch vorhanden sind
                                    // lobby.currentLobbyState = GameStates.GAMEOVER;
                                }
                            }
                        }
                    }
                }

                if (o instanceof GameDataObject) {
                    Log.i("GAME_DATA_OBJECT","Received Game Data");
                    GameDataObject request = (GameDataObject)o;
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbyCode());
                    if(lobby!=null){
                        if(lobby.cheatFuncActive){
                            //WHEN PLAYER PROCEEDED WITH MOVE INSTEAD OF SENSORACTIVATION
                            lobby.cheatFuncActive = false;
                        }
                        lobby.lastMoveOrigin = request.getOrigin();
                        lobby.lastMoveTarget = request.getTarget();
                        lobby.cheatFuncActive = request.isCheatActivated();

                        if(lobby.currentLobbyState == GameStates.WAITING_FOR_PLAYER1_INPUT){
                            System.out.println("Current Lobbystate was WaitForPlayer1move");
                            if(con==lobby.player1.connection){
                                System.out.println("Connection was the same with player 1");
                                System.out.println(lobby.player1);
                                lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER2_INPUT;
                                ObjectSender.sendGameDataObject(lobby.player2.connection, lobby, request);
                            }
                        } else {
                            System.out.println("Current Lobbystate was WaitForPlayer2move");
                            if(con==lobby.player2.connection){
                                System.out.println("Connection was the same with player 2");
                                System.out.println(lobby.player2);
                                lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_INPUT;
                                ObjectSender.sendGameDataObject(lobby.player1.connection, lobby, request);
                            }
                        }
                    } else {
                        //TODO lobby doesn't exist
                    }
                }
                //Log.v("SERVER",con.toString() +"\t"+ o.toString() +"\n");
            }

            @Override
            public void connected(Connection connection) {
                Log.i("SERVER",connection.toString()+" joined");
            }

            @Override
            public void disconnected(Connection connection) {
                Log.i("SERVER",connection.toString()+" left");
                System.out.println("Disconnected: " +connection.toString());
            }
        });
    }
}
