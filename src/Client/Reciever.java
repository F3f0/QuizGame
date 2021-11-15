package Client;

import Client.Client;
import Server.Question;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Reciever extends Thread {
    Thread thread;
    ObjectInputStream in;
    Socket socket;
    Client client;
    Object obj;
    public Reciever(Socket socket, Client client){
        this.socket = socket;
        this.client = client;

        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        while(true) {

            try {
                while (((obj = in.readObject())!=null)) {
                    if(obj instanceof Question) {
                        client.setCurrentQuestion((Question)obj);
                    } else if (obj instanceof String){
                        String s = (String) obj;

                        client.gui.question.setText(s);
                        Thread.sleep(2500);
                    }
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}