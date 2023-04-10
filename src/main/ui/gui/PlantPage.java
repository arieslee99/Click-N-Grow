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

//Represents a plant page
public class PlantPage extends WindowBasics implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
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
        makeWindow(jframe, panel, constraints);
        makeBackButton();
        printCounts(gardenApp.getGarden().getGarden().get(this.position));
        addMaintenanceButtons("Water", "src/main/ui/Images/Buttons/Water.png");
        addMaintenanceButtons("Feed", "src/main/ui/Images/Buttons/Feed.png");
        addMaintenanceButtons("Harvest", "src/main/ui/Images/Buttons/Harvest.png");
        addMaintenanceButtons("Uproot", "src/main/ui/Images/Buttons/Uproot.png");
        addPlant();
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
        if (action.equals("Uproot") || action.equals("Harvest")) {
            uprootAndHarvest(action);
        } else if (action.equals("Feed") || action.equals("Water")) {
            feedAndWater(action);
        } else {
            new MyGardenPage(gardenApp);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove from garden or harvest plant into inventory
    private void uprootAndHarvest(String action) {
        if (action.equals("Uproot")) {
            JOptionPane.showMessageDialog(jframe, "You uprooted a " + plant.getPlantName() + "!");
            garden.removePlant(position);
            new MyGardenPage(gardenApp);
        } else {
            if (plant.getFertilizerCount() == 0 && plant.getWaterCount() == 0) {
                gardenApp.getInventory().justAddPlant(plant);
                garden.removePlant(position);
                JOptionPane.showMessageDialog(jframe, "You harvested a " + plant.getPlantName() + "!");
                new MyGardenPage(gardenApp);
            } else {
                JOptionPane.showMessageDialog(jframe, "Your plant is not ripe enough to be harvest!");
                new PlantPage(gardenApp, String.valueOf(position));
            }
        }
    }

    //EFFECTS: feeds or waters plant
    private void feedAndWater(String action) {
        if (action.equals("Water")) {
            plant.waterPlant();
        } else {
            plant.feedPlant();
        }
        new PlantPage(gardenApp, String.valueOf(position));
    }
}

