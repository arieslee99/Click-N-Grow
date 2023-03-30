package ui.gui;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;

//Represents the create your own account page
public class CreateAccountPage extends WindowBasics implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTextArea textArea;
    private String gardenName;
    private GardenApp gardenApp;

    //EFFECTS: constructs a create your own account page
    public CreateAccountPage() {
        makeWindow(jframe, panel, constraints);
        addFenceImg();
        promptGardenName();
        makeButton("Next","src/main/ui/Images/Buttons/NextButton.png", true);
        makeButton("Back","src/main/ui/Images/Buttons/BackButton.png", false);
    }

    //MODIFIES: this
    //EFFECTS: adds image of fences to frame
    private void addFenceImg() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/FencesImage.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: asks and let users decide what garden name they want
    private void promptGardenName() {
        JLabel question = new JLabel("What is your garden's name?");
        question.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(question, constraints);

        textArea = new JTextArea();
        textArea.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        textArea.setBackground(BACKGROUND);
        panel.add(textArea, constraints);
        jframe.add(panel);
    }

    //EFFECTS: processes command when a button is clicked
    // "Next" will bring user to next page if garden name is filled; else, user stays on the same page
    // "Back" will bring user back to previous page regardless of garden name being blank or not
    @Override
    public void actionPerformed(ActionEvent e) {
        gardenName = textArea.getText();
        jframe.setVisible(false);

        if (e.getActionCommand().equals("Next")) {
            if (!gardenName.isEmpty()) {
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

    //EFFECTS: initializes a new garden, wallet and inventory
    public void initializeNewAccount() {
        this.gardenApp = new GardenApp();
        this.gardenApp.instantiateGarden(gardenName);
    }
}
