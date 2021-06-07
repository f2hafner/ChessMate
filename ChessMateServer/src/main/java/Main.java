
import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMateServer chessMateServer = new ChessMateServer();
        chessMateServer.start();
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();
        if(input.equalsIgnoreCase("list")) LobbyManager.printAllCurrentSession();


    }
}
