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
      scorePanel = new ScorePanel(this);
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
          client.sendAnswer(gamePanel.btn1.getText());
      }
      if(e.getSource().equals(gamePanel.btn2)){
          client.sendAnswer(gamePanel.btn2.getText());
      }
      if(e.getSource().equals(gamePanel.btn3)){
          client.sendAnswer(gamePanel.btn3.getText());
      }
      if(e.getSource().equals(gamePanel.btn4)){
          client.sendAnswer(gamePanel.btn4.getText());
      }
      if(e.getSource().equals(introPanel.button)){
          client.thread.start();
      }
      if(e.getSource().equals(scorePanel.button)){
          client.sendAnswer("start");
          client.gui.scorePanel.button.setVisible(false);
      }
    }
}
