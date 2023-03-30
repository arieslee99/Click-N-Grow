package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.REMAINDER;

public abstract class WindowBasics implements ActionListener {
    private static final Color BACKGROUND = new Color(229, 180, 45);
    private JFrame jframe;
    private JPanel panel;
    private GridBagConstraints constraints;


    //EFFECTS: makes the frame and panel of current page
    protected void makeWindow(JFrame jframe, JPanel panel, GridBagConstraints constraints) {
        this.jframe = jframe;
        this.panel = panel;
        this.constraints = constraints;
        panel.setBackground(BACKGROUND);
        jframe.getContentPane().setBackground(BACKGROUND);
        jframe.setSize(500, 800);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: adds vertical empty space on window
    protected void addEmptySpace() {
        jframe.add(Box.createVerticalGlue());
    }

    //EFFECTS: makes a button
    protected void makeButton(String objectName, String fileName, boolean border) {
        JButton button = new JButton(new ImageIcon(String.valueOf(new File(fileName))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(border);

        button.addActionListener(this);
        button.setActionCommand(objectName);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    protected void makeBackButton() {
        JButton button = new JButton(new ImageIcon(String.valueOf(
                new File("src/main/ui/Images/Buttons/BackButton.png"))));
        button.setBackground(BACKGROUND);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);
        button.setActionCommand("Back");

        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = REMAINDER;
        panel.add(button, constraints);
        constraints.weighty = 1;
        jframe.add(panel);
    }

    public abstract void actionPerformed(ActionEvent e);
}
