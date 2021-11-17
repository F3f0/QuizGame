package Server;

import Questions.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Database {
    
    private final List<Question> questions = new ArrayList<>();
    private String temp;
    private String[] cases;

    Database() throws IOException {
        BufferedReader getQuestionsFromFile = new BufferedReader(new FileReader("src/questions.txt"));
        String line;
        while ((line = getQuestionsFromFile.readLine()) != null) {
            Question qst = new Question();
            qst.setCategory(line.substring(0, line.indexOf(':')+1));
            qst.setQuestion(line.substring(line.indexOf(':')+2, line.indexOf('?')+1));
            temp = line.substring(line.indexOf('*')+1);
            cases = temp.split("\\*");
            qst.setCorrectAnswer(cases[0]);
            qst.setCase1(cases[0]);
            qst.setCase2(cases[1]);
            qst.setCase3(cases[2]);
            qst.setCase4(cases[3]);
            questions.add(qst);
        }
        getQuestionsFromFile.close();
    }
    public List<Question> getQuestionsForGame(){
        return questions;
    }
}
