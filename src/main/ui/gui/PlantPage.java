package ui.gui;

import model.Garden;
import model.Inventory;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.*;

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
        addBackButton();
        printCounts(gardenApp.getGarden().getGarden().get(position));
        addMaintenanceButtons("Water", "src/main/ui/Images/Buttons/Water.png");
        addMaintenanceButtons("Feed", "src/main/ui/Images/Buttons/Feed.png");
        addMaintenanceButtons("Harvest", "src/main/ui/Images/Buttons/Harvest.png");
        addMaintenanceButtons("Uproot", "src/main/ui/Images/Buttons/Uproot.png");
        addPlant();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addPlant() {
        ArrayList<Plant> garden = gardenApp.getGarden().getGarden();
        Plant plant = garden.get(position);
        ImageIcon icon;
        JLabel label;
        if (plant.getLifeStatus().equalsIgnoreCase("Growing!")) {
            icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/Growing.png")));
        } else if (plant.getLifeStatus().equalsIgnoreCase("Dead!")) {
            icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/Dead.png")));
        } else {
            icon = pickPlant(plant);
        }
        label = new JLabel(icon);
        constraints.gridwidth = 1;
        panel.add(label, constraints);
        jframe.add(panel);
    }

    private void printCounts(Plant plant) {
        JLabel waterCount = new JLabel("Water Count: " + plant.getWaterCount());
        waterCount.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        JLabel feedCount = new JLabel("Fertilizer Count: " + plant.getFertilizerCount());
        feedCount.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(waterCount, constraints);
        panel.add(feedCount, constraints);
        jframe.add(panel);
    }

    private ImageIcon pickPlant(Plant plant) {
        String objectName = plant.getPlantName();
        return new ImageIcon(String.valueOf(new File("src/main/ui/Images/Plants/" + objectName + ".png")));
    }

    private void addBackButton() {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File("src/main/ui/Images/Buttons/BackButton.png"))));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        Inventory inventory = gardenApp.getInventory();
        Garden garden = gardenApp.getGarden();
        ArrayList<Plant> plants = garden.getGarden();
        Plant plant = plants.get(position);

        if (e.getActionCommand().equals("Back")) {
            new MyGardenPage(gardenApp);
        }
        if (e.getActionCommand().equals("Water")) {
            plant.waterPlant();
            new PlantPage(gardenApp, String.valueOf(position));
        }
        if (e.getActionCommand().equals("Feed")) {
            plant.feedPlant();
            new PlantPage(gardenApp, String.valueOf(position));
        }
        if (e.getActionCommand().equals("Uproot")) {
            JOptionPane.showMessageDialog(jframe, "You just uprooted a " + plant.getPlantName() + " from your garden!");
            plants.remove(plant);
            new MyGardenPage(gardenApp);
        }
        if (e.getActionCommand().equals("Harvest")) {
            inventory.justAddPlant(plant);
            garden.removePlant(position);
            JOptionPane.showMessageDialog(jframe, "You just harvested a " + plant.getPlantName()
                    + " from your garden!");
            new MyGardenPage(gardenApp);
        }
    }
}
