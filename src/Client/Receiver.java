package Client;

import Questions.Question;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver extends Thread {
    Thread thread;
    ObjectInputStream in;
    Socket socket;
    Client client;
    Object obj;
    public Receiver(Socket socket, Client client){
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
                        client.gui.setContentPane(client.gui.gamePanel);
                        client.gui.repaint();
                        client.gui.revalidate();
                    } else if (obj instanceof String){
                        String s = (String) obj;
                        client.gui.gamePanel.question.setText(s);
                        Thread.sleep(1500);
                        client.gui.setContentPane(client.gui.scorePanel);
                        client.gui.repaint();
                        client.gui.revalidate();
                    }
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}