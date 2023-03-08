package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        try {
            new GardenApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

