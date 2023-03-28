package ui.gui;


import ui.GardenApp;

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
    private GardenApp gardenApp;


    //EFFECTS: constructs a login page
    public LoginPage() {
        makeWindow();
    }

    //EFFECTS: sets up the window of the current screen
    public void makeWindow() {
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        addTitleCard();
        addLoginButton();
        addCreateAccountButton();
        addQuitButton();

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
        JButton loginButton = makeButton("src/main/ui/Images/Buttons/LoginButton.png");
        loginButton.setActionCommand("Login");
    }

    //EFFECTS: adds create account button to let users start a new game
    private void addCreateAccountButton() {
        JButton createAccountButton = makeButton("src/main/ui/Images/NewGarden.png");
        createAccountButton.setActionCommand("New garden");
    }

    //EFFECTS: adds create quit button to let users exit application
    private void addQuitButton() {
        JButton quitButton = makeButton("src/main/ui/Images/Buttons/QuitButton.png");
        quitButton.setActionCommand("Quit");
    }

    //EFFECTS: makes a button and places it on the frame
    private JButton makeButton(String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.addActionListener(this);
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
        return button;
    }

    //EFFECTS: processes command after button is pressed
    // "Login" will bring user to their home page immediately, else, user will go to create account page
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            loadAccount();
            new HomePage(gardenApp);
            jframe.setVisible(false);
        }
        if (e.getActionCommand().equals("New garden")) {
            new CreateAccountPage();
            jframe.setVisible(false);
        }
        if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
    }

    //EFFECTS: loads previously saved garden, wallet and inventory
    public void loadAccount() {
        gardenApp = new GardenApp();
        gardenApp.loadProgress();
    }


}
