package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel {

    JPanel upperPanel;
    JPanel lowerPanel;
    Row row1,row2,row3,row4;
    Row currentRow;
    Row[] rows;

    int currentRowID = 0;
    int score1;
    int score2;
    //TEst

    JButton button;

    JLabel player1;
    JLabel player2;
    JLabel score;
        
    public ScorePanel(ActionListener a, int amountOfRows, int amountOfQuestions){
        setLayout(new BorderLayout());
        upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(500,100));
        upperPanel.setBackground(new Color(40, 55, 71));
        upperPanel.setLayout(new GridLayout(1,2));
        player1 = new JLabel("Player1");
        player1.setForeground(Color.WHITE);
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2 = new JLabel("Player2");
        player2.setForeground(Color.WHITE);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        score = new JLabel("0 - 0");
        score.setForeground(Color.white);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        upperPanel.add(player1);
        upperPanel.add(score);
        upperPanel.add(player2);
        lowerPanel = new JPanel();
        //lowerPanel.setPreferredSize(new Dimension(500,500));
        lowerPanel.setBackground(new Color(40, 55, 71));
        lowerPanel.setLayout(new GridLayout(6,1));
        button = new JButton("Start new round");

        rows = new Row[amountOfRows];

        for(int i = 0; i < rows.length; i++){
            rows[i] = new Row(amountOfQuestions);
            lowerPanel.add(rows[i]);
        }
        button.setVisible(false);
        button.addActionListener(a);
        lowerPanel.add(button);

        //row1.labels[0].setBackground(Color.GREEN);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.CENTER);
        currentRow = rows[currentRowID];
    }

    public void setCurrentRow(){
        currentRowID ++;
        if(currentRowID<rows.length) {
            currentRow = rows[currentRowID];
        }
    }
}
