package ui;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new LoginPage();

        try {
            new GardenApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

