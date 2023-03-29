package ui.gui;

import model.Inventory;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.SwingConstants.NORTH;

//Represents the sell plant page
public class SellPlantPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;
    private int position;
    private Plant plant;

    //EFFECTS: constructs a sell plant page
    public SellPlantPage(GardenApp gardenApp, String position) {
        this.gardenApp = gardenApp;
        Inventory inventory = this.gardenApp.getInventory();
        this.position = Integer.parseInt(position);
        this.plant = inventory.getPlant(this.position);
        makeWindow();
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png");
        makeButton("Sell", "src/main/ui/Images/Buttons/SellButton.png");
        printSalePrice();
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
    //EFFECTS: makes button and places it on the window
    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.gridwidth = REMAINDER;
        constraints.gridheight = NORTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        panel.add(button, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: prints out the sale price on the window
    private void printSalePrice() {
        JLabel salePrice = new JLabel("Sale Price: " + plant.getProfitValue() + " coins");
        salePrice.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(salePrice, constraints);
        jframe.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds plants to the window
    private void addPlant() {
        ImageIcon icon = new ImageIcon(String.valueOf(
                new File("src/main/ui/Images/Plants/" + plant.getPlantName() + ".png")));
        JLabel label = new JLabel(icon);
        panel.add(label, constraints);
        jframe.add(panel);
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("Back")) {
            new InventoryPage(gardenApp);
        }
        if (e.getActionCommand().equals("Sell")) {
            gardenApp.processSale(position, plant);
            JOptionPane.showMessageDialog(jframe, "You just sold a " + plant.getPlantName() + " and earned "
                    + plant.getProfitValue() + "!");
            new InventoryPage(gardenApp);
        }
    }
}
