package ui.gui;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.REMAINDER;

//Represent the quit page
public class SaveProgressPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a save progress page
    public SaveProgressPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
        makeButton("Save", "src/main/ui/Images/Buttons/SaveButton.png", true);
        makeButton("Quit","src/main/ui/Images/Buttons/QuitButton.png", true);
        makeButton("Back","src/main/ui/Images/Buttons/BackButton.png", false);
    }

    //EFFECTS: makes the window
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
    //EFFECTS: makes a button and places it on the frame
    private void makeButton(String objectName, String fileName, boolean border) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(border);

        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new HomePage(gardenApp);
        }

        if (e.getActionCommand().equals("Save")) {
            gardenApp.saveProgress();
            JOptionPane.showMessageDialog(jframe, "Your progress is saved!");
            System.exit(0);
        } else if (e.getActionCommand().equals("Quit")) {
            jframe.setVisible(false);
            System.exit(0);
        }
    }
}
