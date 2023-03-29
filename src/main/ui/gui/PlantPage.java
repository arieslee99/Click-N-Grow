package ui.gui;

import model.Garden;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.*;

public class PlantPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;
    private int position;
    private Garden garden;
    private Plant plant;

    //EFFECTS: constructs a plant page
    public PlantPage(GardenApp gardenApp,String position) {
        this.gardenApp = gardenApp;
        this.garden = gardenApp.getGarden();
        this.position = Integer.parseInt(position);
        this.plant = garden.getPlant(this.position);
        makeWindow();
        addBackButton();
        printCounts(gardenApp.getGarden().getGarden().get(this.position));
        addMaintenanceButtons("Water", "src/main/ui/Images/Buttons/Water.png");
        addMaintenanceButtons("Feed", "src/main/ui/Images/Buttons/Feed.png");
        addMaintenanceButtons("Harvest", "src/main/ui/Images/Buttons/Harvest.png");
        addMaintenanceButtons("Uproot", "src/main/ui/Images/Buttons/Uproot.png");
        addPlant();
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
    //EFFECTS: adds plant to page
    private void addPlant() {
        ImageIcon icon;
        JLabel label;
        if (plant.getUpdatedLifeStatus().equalsIgnoreCase("Growing!")) {
            icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/Growing.png")));
        } else if (plant.getUpdatedLifeStatus().equalsIgnoreCase("Dead!")) {
            icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/Dead.png")));
        } else {
            icon = pickPlant();
        }
        label = new JLabel(icon);
        constraints.gridwidth = 1;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: prints out the fertilizer and water counts on screen
    private void printCounts(Plant plant) {
        JLabel waterCount = new JLabel("Water Count: " + plant.getWaterCount());
        waterCount.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        JLabel feedCount = new JLabel("Fertilizer Count: " + plant.getFertilizerCount());
        feedCount.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(waterCount, constraints);
        panel.add(feedCount, constraints);
        jframe.add(panel);
    }

    //EFFECTS: returns the image of the plant
    private ImageIcon pickPlant() {
        String objectName = plant.getPlantName();
        return new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/" + objectName + ".png")));
    }

    //MODIFIES: this
    //EFFECTS: adds back button on the window
    private void addBackButton() {
        JButton button = new JButton(new ImageIcon(String.valueOf(
                new File("src/main/ui/Images/Buttons/BackButton.png"))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.addActionListener(this);
        button.setActionCommand("Back");

        constraints.gridwidth = REMAINDER;
        constraints.gridheight = NORTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        panel.add(button, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds feed, water, harvest and uproot buttons to the screen
    private void addMaintenanceButtons(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        jframe.add(panel);

    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        String action = e.getActionCommand();
        if (action.equals("Back")) {
            new MyGardenPage(gardenApp);
        }
        if (action.equals("Uproot") || action.equals("Harvest")) {
            if ("Uproot".equals(action)) {
                JOptionPane.showMessageDialog(jframe, "You uprooted a " + plant.getPlantName() + " from your garden!");
            } else {
                gardenApp.getInventory().justAddPlant(plant);
                JOptionPane.showMessageDialog(jframe, "You harvested a " + plant.getPlantName() + " from your garden!");
            }
            garden.removePlant(position);
            new MyGardenPage(gardenApp);
        }

        if (action.equals("Feed") || action.equals("Water")) {
            if ("Feed".equals(action)) {
                plant.feedPlant();
            } else {
                plant.waterPlant();
            }
            new PlantPage(gardenApp, String.valueOf(position));
        }
    }
}
