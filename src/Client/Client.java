package Client;

import Questions.Question;

import javax.swing.*;
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
    Thread thread = new Thread(this);
    ClientGUI gui;
    List alternatives;

    public Client() {
        this.gui = new ClientGUI(this);

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
