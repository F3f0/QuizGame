package Server;


import Questions.Question;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Game extends Thread {
    public int roundNr = 1;
    public int questionNr = 0;
    public boolean p1Answered;
    public boolean p2Answered;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    Database database;
    public int scoreP1;
    public int scoreP2;
    String score = "";
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
        playerTwo.sendMessageToPlayer("Player 2");
        while(true) {
            currentPlayer.sendMessageToPlayer(questions.get(questionNr));
            String temp;
            temp = currentPlayer.receiver.getAnswer();
            if (temp.equalsIgnoreCase(questions.get(questionNr).getCorrectAnswer())) {
                currentPlayer.sendMessageToPlayer("Rätt svar!");
                currentPlayer.setResults(questionNr, "correct");
                System.out.println("saved correct");
            } else {
                currentPlayer.sendMessageToPlayer("Fel svar!");
                currentPlayer.setResults(questionNr, "false");
                System.out.println("saved false");
            }
            questionNr ++;
            System.out.println(questionNr);
            if(currentPlayer == playerOne && questionNr == 3){
                p1Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                scoreP1 = checkScore(currentPlayer.results);
            }
            else if (currentPlayer == playerTwo && questionNr == 3) {
                p2Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                scoreP2 = checkScore(currentPlayer.results);
            }
            playerOne.sendMessageToPlayer(score);
            playerTwo.sendMessageToPlayer(score);
            if (p1Answered && p2Answered){
                questionNr = 0;
                p1Answered = false;
                p2Answered = false;
                playerOne.sendMessageToPlayer("Next Round");
                playerTwo.sendMessageToPlayer("Next Round");
                setScore(scoreP1,scoreP2);
                playerOne.sendMessageToPlayer(score);
                playerTwo.sendMessageToPlayer(score);
            } else if (questionNr==3) {
                changePlayer();
                questionNr = 0;
            }
        }
    }

    public void changePlayer(){
        if (currentPlayer == playerOne){
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    public int checkScore(String[] s){
        int points = 0;
        for (int i = 0; i <s.length ; i++) {
            if(s[i].equals("correct")){
                points ++;
            }
        }
        return points;
    }

    public void setScore(int scoreP1, int scoreP2){
        score = "" + scoreP1 + " - " + scoreP2;
    }
}
