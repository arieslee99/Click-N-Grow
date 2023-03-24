package ui.gui;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import static java.awt.GridBagConstraints.REMAINDER;

//Represents the homepage
public class HomePage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a homepage
    public HomePage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
    }

    //EFFECTS: sets up the window of the current screen
    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        addHomeImage();
        displayWelcomeNote(gardenApp.getGardenName());
        addMyGardenButton();
        addStoreButton();
        addQuitButton();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addHomeImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/HomeImage.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    public void displayWelcomeNote(String name) {
        JLabel welcome = new JLabel("Welcome to " + name + "'s" + " garden!");
        welcome.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(welcome, constraints);
        jframe.add(panel);
    }

    private void addMyGardenButton() {
        JButton gardenButton = makeButton("src/main/ui/Images/MyGardenButton.png");
        gardenButton.setActionCommand("My garden");
    }

    private void addStoreButton() {
        JButton storeButton = makeButton("src/main/ui/Images/StoreButton.png");
        storeButton.setActionCommand("Store");
    }

    private void addQuitButton() {
        JButton quitButton = makeButton("src/main/ui/Images/QuitButton.png");
        quitButton.setActionCommand("Quit");
    }

    //EFFECTS: makes a button and places it on the frame
    private JButton makeButton(String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        //button.setBorderPainted(false);
        button.addActionListener(this);
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("My garden")) {
            new MyGardenPage(gardenApp);
        }
        if (e.getActionCommand().equals("Store")) {
            new StorePage(gardenApp);
        }
        if (e.getActionCommand().equals("Quit")) {
            new SaveProgressPage(gardenApp);
        }

    }
}
