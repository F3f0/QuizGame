package Server;


import java.io.IOException;
import java.util.List;

public class Game extends Thread {
    public int questionNr = 0;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    Question q1;
    Question q2;
    DAO database;
    List<Question> questions;

    Thread thread = new Thread(this);
    public Game() throws IOException {
        database= new DAO();
        questions = database.getQuestionForGame();

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

            currentPlayer.sendMessageToPlayer(questions.get(0));
            String temp;
            temp = currentPlayer.reciever.getAnswer();
            System.out.println("Svar mottaget " + temp);
            if (temp.equalsIgnoreCase(questions.get(questionNr).getAnswer())) {
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
