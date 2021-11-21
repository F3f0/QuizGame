package Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Row extends JPanel {
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;
    JLabel l7;
    JLabel [] labels;
    public Row(){
        setPreferredSize(new Dimension(500,45));
        setLayout(new GridLayout(1,7,4,0));
        setBackground(new Color(40, 55, 71));
        setBorder(BorderFactory.createEmptyBorder(5,2,5,2));


        labels = new JLabel[] {l1,l2,l3,l4,l5,l6,l7};

        for (int i = 0; i < labels.length ; i++) {
            LineBorder line = new LineBorder(new Color(100,149,237),2,true);
            labels[i] = new JLabel("" + (i +1));
            labels[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setBackground(new Color(40, 55, 71));
            labels[i].setForeground(Color.white );
            labels[i].setBorder(line);
            labels[i].setOpaque(true);
            this.add(labels[i]);
        }



    }
}
