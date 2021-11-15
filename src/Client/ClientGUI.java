package Client;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame{
    JButton btn1;
    JButton btn2;
    JLabel question;
    Client client;

  public ClientGUI(Client client){
      this.client = client;
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(500,300));
      panel.setLayout(new GridBagLayout());
      btn1 = new JButton("Alt1");
      btn1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            client.sendAnswer("alt1");
          }
      });
      //btn1.setPreferredSize(new Dimension(200,100));
      btn2 = new JButton("Alt2");
      btn2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              client.sendAnswer("alt2");
          }
      });
      //btn2.setPreferredSize(new Dimension(200,100));
      question = new JLabel("Fråga");
      //question.setPreferredSize(new Dimension(500,100));
      question.setBackground(Color.GRAY);
      question.setOpaque(true);
      question.setHorizontalAlignment(SwingConstants.CENTER);

      GridBagConstraints gc = new GridBagConstraints();
      gc.fill = GridBagConstraints.BOTH;
      gc.gridy = 0;
      gc.gridx = 0;
      gc.gridwidth = 2;
      gc.weightx = 0;
      gc.weighty = 0.3;
      panel.add(question, gc);

      gc.fill = GridBagConstraints.BOTH;
      gc.weightx = 0.5;
      gc.weighty = 0.5;
      gc.gridwidth = 1;
      gc.gridy = 1;
      panel.add(btn1, gc);
      gc.gridx = 1;
      panel.add(btn2, gc);
      add(panel);
      pack();
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

}
