package Server;


import Questions.Category;
import Questions.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game extends Thread {
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
    Category categoryObj;
    String category;
    ArrayList<ArrayList<Question>> questions;

    Thread thread = new Thread(this);
    public Game() throws IOException {
        database= new Database();
        questions = database.getQuestionsByCategory();
        categoryObj = new Category();
        categoryObj = database.InitializeCategoryObject(categoryObj);
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
        while (true) {
            if (p1Answered && p2Answered && questionNr == 0) {
                currentPlayer.askWhichCategory(categoryObj);
                category = currentPlayer.receiver.getAnswer();
                System.out.println(category);
                p1Answered = false;
                p2Answered = false;
            }
            currentPlayer.sendMessageToPlayer(questions.get(database.getCategoryByNumber(category)).get(questionNr));
            System.out.println(questions.get(database.getCategoryByNumber(category)).get(questionNr));
            System.out.println(category);

            String temp;
            temp = currentPlayer.receiver.getAnswer();
            if (temp.equalsIgnoreCase(questions.get(database.getCategoryByNumber(category)).get(questionNr).getCorrectAnswer())) {           // FiX
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
            if (currentPlayer == playerOne && questionNr == 3){
                p1Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                pointsP1 = checkScore(currentPlayer.results);
            }
            else if (currentPlayer == playerTwo && questionNr == 3) {
                p2Answered = true;
                currentPlayer.sendMessageToPlayer(currentPlayer.results);
                pointsP2 = checkScore(currentPlayer.results);
            } if (p1Answered && p2Answered){
                questionNr = 0;
                setScore(pointsP1,pointsP2);
                playerOne.sendMessageToPlayer(score);
                playerTwo.sendMessageToPlayer(score);
                playerOne.sendMessageToPlayer(playerTwo.results);
                playerTwo.sendMessageToPlayer(playerOne.results);
                playerOne.sendMessageToPlayer("Next Round");
                playerTwo.sendMessageToPlayer("Next Round");
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
        System.out.println(game.questions.get(0).get(0).toString());

    }
}
