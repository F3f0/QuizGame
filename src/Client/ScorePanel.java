package Client;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    JPanel upperPanel;
    JPanel lowerPanel;
    Row row1,row2,row3,row4;
    Row currentRow;
    Row[] rows;
    int currentRowID = 0;

    JLabel player1;
    JLabel player2;
        
    public ScorePanel(){
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
        upperPanel.add(player1);
        upperPanel.add(player2);
        lowerPanel = new JPanel();
        //lowerPanel.setPreferredSize(new Dimension(500,500));
        lowerPanel.setBackground(new Color(40, 55, 71));
        lowerPanel.setLayout(new GridLayout(6,1));


        row1 = new Row("History");
            row1.setBackground(new Color(40, 55, 71));
        row2 = new Row("Geography");
            row2.setBackground(new Color(40, 55, 71));
        row3 = new Row("Football");
            row3.setBackground(new Color(40, 55, 71));
        row4 = new Row("Music");
            row4.setBackground(new Color(40, 55, 71));


        lowerPanel.add(row1);
        lowerPanel.add(row2);
        lowerPanel.add(row3);
        lowerPanel.add(row4);

        rows = new Row[]{row1,row2,row3,row4};


        //row1.labels[0].setBackground(Color.GREEN);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.CENTER);
        currentRow = rows[currentRowID];
    }

    public void setCurrentRow(){
        currentRowID ++;
        currentRow = rows[currentRowID];
    }
}
