package ui.gui;

import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.SwingConstants.LEADING;

public class PlantPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;
    private int position;

    public PlantPage(GardenApp gardenApp,String position) {
        this.gardenApp = gardenApp;
        this.position = Integer.parseInt(position);
        makeWindow();
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        makeButton("Back", "src/main/ui/Images/BackButton.png");
        makeButton("Water", "src/main/ui/Images/Water.png");
        makeButton("Feed", "src/main/ui/Images/Feed.png");

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: makes a button and places it on the frame
    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.gridwidth = LEADING;
        constraints.weighty = 1;
        constraints.weightx = 1;
        panel.add(button, constraints);
        jframe.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        ArrayList<Plant> plants = gardenApp.getGarden().getGarden();
        Plant plant = plants.get(position);

        if (e.getActionCommand().equals("Back")) {
            new MyGardenPage(gardenApp);
        }
        if (e.getActionCommand().equals("Water")) {
            plant.waterPlant();
        }
        if (e.getActionCommand().equals("Feed")) {
            plant.feedPlant();
        }
    }
}
