package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;


public class GardenApp {

    private Store store = new Store();
    private Inventory inventory = new Inventory();
    private Garden garden = new Garden(null);
    private Wallet wallet = new Wallet();
    private Scanner input = new Scanner(System.in);


    public GardenApp() {
        runGarden();
    }

    private void runGarden() {
        System.out.println("Welcome to your virtual garden!");
        System.out.println();
        initializeGarden();
    }

    private void initializeGarden() {

        System.out.println("What is your garden's name?");
        String name = input.nextLine();
        garden.setGardenName(name);
        System.out.println("Welcome to " + name + "'s" + " garden!");
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("What would you like to do today?");
        System.out.println("\nSelect from:");
        System.out.println("\tG -> Go to garden");
        System.out.println("\tS -> Go to store");
        System.out.println("\tI -> See my inventory");
        System.out.println("\tW -> Check my wallet");
        System.out.println("\tQ -> Quit");

        String action = input.nextLine();

        processCommand(action);
    }

    private void processCommand(String action) {
        switch(action.toUpperCase()) {
            case ("G"):
                seeMyGarden();
                break;
            case("S"):
                visitStore();
                break;
            case("I"):
                seeMyInventory();
                break;
            case("W"):
                checkBalance();
                break;
            case("Q"):
                System.out.print("Come back soon!");
                break;
            default:
                displayMenu();
        }
    }

    private void seeMyGarden() {
        if (garden.getSize() == 0) {
            System.out.println("Your garden is empty, yet your soil is fertile.");
        }

        for(Plant plant: garden.getGarden()) {
            System.out.println(plant.getPlantName() + ", WaterCount: " + plant.getWaterCount() +
            ", FeedCount: " + plant.getFertilizerCount() + ", Status: " + plant.getLifeStatus());
        }

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tW -> Water plants");
        System.out.println("\tF -> Feed plants");
        System.out.println("\tH -> Harvest plants");
        System.out.println("\tB -> Back to homepage");

        String action = input.nextLine();
        switch(action.toUpperCase()) {
            case("B"):
                displayMenu();
            case("W"):
                waterPlants();
            case("F"):
                feedPlants();
            case("H"):
                harvest();
            default:
                seeMyGarden();
        }
    }

    private void waterPlants() {
        System.out.println("Which plant would you like to water?");
        for(Plant plant: garden.getGarden()) {
            if (plant.getPlantName().equalsIgnoreCase(input.nextLine())) {
                plant.waterPlant();
                System.out.println("Your" + plant.getPlantName() + " is watered!");
                System.out.println("It's current water count is: " + plant.getWaterCount());
            }
        }
        seeMyGarden();
    }

    private void feedPlants() {
        System.out.println("Which plant would you like to feed?");
        for(Plant plant: garden.getGarden()) {
            if (plant.getPlantName().equalsIgnoreCase(input.nextLine())) {
                plant.feedPlant();
                System.out.println("Your" + plant.getPlantName() + " is fed!");
                System.out.println("It's current feed count is: " + plant.getFertilizerCount());
            }
        }
        seeMyGarden();
    }

    private void harvest() {
        for(Plant plant: garden.getGarden()) {
            if(plant.getLifeStatus().equals("Ripe!")) {
                inventory.addPlant(garden, plant);
                break;
            }
        }

        System.out.println("Your inventory count: " + inventory.getSize());
        System.out.println("Your garden size: " + garden.getSize());
        displayMenu();
    }



    private void visitStore() {
        System.out.println("Welcome to Cleo's!");
        System.out.println("\nWhich seed catalogue would you like to browse?");
        System.out.println("\tA -> All seeds");
        System.out.println("\tV -> Veggies");
        System.out.println("\tF -> Flowers");

        String action = input.nextLine();

        switch(action.toUpperCase()) {
            case("A"):
                displaySeeds(store.getStore());
                break;
            case("V"):
                displaySeeds(store.getVegetables());
                break;
            case("F"):
                displaySeeds(store.getFlowers());
                break;
            default:
                visitStore();
        }
    }

    private void displaySeeds(ArrayList<Plant> plants) {
        for(Plant plant: plants) {
            System.out.println(plant.getPlantName() + ", WaterCount: " + plant.getWaterCount() +
                    ", FeedCount: " + plant.getFertilizerCount() + ", Price: " + plant.getPrice());
        }

        System.out.println("\nWould you like to buy something?");
        System.out.println("\tY -> Yes!");
        System.out.println("\tN -> Get me out of here!");

        String action = input.nextLine();

        switch(action.toUpperCase()) {
            case("Y"):
                System.out.println("Which seed would you like to buy?");
                buySeed(input.nextLine(), plants);
                break;
            case("N"):
                displayMenu();
        }
    }

    private void buySeed(String response, ArrayList<Plant> plants) {
        for(Plant plant: plants) {
            if (plant.getPlantName().equalsIgnoreCase(response)) {
                if (wallet.getBalance() >= plant.getPrice()) {
                    garden.addPlant(plant);
                    wallet.decreaseBalance(plant.getPrice());
                    System.out.println("You added a(n) " + plant.getPlantName() + " to your garden!");
                    System.out.println("Your current balance is: " + wallet.getBalance());
                    displayMenu();
                } else {
                    System.out.println("You don't have enough money!");
                    displayMenu();
                }
            }
        }
    }

    private void seeMyInventory(){
        if (inventory.getSize() == 0) {
            System.out.println("It's empty! Go sow some seeds!");
            displayMenu();
        }

        for(Plant plant: inventory.getInventory()) {
            System.out.println(plant.getPlantName() + " value: " + plant.getProfitValue());
        }

        System.out.println("\nWould you like to sell?");
        System.out.println("\tY -> Yes! Lets make some money!");
        System.out.println("\tN -> No! Stay away from my plants!");

        String action = input.nextLine();
        if ("Y".equalsIgnoreCase(action)) {
            sellPlants();
        } else if("N".equalsIgnoreCase(action)) {
            displayMenu();
        } else {
            seeMyInventory();
        }

    }

    private void sellPlants() {
        System.out.println("What would you like to sell?");
        for(Plant plant: inventory.getInventory()) {
            if (plant.getPlantName().equalsIgnoreCase(input.nextLine())) {
                inventory.removePlant(plant);
                wallet.increaseBalance(plant.getProfitValue());
                System.out.println("You've earned: " + plant.getProfitValue());
                displayMenu();
            }
        }
        System.out.println("You don't have that to sell!");
        displayMenu();
    }

    private void checkBalance() {
        System.out.println("Your balance is: " + wallet.getBalance());
        System.out.println();
        displayMenu();
    }

}
