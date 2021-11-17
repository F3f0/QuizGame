package Client;

import javax.swing.*;
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
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(5,2,5,2));

         labels = new JLabel[] {l1,l2,l3,l4,l5,l6,l7};

        for (int i = 0; i < labels.length ; i++) {
            labels[i] = new JLabel("" + (i +1));
            labels[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setBackground(Color.WHITE);
            labels[i].setOpaque(true);
            this.add(labels[i]);
        }

        labels[3].setText("<html><center>1</center><center>Historia</center> </html>");


    }
}
