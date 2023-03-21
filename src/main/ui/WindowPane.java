package ui;


import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.awt.GridBagConstraints.*;

public class WindowPane extends Frame {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();



    public WindowPane() {
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        addTitleCard();
        addLoginButton();
        addCreateAccountButton();
        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addTitleCard() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/TitleCard.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        label.setSize(new Dimension(500,580));
        jframe.add(label);
    }

    private void addLoginButton() {
        JButton loginButton =
                new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/buttom.png"))));

        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        constraints.anchor = SOUTH;
        panel.add(loginButton, constraints);
        constraints.weighty = 0;
        jframe.add(panel);

    }

    private void addCreateAccountButton() {
        JButton createAccountButton =
                new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/untitled.png"))));
        panel.add(createAccountButton, constraints);

        jframe.add(panel);
    }

    
}
