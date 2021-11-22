package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempFrame extends JFrame implements ActionListener{
    ScorePanel scorePanel;
    public TempFrame(){
        scorePanel = new ScorePanel(this);
       add(scorePanel);
       pack();
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[]args){
        TempFrame tempFrame = new TempFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
