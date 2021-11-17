package Server;

import java.io.*;
import java.net.Socket;

public class Player extends Thread{
    String player;
    Socket socket;
    Game game;
    Thread thread;
    BufferedReader in;
    ObjectOutputStream out;
    ServerReceiver receiver;

    public Player(String player, Socket socket, Game game) {
        this.player = player;
        this.socket = socket;
        this.game = game;
        receiver = new ServerReceiver(socket);

        try{
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
    }

    public void sendMessageToPlayer(Object o){
        try {
            System.out.println(o);
            out.writeObject(o);
            System.out.println("Klart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
    }
}
