package ui;


import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new WindowPane();

        try {
            new GardenApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

