package ui.GUI;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.CENTER;

public class SaveProgressPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    public SaveProgressPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        addSaveButton();
        addQuitButton();
        addBackButton();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addSaveButton() {
        JButton quitButton = makeButton("src/main/ui/Images/SaveButton.png");
        quitButton.setActionCommand("Save");
    }

    private void addQuitButton() {
        JButton quitButton = makeButton("src/main/ui/Images/QuitButton.png");
        quitButton.setActionCommand("Quit");
    }

    private void addBackButton() {
        JButton quitButton = makeButton("src/main/ui/Images/BackButton.png");
        quitButton.setActionCommand("Back");
        quitButton.setBorderPainted(false);
    }

    //EFFECTS: makes a button and places it on the frame
    private JButton makeButton(String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        //button.setBorderPainted(false);
        button.addActionListener(this);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new HomePage(gardenApp);
        }

        if (e.getActionCommand().equals("Save")) {
            gardenApp.saveProgress();
        } else if (e.getActionCommand().equals("Quit")) {
            jframe.setVisible(false);
            System.exit(0);
        }
    }
}
