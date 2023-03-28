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
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.SwingConstants.*;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.NORTH;


public class MyGardenPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
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
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png");
        seeGarden();
        addScrollBar();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addEmptySpace() {
        jframe.add(Box.createVerticalGlue());
    }

    //EFFECTS: makes a button and places it on the frame
    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.gridwidth = REMAINDER;
        constraints.weighty = 1;
        constraints.weightx = 1;
        panel.add(button, constraints);
        jframe.add(panel);
        addEmptySpace();
    }

    public void seeGarden() {
        Garden myGarden = gardenApp.getGarden();
        List<Plant> plants = myGarden.getGarden();
        for (int i = 0; i < plants.size(); i++) {
            makePlantButton(i, plants.get(i).getPlantName(), "src/main/ui/Images/Buttons/Plant.png");
        }
    }

    private void makePlantButton(int position, String plantName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setText(plantName);
        button.setVerticalTextPosition(NORTH);
        button.setHorizontalTextPosition(CENTER);
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(this);
        button.setActionCommand(String.valueOf(position));

        constraints.gridwidth = LEADING;
        panel.add(button,constraints);
        jframe.add(panel);
    }

    private void addScrollBar() {
        JScrollPane scrollPane =
                new JScrollPane(panel, VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jframe.add(scrollPane);
    }

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
