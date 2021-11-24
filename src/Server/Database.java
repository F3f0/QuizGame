package Server;

import Questions.Category;
import Questions.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Database {
    int categories = 5;
    private final List<Question> questions = new ArrayList<>();
    private String temp;
    private String[] cases;
    private ArrayList<ArrayList<Question>> questionsByCategory = new ArrayList<>(categories);

    Database() throws IOException {
        BufferedReader getQuestionsFromFile = new BufferedReader(new FileReader("src/questions.txt"));
        String line;
        while ((line = getQuestionsFromFile.readLine()) != null) {
            Question qst = new Question();
            qst.setCategory(line.substring(0, line.indexOf(':')));
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

    public ArrayList<ArrayList<Question>> getQuestionsByCategory (){
        ArrayList<ArrayList<Question>> listByCategory = questionsByCategory;

        for(int i=0; i <= categories; i++) {
            listByCategory.add(new ArrayList());
        }
        Collections.shuffle(questions);

        for(Question qst : questions)
            switch (qst.getCategory()){
                case "History":
                    listByCategory.get(0).add(qst);
                    break;
                case "Movies":
                    listByCategory.get(1).add(qst);
                    break;
                case "Sport":
                    listByCategory.get(2).add(qst);
                    break;
                case "Geography":
                    listByCategory.get(3).add(qst);
                    break;
                case "Music":
                    listByCategory.get(4).add(qst);
                    break;
                case "Computers":
                    listByCategory.get(5).add(qst);
                    break;
            }
        return listByCategory;
    }

    public int getCategoryByNumber(String str) {
        int temp;
        switch (str) {
            case "History:":
                temp = 0;
                break;
            case "Movies:":
                temp = 1;
                break;
            case "Sport:":
                temp = 2;
                break;
            case "Geography:":
                temp = 3;
                break;
            case "Music:":
                temp = 4;
                break;
            case "Computers:":
                temp = 5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
        return temp;
    }

}
