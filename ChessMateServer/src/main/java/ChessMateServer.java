import NetObjects.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ChessMateServer extends Thread{
    int TCP_PORT = 53216;//54555
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
        } catch (IOException e) {
            System.err.println("[S]>Error starting server!");
        }

        serverInstance.addListener(new Listener() {
            @Override
            public void received (Connection con, Object o) {
                if (o instanceof createSessionRequest) {
                    System.out.println("[CREATE_SESSION_REQUEST]");
                    // Receive
                    createSessionRequest request = (createSessionRequest)o;
                    System.out.println("Request Arg: " + request.getName());
                    // Process
                    Lobby lobby = new Lobby();
                    lobby._player1_join(con, request.getName());
                    LobbyManager.getSessions().add(lobby);
                    // Send
                    ObjectSender.createLobbyResponse(con, lobby);
                }

                if (o instanceof joinSessionRequest) {
                    System.out.println("[JOIN_SESSION_REQUEST]");
                    // Receive
                    joinSessionRequest request = (joinSessionRequest)o;
                    System.out.println("Name: " + request.getName());
                    System.out.println("LobbyCode: " + request.getLobbycode());
                    // Process
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbycode());
                    if(lobby!=null){
                        lobby._player2_join(con,request.getName());
                        // Send
                        ObjectSender.sendLobbyDataObject(con,lobby);
                        ObjectSender.sendLobbyDataObject(lobby.player1.connection,lobby);
                    } else {
                        //TODO lobby doesn't exist
                    }
                }

                if (o instanceof startGameRequest) {
                    System.out.println("[START_GAME_REQUEST]");
                    // Receive
                    startGameRequest request = (startGameRequest)o;
                    // Process
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbycode());
                    if(lobby!=null){
                        // Send
                        lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_MOVE;
                        ObjectSender.sendStartGameParameters(con,lobby.player1);
                        ObjectSender.sendStartGameParameters(lobby.player2.connection,lobby.player2);
                    } else {
                        //TODO lobby doesn't exist
                    }
                }

                if(o instanceof SensorActivationObject){
                    System.out.println("[SENSOR_PACKET]");
                    SensorActivationObject request = (SensorActivationObject)o;
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbyCode());
                    if(lobby!=null){
                        if(lobby.cheatFuncActive){
                            if(lobby.player1.connection == con) {
                                if (lobby.origin != null) {
                                  FieldDataObject originField = lobby.origin;
                                  FieldDataObject targetField = lobby.target;
                                  GameDataObject moveBackToOrigin = new GameDataObject();
                                  moveBackToOrigin.setOrigin(originField);
                                  moveBackToOrigin.setTarget(targetField);
                                  System.out.println("the player reveald your cheat");
                                  //TODO tell player 2 that his cheat was reveald
                                  ObjectSender.sendGameDataObject(lobby.player2.connection,lobby, moveBackToOrigin);
                                  lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_MOVE;

                                }
                            }

                            if(lobby.player2.connection==con){
                                if (lobby.origin != null) {
                                    FieldDataObject originField = lobby.origin;
                                    FieldDataObject targetField = lobby.target;
                                    GameDataObject moveBackToOrigin = new GameDataObject();
                                    moveBackToOrigin.setOrigin(originField);
                                    moveBackToOrigin.setTarget(targetField);
                                    System.out.println("the player reveald your cheat");
                                    //TODO tell player 1 that his cheat was reveald
                                    ObjectSender.sendGameDataObject(lobby.player1.connection,lobby, moveBackToOrigin);
                                    lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_MOVE;

                                }


                            //ON SENSOR ACTIVATED BUT PLAYER DID NOT CHEAT
                            System.out.println("Sensor got activated and other player cheated");
                            lobby.cheatFuncActive = false;
                        } else {
                            System.out.println("Sensor got activated and other player did not cheat");
                            if(lobby.player1.connection == con) {
                                if (lobby.player1.maxWrongCheatReveal > 0) {
                                    lobby.player1.maxWrongCheatReveal--;
                                    lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER2_MOVE;

                                   // ObjectSender.sendGameDataObject(lobby.player2.connection, lobby, request);

                                } else {
                                    System.out.println("You wrongly accused the other player of cheating more than 3 times");
                                    //TODO anzeigen wieviele Aufdeckversuche noch vorhanden sind
                                    // lobby.currentLobbyState = GameStates.GAMEOVER;
                                }
                                }
                            }
                                if(lobby.player2.connection == con){
                                    if(lobby.player2.maxWrongCheatReveal > 0){
                                        lobby.player2.maxWrongCheatReveal--;
                                        lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_MOVE;

                                    }
                                } else {
                                    System.out.println("You wrongly accused the other player of cheating more than 3 times");
                                    //TODO anzeigen wieviele Aufdeckversuche noch vorhanden sind
                                    // lobby.currentLobbyState = GameStates.GAMEOVER;
                                }


                        }
                    }
                }

                if (o instanceof GameDataObject) {
                    System.out.println("[GameDataObject]");
                    // Receive
                    GameDataObject request = (GameDataObject)o;
                    // Process
                    Lobby lobby = LobbyManager.getSessionByLobbycode(request.getLobbyCode());
                    if(lobby!=null){
                        // Send
                        if(lobby.cheatFuncActive){

                            //WHEN PLAYER PROCEEDED WITH MOVE INSTEAD OF SENSORACTIVATION
                            lobby.cheatFuncActive = false;
                        }
                        lobby.origin = request.getOrigin();
                        lobby.target = request.getTarget();
                        lobby.cheatFuncActive = request.isCheatActivated();
                        System.out.println("Before Decision");
                        if(lobby.currentLobbyState == GameStates.WAITING_FOR_PLAYER1_MOVE){
                            System.out.println("Current Lobbystate was WaitForPlayer1move");
                            if(con==lobby.player1.connection){
                                System.out.println("Connection was the same with player 1");
                                System.out.println(lobby.player1);
                                lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER2_MOVE;
                                ObjectSender.sendGameDataObject(lobby.player2.connection, lobby, request);
                            }
                        } else {
                            System.out.println("Current Lobbystate was WaitForPlayer2move");
                            if(con==lobby.player2.connection){
                                System.out.println("Connection was the same with player 2");
                                System.out.println(lobby.player2);
                                lobby.currentLobbyState = GameStates.WAITING_FOR_PLAYER1_MOVE;
                                ObjectSender.sendGameDataObject(lobby.player1.connection, lobby, request);
                            }
                        }
                    } else {
                        //TODO lobby doesn't exist
                    }
                }

                System.out.println(con.toString() +"\t"+ o.toString() +"\n");
            }

            @Override
            public void connected(Connection connection) {
                System.out.println("Connected: " +connection.toString());
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("Disconnected: " +connection.toString());
            }
        });
    }
}
