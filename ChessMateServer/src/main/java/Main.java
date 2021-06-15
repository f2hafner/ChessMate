import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMateServer chessMateServer = new ChessMateServer();
        chessMateServer.start();
        Scanner in = new Scanner(System.in);
        String input="";
        boolean commandExecuted;
        try {Thread.sleep(2000);} catch(InterruptedException e) {}
        Log.v("SYSTEM","Type '/help' to see the available commands!");
        while(true){
            commandExecuted = false;
            if(in.hasNext()){ input = in.nextLine(); }
            if(input.equalsIgnoreCase("/quit")){
                System.exit(0);
                commandExecuted = true;
            }
            if(input.equalsIgnoreCase("/help")){
                Log.v("HELP PAGE"," - "+"/quit"+"\t"+"Closes the Server");
                Log.v("HELP PAGE"," - "+"/list"+"\t"+"Lists informations about the Lobbies online");
                commandExecuted = true;
            }
            if(input.equalsIgnoreCase("/list")){
                LobbyManager.printAllCurrentSession();
                commandExecuted = true;
            }
            if(commandExecuted) input="";
        }
    }
}
