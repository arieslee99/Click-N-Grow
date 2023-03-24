package ui.gui;

import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class StorePage extends JFrame implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe = new JFrame();
    private JPanel panel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GardenApp gardenApp;

    public StorePage(GardenApp gardenApp) {
        makeWindow();
        this.gardenApp = gardenApp;
    }

    public void makeWindow() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);

        addStoreImage();
        makeButton("src/main/ui/Images/BackButton.png");
        makeButton("src/main/ui/Images/CactusSeeds.png");
        makeButton("src/main/ui/Images/RoseSeeds.png");
        makeButton("src/main/ui/Images/LavenderSeeds.png");
        makeButton("src/main/ui/Images/ForgetMeNotSeeds.png");
        makeButton("src/main/ui/Images/SunflowerSeeds.png");
        makeButton("src/main/ui/Images/GarlicSeeds.png");
        makeButton("src/main/ui/Images/PotatoSeeds.png");
        makeButton("src/main/ui/Images/CarrotSeeds.png");
        makeButton("src/main/ui/Images/LettuceSeeds.png");
        makeButton("src/main/ui/Images/EggplantSeeds.png");
        addScrollBar();

        jframe.setVisible(true);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addEmptySpace() {
        jframe.add(Box.createVerticalGlue());
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
    private void makeButton(String fileName) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.addActionListener(this);
        button.setActionCommand(fileName);

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
        if (e.getActionCommand().equals("src/main/ui/Images/BackButton.png")) {
            new HomePage(gardenApp);
        } else {
            //stub
        }
    }
}
