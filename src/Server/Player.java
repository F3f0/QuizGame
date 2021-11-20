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
    String q1;
    String q2;
    String q3;
    String [] results = {q1,q2,q3};

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
            out.reset();
            out.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResults(int questionNr, String correctOrFalse){
        results[questionNr] = correctOrFalse;
    }
    public String [] getResults(){
        return results;
    }


    @Override
    public void run() {
    }
}
