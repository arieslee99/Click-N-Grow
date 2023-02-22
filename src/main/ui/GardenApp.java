package ui;

import model.*;
import model.SeedCatagloue.*;

import java.util.ArrayList;
import java.util.Scanner;


public class GardenApp {

    private final Store store = new Store();
    private final Inventory inventory = new Inventory();
    private final Garden garden = new Garden(null);
    private final Wallet wallet = new Wallet();
    private final Scanner input = new Scanner(System.in);


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
        System.out.println();
        System.out.println("What would you like to do today?");
        System.out.println("\nSelect from:");
        System.out.println("\tG -> Go to garden");
        System.out.println("\tS -> Go to store");
        System.out.println("\tI -> See my inventory");
        System.out.println("\tW -> Check my wallet");
        System.out.println("\tP -> How To Play");
        System.out.println("\tQ -> Quit");

        String action = input.nextLine();

        processCommand(action);
    }

    private void processCommand(String action) {
        switch (action.toUpperCase()) {
            case ("G"):
                seeMyGarden();
                break;
            case ("S"):
                visitStore();
                break;
            case ("I"):
                seeMyInventory();
                break;
            case ("W"):
                checkBalance();
                break;
            case ("P"):
                getInstructions();
            case ("Q"):
                System.out.print("Come back soon!");
                break;
            default:
                displayMenu();
                break;
        }
    }

    private void seeMyGarden() {
        if (garden.getSize() == 0) {
            System.out.println("Your garden is empty, yet your soil is fertile.");
        }

        for (Plant plant : garden.getGarden()) {
            System.out.println(plant.getPlantName() + ", WaterCount: " + plant.getWaterCount() +
                    ", FeedCount: " + plant.getFertilizerCount() + ", Status: " + plant.getLifeStatus());
        }

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tW -> Water plants");
        System.out.println("\tF -> Feed plants");
        System.out.println("\tH -> Harvest plants");
        System.out.println("\tU -> Uproot plants");
        System.out.println("\tS -> Go to store");
        System.out.println("\tB -> Back to homepage");

        String action = input.nextLine();
        switch (action.toUpperCase()) {
            case ("B"):
                displayMenu();
                break;
            case ("W"):
                waterPlants();
                break;
            case ("F"):
                feedPlants();
                break;
            case ("H"):
                harvest();
                break;
            case ("U"):
                uproot();
                break;
            case ("S"):
                visitStore();
                break;
            default:
                seeMyGarden();
                break;
        }
    }

    private void waterPlants() {
        System.out.println("Which plant would you like to water?");
        String response = input.nextLine();
        String feedOrWater = "water";
        waterAndFeed(response, feedOrWater);
    }

    private void feedPlants() {
        System.out.println("Which plant would you like to feed?");
        String response = input.nextLine();
        String feedOrWater = "feed";
        waterAndFeed(response, feedOrWater);
    }

    private void waterAndFeed(String response, String feedOrWater) {
        if (garden.searchForPlant(response)) {
            Plant plant = garden.getPlant(response);

            if (feedOrWater.equals("water")) {
                plant.waterPlant();
                System.out.println("Your " + plant.getPlantName() + " is watered!");
                System.out.println("It's current water count is: " + plant.getWaterCount());
            } else {
                plant.feedPlant();
                System.out.println("Your " + plant.getPlantName() + " is fed!");
                System.out.println("It's current feed count is: " + plant.getFertilizerCount());
            }
            seeMyGarden();
        }
        System.out.println("You don't have this plant to water!");
        seeMyGarden();
    }

    private void harvest() {
        for (Plant plant : garden.getGarden()) {
            if (plant.getLifeStatus().equals("Ripe!")) {
                inventory.addPlant(garden, plant);
                System.out.println("You just harvested a " + plant.getPlantName() + "!");
                System.out.println("Your inventory count: " + inventory.getSize());
                System.out.println("Your garden size: " + garden.getSize());
                displayMenu();
            }
        }
        System.out.println("Your garden has nothing to harvest :(");
        displayMenu();
    }

    private void uproot() {
        if (garden.getSize() == 0) {
            seeMyGarden();
        }
        System.out.println("Which plant would you like to remove?");
        String removePlant = input.nextLine();
        System.out.println("\nAre you sure you want to permanently remove " + removePlant + "?");
        System.out.println("\tY -> Get rid of it!");
        System.out.println("\tN -> My mistake! I want to keep it!");

        String response = input.nextLine();

        if ("Y".equalsIgnoreCase(response)) {
            if (garden.removePlant(removePlant)) {
                System.out.println("Your garden size: " + garden.getSize());
                System.out.println();
                seeMyGarden();
            } else {
                System.out.println("You don't have this plant in your garden!");
                seeMyGarden();
            }
        } else {
            seeMyGarden();
        }
    }


    private void visitStore() {
        System.out.println("Welcome to the store!");
        System.out.println("\nWhich seed catalogue would you like to browse?");
        System.out.println("\tA -> All seeds");
        System.out.println("\tV -> Veggies");
        System.out.println("\tF -> Flowers");
        System.out.println("\tB -> Back to homepage");

        String action = input.nextLine();

        switch (action.toUpperCase()) {
            case ("A"):
                displaySeeds(store.getStore());
                break;
            case ("V"):
                displaySeeds(store.getVegetables());
                break;
            case ("F"):
                displaySeeds(store.getFlowers());
                break;
            case ("B"):
                displayMenu();
                break;
            default:
                visitStore();
                break;
        }
    }

    private void displaySeeds(ArrayList<Plant> plants) {
        for (Plant plant : plants) {
            System.out.println(plant.getPlantName() + ", WaterCount: " + plant.getWaterCount() +
                    ", FeedCount: " + plant.getFertilizerCount() + ", Price: " + plant.getPrice());
        }

        System.out.println("\nWould you like to buy something?");
        System.out.println("\tY -> Yes!");
        System.out.println("\tN -> Get me out of here!");

        String action = input.nextLine();

        switch (action.toUpperCase()) {
            case ("Y"):
                System.out.println("Which seed would you like to buy?");
                String response = input.nextLine();
                if (store.searchForPlant(response)) {
                    buySeed(response, plants);
                    break;
                }
                System.out.println("Not in stock :(");
                visitStore();
                break;
            case ("N"):
                displayMenu();
        }
    }

    private void buySeed(String response, ArrayList<Plant> plants) {
        for (Plant plant : plants) {
            if (plant.getPlantName().equalsIgnoreCase(response)) {
                if (wallet.getBalance() >= plant.getPrice()) {

                    Plant newPlant = duplicatePlant(plant);

                    garden.addPlant(newPlant);

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

    private Plant duplicatePlant(Plant plant) {
        String plantName = plant.getPlantName();

        switch (plantName) {
            case ("Cactus"):
                return new Cactus("Cactus", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Carrot"):
                return new Carrot("Carrot", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Eggplant"):
                return new Eggplant("Eggplant", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Forget_Me_Not"):
                return new Forget_Me_Not("Forget_Me_Not", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Garlic"):
                return new Garlic("Garlic", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Lavender"):
                return new Lavender("Lavender", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Lettuce"):
                return new Lettuce("Lettuce", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Potato"):
                return new Potato("Potato", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Rose"):
                return new Rose("Rose", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
            case ("Sunflower"):
                return new Sunflower("Sunflower", plant.getWaterCount(),
                        plant.getFertilizerCount(), plant.getPrice(), plant.getType());
        }
        return null;
    }

    private void seeMyInventory() {
        if (inventory.getSize() == 0) {
            System.out.println("It's empty! Go sow some seeds!");
            displayMenu();
        }

        for (Plant plant : inventory.getInventory()) {
            System.out.println(plant.getPlantName() + " value: " + plant.getProfitValue());
        }

        System.out.println("\nWould you like to sell?");
        System.out.println("\tY -> Yes! Lets make some money!");
        System.out.println("\tN -> No! Stay away from my plants!");

        String action = input.nextLine();
        if ("Y".equalsIgnoreCase(action)) {
            sellPlants();
        } else if ("N".equalsIgnoreCase(action)) {
            displayMenu();
        } else {
            seeMyInventory();
        }

    }

    private void sellPlants() {
        System.out.println("What would you like to sell?");
        String response = input.nextLine();
        Plant removedPlant = inventory.searchInventory(response);

        if (inventory.removePlant(response)) {
            wallet.increaseBalance(removedPlant.getProfitValue());
            System.out.println("You just sold a " + response + "!");
            System.out.println("You've earned: " + removedPlant.getProfitValue());
            System.out.println("Total balance: " + wallet.getBalance());
        } else {
            System.out.println("You don't have that to sell!");
        }
        System.out.println();
        displayMenu();

    }

    private void checkBalance() {
        System.out.println("Your balance is: " + wallet.getBalance());
        System.out.println();
        displayMenu();
    }

    private void getInstructions() {
        System.out.println("\n~ GAME PLAY ~");
        System.out.println("\tBuy seeds from the store and grow them!");
        System.out.println("\tEach plant has its own water and fertilizer count. " +
                "Once both counts are 0, the plant is ready for harvest!");
        System.out.println("\tHarvest your plants and sell them to buy more plants!");
        System.out.println("\tOverwatering and overfeeding will kill your plants!");
        System.out.println();
        displayMenu();
    }

}
