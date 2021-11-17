package Server;

import Questions.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    private final List<Question> questions = new ArrayList<>();
    private String temp;
    private String[] cases;

    Database() throws IOException {
        BufferedReader getQuestionsFromFile = new BufferedReader(new FileReader("src/questions.txt"));
        String line;
        while ((line = getQuestionsFromFile.readLine()) != null) {
            Question qst = new Question();                              //Objekt question skapas
                                 //Läser första rad > första fråga
            qst.setCategory(line.substring(0, line.indexOf(':')+1));
            qst.setQuestion(line.substring(line.indexOf(':')+2, line.indexOf('?')+1));
            String temp = line.substring(line.indexOf('*')+1);          //String med bara svår
            String [] cases = temp.split("\\*");
            System.out.println(cases[0]);//Splittrar alla svår
            System.out.println(cases[1]);
            System.out.println(cases[2]);
            System.out.println(cases[3]);
            qst.setCase1(cases[0]);                         //alltid rätt svar
            qst.setCase2(cases[1]);
            qst.setCase3(cases[2]);
            qst.setCase4(cases[3]);
            questions.add(qst);
            //Adderar vi frågan i listan
        }
        getQuestionsFromFile.close();
    }
    public List<Question> getQuestionForGame(){
        return questions;
    }      //Returnerar listan till server
}
