package Server;


import java.util.ArrayList;

public class Game extends Thread {
    public int questionNr = 0;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    Question q1;
    Question q2;
    ArrayList<Question> questions;

    Thread thread = new Thread(this);
    public Game(){
        q1 = new Question("När fick vi allmän rösträtt i Sverige", "1875","1921","alt2");
        q2 = new Question("När introducerades Java?", "1993", "1995", "alt2");
        questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    @Override
    public void run() {
        currentPlayer = playerOne;
        while(true) {

            currentPlayer.sendMessageToPlayer(questions.get(questionNr));
            String temp;
            temp = currentPlayer.reciever.getAnswer();
            if (temp.equalsIgnoreCase(questions.get(questionNr).correctAnswer)) {
                currentPlayer.sendMessageToPlayer("Rätt svar!");
            } else {
                currentPlayer.sendMessageToPlayer("Fel svar!");
            }
            if(questionNr==0 && currentPlayer == playerTwo){
                questionNr++;
            } else if (questionNr == 1 && currentPlayer == playerTwo){
                questionNr = 0;
            }
            if(currentPlayer == playerOne){
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }

        }
    }
}
