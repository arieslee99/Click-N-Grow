package ui.gui;

import model.Plant;
import model.Store;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class StorePage extends JFrame implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    public StorePage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        makeWindow();
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);

        addStoreImage();
        addBalance();
        makeButton("Back", "src/main/ui/Images/Buttons/BackButton.png");
        makeButton("Cactus", "src/main/ui/Images/Seeds/CactusSeeds.png");
        makeButton("Rose", "src/main/ui/Images/Seeds/RoseSeeds.png");
        makeButton("Lavender", "src/main/ui/Images/Seeds/LavenderSeeds.png");
        makeButton("Forget Me Not", "src/main/ui/Images/Seeds/ForgetMeNotSeeds.png");
        makeButton("Sunflower", "src/main/ui/Images/Seeds/SunflowerSeeds.png");
        makeButton("Garlic", "src/main/ui/Images/Seeds/GarlicSeeds.png");
        makeButton("Potato", "src/main/ui/Images/Seeds/PotatoSeeds.png");
        makeButton("Carrot", "src/main/ui/Images/Seeds/CarrotSeeds.png");
        makeButton("Lettuce", "src/main/ui/Images/Seeds/LettuceSeeds.png");
        makeButton("Eggplant", "src/main/ui/Images/Seeds/EggplantSeeds.png");
        addScrollBar();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addEmptySpace() {
        jframe.add(Box.createVerticalGlue());
    }

    public void addBalance() {
        JLabel currentBalance = new JLabel("Balance: " + gardenApp.getWallet().getBalance());
        currentBalance.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        panel.add(currentBalance, constraints);
        jframe.add(panel);
        addEmptySpace();
    }

    public void addStoreImage() {
        ImageIcon icon = new ImageIcon(String.valueOf(new File("src/main/ui/Images/StoreImage.png")));
        JLabel label = new JLabel(icon, JLabel.CENTER);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(label, constraints);
        jframe.add(panel);
        addEmptySpace();
    }

    //EFFECTS: makes a button and places it on the frame
    private void makeButton(String objectName, String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(this);
        button.setActionCommand(objectName);

        panel.add(button, constraints);
        jframe.add(panel);
        addEmptySpace();
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
