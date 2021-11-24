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
      gamePanel = new GamePanel(this);
      scorePanel = new ScorePanel(this, 4, 4);

      pack();
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void setRemainingPanels(int amountOfRows, int amountOfQuestions){
        scorePanel = new ScorePanel(this, amountOfRows, amountOfQuestions);
        repaint();
        revalidate();
        setContentPane(scorePanel);
  }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(e.getSource().equals(gamePanel.btn1)){
          if(!gamePanel.btn1.getText().equals("")) {
              client.sendAnswer(gamePanel.btn1.getText());
              System.out.println("skickat");
              if (gamePanel.btn1.getText().equalsIgnoreCase(correctAnswer)) {
                  gamePanel.btn1.setBackground(new Color(88, 214, 141));
                  revalidate();
                  repaint();
              } else if (!gamePanel.btn1.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")) {
                  gamePanel.btn1.setBackground(new Color(192, 57, 43));
                  revalidate();
                  repaint();
                  correctAnswer = "";
              }
          }
      }
      if(e.getSource().equals(gamePanel.btn2)){
          if(!gamePanel.btn2.getText().equals("")) {
              client.sendAnswer(gamePanel.btn2.getText());
              if (gamePanel.btn2.getText().equalsIgnoreCase(correctAnswer)) {
                  gamePanel.btn2.setBackground(new Color(88, 214, 141));
                  revalidate();
                  repaint();
              } else if (correctAnswer.equalsIgnoreCase("")) {
              } else if (!gamePanel.btn2.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")) {
                  gamePanel.btn2.setBackground(new Color(192, 57, 43));
                  revalidate();
                  repaint();
                  correctAnswer = "";
              }
          }
      }
      if(e.getSource().equals(gamePanel.btn3)){
          if(!gamePanel.btn3.getText().equals("")) {
              client.sendAnswer(gamePanel.btn3.getText());
              if (gamePanel.btn3.getText().equalsIgnoreCase(correctAnswer)) {
                  gamePanel.btn3.setBackground(new Color(88, 214, 141));
                  revalidate();
                  repaint();
              } else if (!gamePanel.btn3.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")) {
                  gamePanel.btn3.setBackground(new Color(192, 57, 43));
                  revalidate();
                  repaint();
                  correctAnswer = "";
              }
          }
      }
      if(e.getSource().equals(gamePanel.btn4)){
          if(!gamePanel.btn4.getText().equals("")) {
              client.sendAnswer(gamePanel.btn4.getText());
              if (gamePanel.btn4.getText().equalsIgnoreCase(correctAnswer)) {
                  gamePanel.btn4.setBackground(new Color(88, 214, 141));
                  revalidate();
                  repaint();
              } else if (!gamePanel.btn4.getText().equalsIgnoreCase(correctAnswer) && !correctAnswer.equals("category")) {
                  gamePanel.btn4.setBackground(new Color(192, 57, 43));
                  revalidate();
                  repaint();
                  correctAnswer = "";
              }
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
