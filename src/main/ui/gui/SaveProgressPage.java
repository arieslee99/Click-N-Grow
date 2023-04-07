package ui.gui;

import model.EventLog;
import ui.GardenApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


//Represent the quit page
public class SaveProgressPage extends WindowBasics implements ActionListener {
    private JFrame jframe = new JFrame();
    private GardenApp gardenApp;

    //EFFECTS: constructs a save progress page
    public SaveProgressPage(GardenApp gardenApp) {
        this.gardenApp = gardenApp;
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        makeWindow(jframe, panel, constraints);
        //jframe.addWindowListener(this);
        makeButton("Save", "src/main/ui/Images/Buttons/SaveButton.png", true);
        makeButton("Quit","src/main/ui/Images/Buttons/QuitButton.png", true);
        makeButton("Back","src/main/ui/Images/Buttons/BackButton.png", false);
    }

    //EFFECTS: based on the action event, carry out corresponding action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new HomePage(gardenApp);
        }

        if (e.getActionCommand().equals("Save")) {
            gardenApp.saveProgress();
            JOptionPane.showMessageDialog(jframe, "Your progress is saved!");
            windowClosing(new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
        } else if (e.getActionCommand().equals("Quit")) {
            windowClosing(new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
        }
    }
}
