package ui.gui;

import model.Plant;
import ui.GardenApp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.*;

//Represents the inventory page
public class InventoryPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs the inventory page
    public InventoryPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
        addBackButton();
        addInventoryImage();
        showInventory();
    }

    //EFFECTS: makes the window
    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: adds inventory image to window
    private void addInventoryImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Inventory.png")));
        JLabel label = new JLabel(icon);

        constraints.gridheight = NORTH;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds back button to window
    private void addBackButton() {
        JButton button = new JButton(new ImageIcon(String.valueOf(
                new File("src/main/ui/Images/Buttons/BackButton.png"))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);
        button.setActionCommand("Back");

        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    //EFFECTS: makes a button and places it on the frame
    private void makeButton(int position, String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setText(objectName);
        button.setVerticalTextPosition(NORTH);
        button.setHorizontalTextPosition(CENTER);
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addActionListener(this);
        button.setActionCommand(String.valueOf(position));

        constraints.gridwidth = LEADING;
        panel.add(button, constraints);
        jframe.add(panel);
    }

    //EFFECTS: makes buttons for each plant in the inventory
    private void showInventory() {
        List<Plant> inventory = gardenApp.getInventory().getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            String plantName = inventory.get(i).getPlantName();
            makeButton(i, plantName, "src/main/ui/Images/Inventory/" + plantName + "Button.png");
        }
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("Back")) {
            new HomePage(gardenApp);
        } else {
            new SellPlantPage(gardenApp, e.getActionCommand());
        }
    }
}
