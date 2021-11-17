package Server;


import Questions.Question;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Game extends Thread {
    public int questionNr = 0;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    Question q1;
    Question q2;
    Database database;
    List<Question> questions;

    Thread thread = new Thread(this);
    public Game() throws IOException {
        database= new Database();
        questions = database.getQuestionsForGame();
        Collections.shuffle(questions);
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
            System.out.println(questionNr);
            currentPlayer.sendMessageToPlayer(questions.get(questionNr));
            String temp;
            temp = currentPlayer.receiver.getAnswer();
            System.out.println("Svar mottaget " + temp);
            if (temp.equalsIgnoreCase(questions.get(questionNr).getCorrectAnswer())) {
                currentPlayer.sendMessageToPlayer("Rätt svar!");
            } else {
                currentPlayer.sendMessageToPlayer("Fel svar!");
            }
            if(currentPlayer == playerTwo){
                questionNr++;
            }
            if(currentPlayer == playerOne){
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }
        }
    }
}
