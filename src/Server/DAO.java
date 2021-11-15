package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    
    private final List<Question> questions = new ArrayList<>();

    DAO() throws IOException {
        BufferedReader getQuestionsFromFile = new BufferedReader(new FileReader("src/questions.txt"));
        String line;
        while (getQuestionsFromFile.readLine() != null) {
            Question qst = new Question();
            line = getQuestionsFromFile.readLine();
            qst.setQuestion(line);
            line = getQuestionsFromFile.readLine();
            qst.setCase1(line);
            line = getQuestionsFromFile.readLine();
            qst.setCase2(line);
            line = getQuestionsFromFile.readLine();
            qst.setCase3(line);
            line = getQuestionsFromFile.readLine();
            qst.setCase4(line);
            line = getQuestionsFromFile.readLine();
            qst.setAnswer(line);
            questions.add(qst);
        }
        getQuestionsFromFile.close();
    }
    public List<Question> getQuestionForGame(){
        return questions;
    }
}
