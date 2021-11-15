package Client;

import Server.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
        //push
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    Reciever reciever;
    Question currentQuestion;
    Thread thread = new Thread(this);
    ClientGUI gui;

    public Client() {
        this.gui = new ClientGUI(this);
        try{
        socket = new Socket("localhost", 55555);
        reciever = new Reciever(socket, this);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread.start();
    }

    public void setCurrentQuestion(Question q){
        currentQuestion = q;
        gui.question.setText(currentQuestion.getQuestion());
        gui.btn1.setText(currentQuestion.getCase1());
        gui.btn2.setText(currentQuestion.getCase2());
        gui.btn3.setText(currentQuestion.getCase3());
        gui.btn4.setText(currentQuestion.getCase4());


        //Behövs dessa?
        gui.repaint();
        gui.revalidate();
    }

    public static void main(String[]args)  {
        Client client = new Client();
    }

    public void sendAnswer(String s){
        out.println(s);
    }

    @Override
    public void run() {
        while(true) {


        }
    }
}
