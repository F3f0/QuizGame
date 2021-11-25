package Server;

import Questions.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
    public int amountOfQuestions;
    public int amountOfRows;
    public int roundNr = 1;
    public int questionNr = 0;
    public boolean p1Answered = true;
    public boolean p2Answered = true;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    Database database;
    public int pointsP1;
    public int pointsP2;
    public int scoreTotP1 = 0;
    public int scoreTotP2 = 0;
    String score = "";
    String category;
    ArrayList<ArrayList<Question>> questions;
    String temp = "";
    List<String> categories;

    Thread thread = new Thread(this);
    public Game() throws IOException {
        database= new Database();
        questions = database.getQuestionsByCategory();
        categories = database.getCategoryList();
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

        while (true) {
            if (p1Answered && p2Answered && questionNr == 0) {
                removeCategory();
                startNewRound();
                sendAndRecieveCategory();
                p1Answered = false;
                p2Answered = false;
            }
            sendQuestion();
            getAnswer();
            registerResult();
            questionNr ++;
            if (questionNr == amountOfQuestions) {
                updatePlayerPoints();
            }
            if (p1Answered && p2Answered){
                questionNr = 0;
                setScore(pointsP1,pointsP2);
                playerOne.sendMessageToPlayer(score);
                playerTwo.sendMessageToPlayer(score);
                playerOne.sendMessageToPlayer(playerTwo.results);
                playerTwo.sendMessageToPlayer(playerOne.results);
                if(roundNr==amountOfRows){
                    if(scoreTotP1>scoreTotP2){
                        playerOne.sendMessageToPlayer("won");
                        playerTwo.sendMessageToPlayer("lost");
                    } else if(scoreTotP1<scoreTotP2){
                        playerTwo.sendMessageToPlayer("won");
                        playerOne.sendMessageToPlayer("lost");
                    } else{
                        playerOne.sendMessageToPlayer("tied");
                        playerTwo.sendMessageToPlayer("tied");
                    }
                }
                questionNr = 0;
                playerOne.sendMessageToPlayer("Next Round");
                playerTwo.sendMessageToPlayer("Next Round");
                roundNr ++;
            } else if (questionNr==amountOfQuestions) {
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
        for (int i = 1; i <s.length ; i++) {
            if(s[i].equals("correct")){
                points ++;
            }
        }
        return points;
    }

    public void setScore(int pointsP1, int pointsP2){

        if(pointsP1>pointsP2){
            scoreTotP1 ++;
        } else if(pointsP1<pointsP2){
            scoreTotP2 ++;
        } else if (pointsP1==pointsP2){
            scoreTotP1 ++;
            scoreTotP2 ++;
        }
        score = "Score" + scoreTotP1 + " - " + scoreTotP2;
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
    }

    public void startNewRound(){
        currentPlayer.sendMessageToPlayer("start?");
        temp = currentPlayer.receiver.getAnswer();
    }

    public void sendAndRecieveCategory(){
        currentPlayer.sendMessageToPlayer(categories);
        category = currentPlayer.receiver.getAnswer();
    }

    public void sendQuestion(){
        currentPlayer.sendMessageToPlayer(questions.get(database.getCategoryByNumber(category)).get(questionNr));
        System.out.println("sent q");
    }

    public void getAnswer(){
        temp = currentPlayer.receiver.getAnswer();
    }

    public void registerResult(){
        if (temp.equalsIgnoreCase(questions.get(database.getCategoryByNumber(category)).get(questionNr).getCorrectAnswer())) {
            currentPlayer.sendMessageToPlayer("Rätt svar!");
            currentPlayer.setResults(questionNr, "correct");
        } else {
            currentPlayer.sendMessageToPlayer("Fel svar!");
            currentPlayer.setResults(questionNr, "false");
        }
    }

    public void removeCategory(){
        for (int i = 0; i <categories.size() ; i ++) {
            if(categories.get(i).equals(category)){
                categories.remove(categories.get(i));
            }
        }
    }

    public void updatePlayerPoints(){
            if(currentPlayer == playerOne) {
                p1Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                pointsP1 = checkScore(currentPlayer.results);
            } else if(currentPlayer == playerTwo){
                p2Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                pointsP2 = checkScore(currentPlayer.results);
            }
    }
}
