package ui.gui;

import model.Inventory;
import model.Plant;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//Represents the sell plant page
public class SellPlantPage extends WindowBasics implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45, 255);
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
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
        makeWindow(jframe, panel, constraints);
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png", false);
        makeButton("Sell", "src/main/ui/Images/Buttons/SellButton.png", false);
        printSalePrice();
        addPlant();
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
            int totalBalance = gardenApp.getWallet().getBalance() + plant.getProfitValue();
            gardenApp.getWallet().setBalance(totalBalance);
            gardenApp.getInventory().removePlant(position);
            JOptionPane.showMessageDialog(jframe, "You just sold a " + plant.getPlantName() + " and earned "
                    + plant.getProfitValue() + "!");
            new InventoryPage(gardenApp);
        }
    }
}
