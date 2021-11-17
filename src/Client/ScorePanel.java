package Client;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    JPanel upperPanel;
    JPanel lowerPanel;
    Row row1;
    Row row2;
    Row row3;
    Row row4;
    Row row5;
    Row row6;

    JLabel player1;
    JLabel player2;
        
    public ScorePanel(){
        setLayout(new BorderLayout());
        upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(500,100));
        upperPanel.setBackground(Color.white);
        upperPanel.setLayout(new GridLayout(1,2));
        player1 = new JLabel("Player1");
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2 = new JLabel("Player2");
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        upperPanel.add(player1);
        upperPanel.add(player2);
        lowerPanel = new JPanel();
        //lowerPanel.setPreferredSize(new Dimension(500,500));
        lowerPanel.setBackground(Color.white);
        lowerPanel.setLayout(new GridLayout(6,1));

        row1 = new Row();
        row2 = new Row();
        row3 = new Row();
        row4 = new Row();
        row5 = new Row();
        row6 = new Row();



        lowerPanel.add(row1);
        lowerPanel.add(row2);
        lowerPanel.add(row3);
        lowerPanel.add(row4);
        lowerPanel.add(row5);
        lowerPanel.add(row6);

        row1.labels[0].setBackground(Color.GREEN);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.CENTER);

    }
}
