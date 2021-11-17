package Client;

import javax.swing.*;

public class TempFrame extends JFrame {
    ScorePanel scorePanel;
    public TempFrame(){
       scorePanel = new ScorePanel();
       add(scorePanel);
       pack();
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[]args){
        TempFrame tempFrame = new TempFrame();
    }
}
