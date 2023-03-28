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

public class SellPlantPage implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;
    private int position;

    public SellPlantPage(GardenApp gardenApp, String position) {
        this.gardenApp = gardenApp;
        this.position = Integer.valueOf(position);
        makeWindow();
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png");
        makeButton("Sell", "src/main/ui/Images/Buttons/Sell.png");
        printSalePrice();
        addPlant();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
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

    private void printSalePrice() {
        Inventory inventory = gardenApp.getInventory();
        Plant plant = inventory.getPlant(position);
        JLabel salePrice = new JLabel("Sale Price: " + plant.getProfitValue() + " coins");
        salePrice.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(salePrice, constraints);
        jframe.add(panel);
    }

    private void addPlant() {
        Inventory inventory = gardenApp.getInventory();
        Plant plant = inventory.getPlant(position);
        ImageIcon icon = new ImageIcon(String.valueOf
                (new File("src/main/ui/Images/Plants/" + plant.getPlantName() + ".png")));
        JLabel label = new JLabel(icon);
        panel.add(label, constraints);
        jframe.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jframe.setVisible(false);
        if (e.getActionCommand().equals("Back")) {
            new InventoryPage(gardenApp);
        }
    }
}
