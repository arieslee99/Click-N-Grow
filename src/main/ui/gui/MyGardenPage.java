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

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.SwingConstants.*;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.NORTH;

//Represents the garden page
public class MyGardenPage extends WindowBasics implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a garden page
    public MyGardenPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow(jframe, panel, constraints);
        makeBackButton();
        addGardenImage();
        seeGarden();
        addScrollBar();
    }

    //MODIFIES: this
    //EFFECTS: adds garden image to window
    private void addGardenImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/Garden.png")));
        JLabel label = new JLabel(icon);
        constraints.gridheight = NORTH;
        constraints.gridwidth = REMAINDER;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(label, constraints);
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
            panel.add(button, constraints);
            jframe.add(panel);
        }
    }

    private void addScrollBar() {
        JScrollPane scrollPane =
                new JScrollPane(panel, VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jframe.add(scrollPane);
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
