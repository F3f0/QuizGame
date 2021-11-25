package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class IntroPanel extends JPanel {
    JButton button =  new JButton("Start");
    private ImageIcon pictureIcon;
    private JLabel myLabel;
    private JPanel homePanel;

    public IntroPanel(ActionListener a){

            pictureIcon = new ImageIcon(Objects.requireNonNull(super.getClass().getResource("picture.jpg")));
            myLabel = new JLabel(pictureIcon);
            myLabel.setSize(550, 370);


        homePanel = new JPanel();

        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.addActionListener(a);
        setLayout(null);
        button.setBounds(150, 300, 250, 25);

        add(myLabel);
        add(button);

        add(homePanel);
        setPreferredSize(new Dimension(550,370));
        setVisible(true);
    }
}
