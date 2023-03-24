package ui.gui;

import model.Garden;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.EAST;


public class MyGardenPage {
    private static final Color BACKGROUND = new Color(45, 133, 18, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    public MyGardenPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        seeGarden();
        addScrollBar();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void seeGarden() {
        List<JLabel> myPlants = new ArrayList<>();
        Garden myGarden = gardenApp.getGarden();
        List<Plant> plants = myGarden.getGarden();
        for (Plant plant: plants) {
//            JLabel thisPlant = new JLabel(plant.getPlantName() + ": " + "Water count: " + plant.getWaterCount() +
//                    "Fertilizer count: " + plant.getFertilizerCount() + "Life status: " + plant.getLifeStatus());

            JLabel plantName = new JLabel("\n" + plant.getPlantName());
            JLabel waterCount = new JLabel("\t Water Count: " + plant.getWaterCount());
            JLabel feedCount = new JLabel("\t Fertilizer Count: " + plant.getFertilizerCount());
            JLabel plantStatus = new JLabel("\t Life Status: " + plant.getLifeStatus());

            plantName.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            waterCount.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            feedCount.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            plantStatus.setFont(new Font("Comic Sans", Font.PLAIN, 15));

            constraints.weighty = 1;
            constraints.weightx = 1;
            constraints.gridwidth = REMAINDER;

            panel.add(plantName, constraints);
            panel.add(waterCount, constraints);
            panel.add(feedCount, constraints);
            panel.add(plantStatus, constraints);
            jframe.add(panel);
        }
    }

    private void addScrollBar() {
        JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL, 30, 20, 0, 100);
        scrollBar.setBlockIncrement(1);

        constraints.anchor = EAST;
        jframe.getContentPane().add(scrollBar, BorderLayout.EAST);

    }
}
