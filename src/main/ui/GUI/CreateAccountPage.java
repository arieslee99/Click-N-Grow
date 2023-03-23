package ui.GUI;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;

//Represents the create your own account page
public class CreateAccountPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTextArea textArea;
    private String gardenName;
    private GardenApp gardenApp;

    //EFFECTS: constructs a create your own acconut page
    public CreateAccountPage() {
        makeWindow();
        addFenceImg();
        promptGardenName();
        addNextButton();
        addBackButton();
    }

    //EFFECTS: sets up the window of the current screen
    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //EFFECTS: adds image of fences to frame
    private void addFenceImg() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Fences.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //EFFECTS: asks and let users decide what garden name they want
    private void promptGardenName() {
        JLabel question = new JLabel("What is your garden's name?");
        question.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
        panel.add(question, constraints);

        textArea = new JTextArea();
        textArea.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
        textArea.setBackground(BACKGROUND);
        panel.add(textArea, constraints);
        jframe.add(panel);
    }

    //EFFECTS: adds button that proceeds to the next page
    private void addNextButton() {
        JButton nextButton = makeButton("src/main/ui/Images/NextButton.png");
        nextButton.setActionCommand("Next");
    }

    //EFFECTS: adds button that goes back to previous page
    private void addBackButton() {
        JButton backButton = makeButton("src/main/ui/Images/BackButton.png");
        backButton.setActionCommand("Back");
    }

    //EFFECTS: makes a button and places it on the frame
    private JButton makeButton(String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
        return button;
    }

    //EFFECTS: processes command when a button is clicked
    // "Next" will bring user to next page if garden name is filled; else, user stays on the same page
    // "Back" will bring user back to previous page regardless of garden name being blank or not
    @Override
    public void actionPerformed(ActionEvent e) {
        gardenName = textArea.getText();
        jframe.setVisible(false);

        if (e.getActionCommand().equals("Next")) {
            if (! gardenName.isBlank()) {
                initializeNewAccount();
                new HomePage(gardenApp);
            } else {
                new CreateAccountPage();
            }
        }
        if (e.getActionCommand().equals("Back")) {
            new LoginPage();
        }
    }

    public void initializeNewAccount() {
        gardenApp = new GardenApp();
        gardenApp.instantiateGarden(gardenName);
    }
}
