package Server;

import java.io.Serializable;

public class Question implements Serializable {

    String question;
    String alt1;
    String alt2;
    public String correctAnswer;

    public Question(String question, String alt1, String alt2, String correctAnswer) {
        this.question = question;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAlt1() {
        return alt1;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
