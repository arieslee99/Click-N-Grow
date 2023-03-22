package ui;

import model.Garden;

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

    //EFFECTS: constructs a create your own acconut page
    public CreateAccountPage() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        addFenceImg();
        promptGardenName();
        addNextButton();
        addBackButton();

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
        JButton nextButton = new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/NextButton.png"))));
        nextButton.setActionCommand("Next");
        nextButton.addActionListener(this);
        panel.add(nextButton, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    //EFFECTS: adds button that goes back to previous page
    private void addBackButton() {
        JButton backButton = new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/back.png"))));
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);
        panel.add(backButton, constraints);
        jframe.add(panel);
    }

    //EFFECTS: processes command when a button is clicked
    // "Next" will bring user to next page if garden name is filled; else, user stays on the same page
    // "Back" will bring user back to previous page regardless of garden name being blank or not
    @Override
    public void actionPerformed(ActionEvent e) {
        gardenName = textArea.getText();
        if (e.getActionCommand().equals("Next")) {
            if (! gardenName.isBlank()) {
                new HomePage(gardenName);
            } else {
                new CreateAccountPage();
            }
        }
        if (e.getActionCommand().equals("Back")) {
            new LoginPage();
        }
        jframe.setVisible(false);
    }
}
