package ui.gui;

import model.Plant;
import model.Store;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.awt.GridBagConstraints.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

//Represents the store page
public class StorePage extends WindowBasics implements ActionListener {
    private JFrame jframe = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    //EFFECTS: constructs a store page
    public StorePage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow(jframe, panel, constraints);
        addStoreImage();
        addBalance();
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png", false);
        makeButton("Cactus", "src/main/ui/Images/Seeds/CactusSeeds.png", false);
        makeButton("Rose", "src/main/ui/Images/Seeds/RoseSeeds.png", false);
        makeButton("Lavender", "src/main/ui/Images/Seeds/LavenderSeeds.png", false);
        makeButton("Forget Me Not", "src/main/ui/Images/Seeds/ForgetMeNotSeeds.png", false);
        makeButton("Sunflower", "src/main/ui/Images/Seeds/SunflowerSeeds.png", false);
        makeButton("Garlic", "src/main/ui/Images/Seeds/GarlicSeeds.png", false);
        makeButton("Potato", "src/main/ui/Images/Seeds/PotatoSeeds.png", false);
        makeButton("Carrot", "src/main/ui/Images/Seeds/CarrotSeeds.png", false);
        makeButton("Lettuce", "src/main/ui/Images/Seeds/LettuceSeeds.png", false);
        makeButton("Eggplant", "src/main/ui/Images/Seeds/EggplantSeeds.png", false);
        addScrollBar();
    }

    //MODIFIES: this
    //EFFECTS: prints out current balance
    private void addBalance() {
        JLabel currentBalance = new JLabel("Balance: " + gardenApp.getWallet().getBalance());
        currentBalance.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(currentBalance, constraints);
        jframe.add(panel);
        addEmptySpace();
    }

    //MODIFIES: this
    //EFFECTS: adds image of store on screen
    private void addStoreImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/StoreImage.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
        addEmptySpace();
    }

    //MODIFIES: this
    //EFFECTS: adds scroll bar to screen
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
            Store store = gardenApp.getStoreFront();
            Plant plant = store.getPlant(e.getActionCommand());
            if (gardenApp.checkForMoney(plant)) {
                gardenApp.buySeed(plant);
                JOptionPane.showMessageDialog(jframe, "You just added a " + plant.getPlantName() + " to your garden!");
                new HomePage(gardenApp);
            } else {
                JOptionPane.showMessageDialog(jframe, "You don't have enough money!");
                new StorePage(gardenApp);
            }

        }
    }
}
