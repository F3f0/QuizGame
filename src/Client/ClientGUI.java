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
    String correctAnswer = "";


    public ClientGUI(Client client){
      this.client = client;
      introPanel = new IntroPanel(this);
      this.setContentPane(introPanel);

      pack();
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void setRemainingPanels(int amountOfRows, int amountOfQuestions){
      gamePanel = new GamePanel(this);
      scorePanel = new ScorePanel(this, amountOfRows, amountOfQuestions);
  }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(e.getSource().equals(gamePanel.btn1)){
          client.sendAnswer(gamePanel.btn1.getText());
          if(gamePanel.btn1.getText().equalsIgnoreCase(correctAnswer)){
              gamePanel.btn1.setBackground(Color.GREEN);
              revalidate();
              repaint();
          }
          else if(!gamePanel.btn1.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")){
              gamePanel.btn1.setBackground(Color.RED);
              revalidate();
              repaint();
              correctAnswer = "";
          }
      }
      if(e.getSource().equals(gamePanel.btn2)){
          client.sendAnswer(gamePanel.btn2.getText());
          if(gamePanel.btn2.getText().equalsIgnoreCase(correctAnswer)){
              gamePanel.btn2.setBackground(Color.GREEN);
              revalidate();
              repaint();
          } else if(correctAnswer.equalsIgnoreCase("")){}
          else if(!gamePanel.btn2.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category"))
          {
              gamePanel.btn2.setBackground(Color.RED);
              revalidate();
              repaint();
              correctAnswer = "";
          }
      }
      if(e.getSource().equals(gamePanel.btn3)){
          client.sendAnswer(gamePanel.btn3.getText());
          if(gamePanel.btn3.getText().equalsIgnoreCase(correctAnswer)){
              gamePanel.btn3.setBackground(Color.GREEN);
              revalidate();
              repaint();
          } else if(!gamePanel.btn3.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")){
              gamePanel.btn3.setBackground(Color.RED);
              revalidate();
              repaint();
              correctAnswer = "";
          }
      }
      if(e.getSource().equals(gamePanel.btn4)){
          client.sendAnswer(gamePanel.btn4.getText());
          if(gamePanel.btn4.getText().equalsIgnoreCase(correctAnswer)){
              gamePanel.btn4.setBackground(Color.GREEN);
              revalidate();
              repaint();
          }
          else if(!gamePanel.btn4.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")){
              gamePanel.btn4.setBackground(Color.RED);
              revalidate();
              repaint();
              correctAnswer = "";
          }
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
