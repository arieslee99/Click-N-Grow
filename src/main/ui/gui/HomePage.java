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
    public static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a homepage
    public HomePage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
        addHomeImage();
        displayWelcomeNote(gardenApp.getGardenName());
        makeButton("My garden", "src/main/ui/Images/Buttons/MyGardenButton.png");
        makeButton("Inventory", "src/main/ui/Images/Buttons/InventoryButton.png");
        makeButton("Store", "src/main/ui/Images/Buttons/StoreButton.png");
        makeButton("Quit", "src/main/ui/Images/Buttons/QuitButton.png");
    }

    //EFFECTS: sets up the window of the current screen
    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: add home image to window
    private void addHomeImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/HomeImage.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: welcomes the user to the home page
    private void displayWelcomeNote(String name) {
        JLabel welcome = new JLabel("Welcome to " + name + "'s" + " garden!");
        welcome.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(welcome, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: makes a button and places it on the frame
    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.addActionListener(this);
        button.setActionCommand(objectName);

        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("My garden")) {
            new MyGardenPage(gardenApp);
        } else if (e.getActionCommand().equals("Store")) {
            new StorePage(gardenApp);
        } else if (e.getActionCommand().equals("Quit")) {
            new SaveProgressPage(gardenApp);
        } else {
            new InventoryPage(gardenApp);
        }

    }
}
