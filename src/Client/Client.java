package Client;

import Questions.Category;
import Questions.Question;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    Receiver receiver;
    Question currentQuestion;
    Category category;
    Thread thread = new Thread(this);
    ClientGUI gui;
    List alternatives;
    List categories;
    int playerID;

    public Client() {
        this.gui = new ClientGUI(this);
    }

    public void setPlayerID(int ID){
        playerID = ID;
        if(playerID == 1){
            gui.scorePanel.player1.setText("<html><center>" +"You are player 1, waiting for player 2 to connect" + "</center></html>");
        } else if(playerID == 2){
            gui.scorePanel.player2.setText("<html><center>" + "You are player 2. Waiting for player 1 to finish round" + "</center></html>");
        }
        System.out.println("PlayerID = " + playerID);
    }

    public void setResults(String[] s){
        if (playerID == 1){
            for (int i = 0; i <s.length ; i++) {
                if(s[i].equalsIgnoreCase("correct")) {
                    gui.scorePanel.currentRow.labels[i].setBackground(new Color(154,205,50));
                } else if (s[i].equalsIgnoreCase("false")){
                    gui.scorePanel.currentRow.labels[i].setBackground(new Color(220,20,60));
                }
            }

        } else if (playerID == 2){
            for (int i = 0; i <s.length ; i++) {
                if(s[i].equalsIgnoreCase("correct")) {
                    gui.scorePanel.currentRow.labels[i+4].setBackground(new Color(154,205,50));
                } else if (s[i].equalsIgnoreCase("false")){
                    gui.scorePanel.currentRow.labels[i+4].setBackground(new Color(220,20,60));
                }
            }
        }
        gui.scorePanel.repaint();
        gui.scorePanel.revalidate();
        System.out.println("Updated results");
    }

    public void setScore(String s){
        gui.scorePanel.score.setText(s.substring(5));
    }

    public void setCurrentRow(){
        gui.scorePanel.setCurrentRow();
        System.out.println(gui.scorePanel.currentRowID);
    }

    public void setCurrentQuestion(Question q){
        currentQuestion = q;
        alternatives = q.getShuffledAlternatives();
        gui.gamePanel.question.setText("<html><center>" + currentQuestion.getQuestion() + "</center></html>");
        gui.gamePanel.btn1.setText((String) alternatives.get(0));
        gui.gamePanel.btn2.setText((String) alternatives.get(1));
        gui.gamePanel.btn3.setText((String) alternatives.get(2));
        gui.gamePanel.btn4.setText((String) alternatives.get(3));


        //Behövs dessa?
        gui.repaint();
        gui.revalidate();
    }

    public void setCategoryQuestion (Category c){
        category = c;
        categories = c.getShuffledCategories();
        gui.gamePanel.question.setText("<html><center>" + "Choose your category" + "</center></html>");
        gui.gamePanel.btn1.setText((String) categories.get(0));
        gui.gamePanel.btn2.setText((String) categories.get(1));
        gui.gamePanel.btn3.setText((String) categories.get(2));
        gui.gamePanel.btn4.setText((String) categories.get(3));
        gui.repaint();
        gui.revalidate();
    }

    public static void main(String[]args)  {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Client client = new Client();
    }

    public void sendAnswer(String s){
        out.println(s);
    }

    @Override
    public void run() {
        System.out.println("startar..");
        gui.setContentPane(gui.scorePanel);
        gui.repaint();
        gui.revalidate();
        try{
            socket = new Socket("localhost", 55555);
            receiver = new Receiver(socket, this);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {

        }
    }
}
