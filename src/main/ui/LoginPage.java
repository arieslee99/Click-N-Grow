package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;

//Represents the login page
public class LoginPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    //EFFECTS: constructs a login page
    public LoginPage() {
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

    //EFFECTS: adds image of the name of the application
    private void addTitleCard() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/TitleCard.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        constraints.weighty = 0;
        jframe.add(panel);
    }

    //EFFECTS: adds the login button (for users to load their previous game)
    private void addLoginButton() {
        JButton loginButton =
                new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/LoginButton.png"))));
        constraints.anchor = CENTER;
        constraints.gridwidth = REMAINDER;
        panel.add(loginButton, constraints);
        constraints.weighty = 2;
        jframe.add(panel);
        loginButton.setActionCommand("Login");
        loginButton.addActionListener(this);

    }

    //EFFECTS: adds create account button to let users start a new game
    private void addCreateAccountButton() {
        JButton createAccountButton =
                new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/NewGarden.png"))));
        panel.add(createAccountButton, constraints);
        jframe.add(panel);
        createAccountButton.setActionCommand("New garden");
        createAccountButton.addActionListener(this);
    }

    //EFFECTS: processes command after button is pressed
    // "Login" will bring user to their home page immediately, else, user will go to create account page
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            new HomePage(null);
        } else {
            new CreateAccountPage();
        }
        jframe.setVisible(false);
    }


}
