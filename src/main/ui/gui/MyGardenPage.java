package ui.gui;

import model.Garden;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static javax.swing.SwingConstants.*;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.NORTH;

//Represents the garden page
public class MyGardenPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a garden page
    public MyGardenPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
        makeBackButton();
        addGardenImage();
        seeGarden();
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
    //EFFECTS: adds an empty vertical space to window
    private void addEmptySpace() {
        jframe.add(Box.createVerticalGlue());
    }

    //MODIFIES: this
    //EFFECTS: adds garden image to window
    private void addGardenImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Garden.png")));
        JLabel label = new JLabel(icon);
        constraints.gridheight = NORTH;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: makes and adds back button on window
    private void makeBackButton() {
        File file = new File("src/main/ui/Images/Buttons/BackButton.png");
        JButton button = new JButton(new ImageIcon(String.valueOf(file)));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(this);
        button.setActionCommand("Back");
        constraints.gridwidth = REMAINDER;
        constraints.weighty = 1;
        constraints.weightx = 1;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: makes buttons for each plant in the garden and places it on the window
    public void seeGarden() {
        Garden myGarden = gardenApp.getGarden();
        JButton button;
        List<Plant> plants = myGarden.getGarden();
        for (int i = 0; i < plants.size(); i++) {
            button = new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/Buttons/Plant.png"))));
            button.setText(plants.get(i).getPlantName());
            button.setVerticalTextPosition(NORTH);
            button.setHorizontalTextPosition(CENTER);
            button.setBackground(BACKGROUND);
            button.setBorderPainted(false);
            button.setOpaque(true);

            button.addActionListener(this);
            button.setActionCommand(String.valueOf(i));

            constraints.gridwidth = LEADING;
            panel.add(button,constraints);
            jframe.add(panel);
        }
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("Back")) {
            new HomePage(gardenApp);
        } else {
            new PlantPage(gardenApp, e.getActionCommand());
        }
    }
}
