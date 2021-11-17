package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(55555);
        while(true){
            Game game = new Game();
            try{
                Player playerOne = new Player("Player 1", serverSocket.accept(), game);
                Player playerTwo = new Player("Player 2", serverSocket.accept(), game);
                game.setPlayerOne(playerOne);
                game.setPlayerTwo(playerTwo);
                playerOne.start();
                playerTwo.start();
                game.thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // lägga till finally?

        }
    }

    public static void main(String[]args) throws IOException {
        Server server = new Server();
    }
}
