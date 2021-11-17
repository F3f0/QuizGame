package Client;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener{

    Client client;
    GamePanel gamePanel;
    ScorePanel scorePanel;
    IntroPanel introPanel;

  public ClientGUI(Client client){
      this.client = client;
      gamePanel = new GamePanel(this);
      scorePanel = new ScorePanel();
      introPanel = new IntroPanel(this);
      this.setContentPane(introPanel);

      pack();
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(e.getSource().equals(gamePanel.btn1)){
          client.sendAnswer("1");
      }
      if(e.getSource().equals(gamePanel.btn2)){
          client.sendAnswer("2");
      }
        if(e.getSource().equals(gamePanel.btn3)){
            client.sendAnswer("3");
        }
        if(e.getSource().equals(gamePanel.btn4)){
            client.sendAnswer("4");
        }
        if(e.getSource().equals(introPanel.button)){
            client.thread.start();

        }
    }
}
