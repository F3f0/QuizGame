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
                        client.gui.scorePanel.player1.setText("Player 1");
                        client.gui.scorePanel.player2.setText("Player 2");
                    } else if (obj instanceof String){
                        String s = (String) obj;
                        if (s.equalsIgnoreCase("Player 1")){
                            client.gui.scorePanel.player1.setText("<html><center>" +"You are player 1, waiting for player 2 to connect" + "</center></html>");
                        }else if (s.equalsIgnoreCase("Player 2")){
                            client.gui.scorePanel.player2.setText("<html><center>" + "You are player 2. Waiting for player 1 to finish round" + "</center></html>");
                        } else
                        {
                            System.out.println(s);
                            client.gui.gamePanel.question.setText(s);
                            Thread.sleep(1500);
                            client.gui.setContentPane(client.gui.scorePanel);
                            client.gui.repaint();
                            client.gui.revalidate();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}