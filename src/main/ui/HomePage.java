package ui;

import javax.swing.*;
import java.awt.*;


import static java.awt.GridBagConstraints.REMAINDER;

//Represents the homepage
public class HomePage {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();

    //EFFECTS: constructs a homepage
    public HomePage(String gardenName) {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        displayWelcomeNote(gardenName);

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayWelcomeNote(String name) {
        JLabel welcome = new JLabel("Welcome to " + name + "'s" + " garden!");
        welcome.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(welcome, constraints);
        jframe.add(panel);
    }
}
