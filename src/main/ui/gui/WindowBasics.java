package ui.gui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import static java.awt.GridBagConstraints.REMAINDER;

public abstract class WindowBasics implements ActionListener, WindowListener {
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
        jframe.addWindowListener(this);
        jframe.setVisible(true);
    }

    //EFFECTS: prints event log from current session
    protected void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
        }
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

    //MODIFIES: this
    //EFFECTS: makes the back button and places it on the screen
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

    //EFFECTS: based on the action event, carry out corresponding action
    public abstract void actionPerformed(ActionEvent e);

    //EFFECTS: does nothing
    @Override
    public void windowOpened(WindowEvent e) {}

    //EFFECTS: prints out the event log when window is closing
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    //EFFECTS: does nothing
    @Override
    public void windowClosed(WindowEvent e) {}

    //EFFECTS: does nothing
    @Override
    public void windowIconified(WindowEvent e) {}

    //EFFECTS: does nothing
    @Override
    public void windowDeiconified(WindowEvent e) {}

    //EFFECTS: does nothing
    @Override
    public void windowActivated(WindowEvent e) {}

    //EFFECTS: does nothing
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
