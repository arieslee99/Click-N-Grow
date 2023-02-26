package ui;

import model.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

    //EFFECTS: initializes garden by asking for name
    private void initializeGarden() {
        System.out.println("What is your garden's name?");
        String name = input.nextLine();
        garden.setGardenName(name);
        System.out.println("Welcome to " + name + "'s" + " garden!");
        displayMenu();
    }

    //EFFECTS: displays game options
    private void displayMenu() {
        System.out.println();
        System.out.println("What would you like to do today?");
        System.out.println("\nSelect from:");
        System.out.println("\tG -> Go to garden");
        System.out.println("\tS -> Go to store");
        System.out.println("\tI -> See my inventory");
        System.out.println("\tW -> Check my wallet");
        System.out.println("\tP -> How to play");
        System.out.println("\tQ -> Quit");

        String action = input.nextLine();
        processDisplayCommand(action);
    }

    //EFFECTS: processes user's command to navigate through entire game
    private void processDisplayCommand(String action) {
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
                System.exit(0);
            default:
                displayMenu();
        }
    }

    //EFFECTS: shows user's garden
    private void seeMyGarden() {
        if (garden.getSize() == 0) {
            System.out.println("Your garden is empty, yet your soil is fertile.");
        } else {
            int position = 0;
            System.out.println("** Your Garden **");
            for (Plant plant : garden.getGarden()) {
                System.out.println(position + ". " + plant.getPlantName() + ", WaterCount: " + plant.getWaterCount()
                        + ", FeedCount: " + plant.getFertilizerCount() + ", Status: " + plant.getLifeStatus());
                position++;
            }
        }

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tW -> Water plants");
        System.out.println("\tF -> Feed plants");
        System.out.println("\tH -> Harvest plants");
        System.out.println("\tU -> Uproot plants");
        System.out.println("\tS -> Go to store");
        System.out.println("\tB -> Back to homepage");

        processGardenCommand(input.nextLine());
    }

    //EFFECTS: processes user's command to navigate through garden
    private void processGardenCommand(String action) {
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
        }
    }

    //EFFECTS: checks input to see if it's an integer; returns integer
    private Integer getInt() {
        while (true) {
            try {
                int response = input.nextInt();
                input.nextLine();
                return response;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
                input.nextLine();
            }
        }
    }

    //EFFECTS: asks which plant user wants to water
    private void waterPlants() {
        System.out.println("Which plant would you like to water? (Enter plant's numeric position on garden list)");
        String feedOrWater = "water";
        waterAndFeed(getInt(), feedOrWater);
    }

    //EFFECTS: asks which plant user wants to feed
    private void feedPlants() {
        System.out.println("Which plant would you like to feed? (Enter plant's numeric position on garden list)");
        String feedOrWater = "feed";
        waterAndFeed(getInt(), feedOrWater);
    }

    //EFFECTS: waters or feeds plant
    private void waterAndFeed(int response, String feedOrWater) {
        if (0 <= response && response < garden.getSize()) {
            Plant plant = garden.getPlant(response);

            if (feedOrWater.equals("water")) {
                plant.waterPlant();
                System.out.println("Your " + plant.getPlantName() + " is watered!");
                System.out.println("It's current water count is: " + plant.getWaterCount());
                System.out.println();
            } else {
                plant.feedPlant();
                System.out.println("Your " + plant.getPlantName() + " is fed!");
                System.out.println("It's current feed count is: " + plant.getFertilizerCount());
                System.out.println();
            }
        } else {
            System.out.println("You don't have this plant to water!");
            System.out.println();
        }
        seeMyGarden();
    }

    //EFFECTS: adds ripe plants to inventory, removes the same plants from garden
    private void harvest() {
        if (garden.getSize() != 0) {
            System.out.println("You just harvested " + inventory.addPlant(garden) + " plant(s)!");
            System.out.println("Your inventory count: " + inventory.getSize());
            System.out.println("Your garden size: " + garden.getSize());
            displayMenu();
        } else {
            System.out.println("Your garden has nothing to harvest :(");
            displayMenu();
        }
    }

    //EFFECTS: asks user which plant(s) they want to remove from garden
    private void uproot() {
        if (garden.getSize() == 0) {
            seeMyGarden();
        }
        System.out.println("\n Which plant would you like to remove?");
        System.out.println("\t A -> Take them all away!");
        System.out.println("\t D -> Just the dead ones");
        System.out.println("\t O -> Just one plant");
        System.out.println("\t B -> I changed my mind... get me outta here!");

        String response = input.nextLine();
        processUprootCommand(response);
    }

    //EFFECTS: processes command from uproot display menu
    private void processUprootCommand(String response) {
        switch (response.toUpperCase()) {
            case ("A"):
                garden.emptyGarden();
                System.out.println("You just cleaned house!");
                seeMyGarden();
                break;
            case ("D"):
                garden.removeDeadPlants();
                System.out.println("All cleaned up!");
                seeMyGarden();
                break;
            case ("O"):
                uprootJustOne();
                break;
            case ("B"):
                seeMyGarden();
                break;
            default:
                System.out.println("Please enter one of the presented letters");
                uproot();
        }
    }

    //EFFECTS: removes just one plant that the user specifies
    private void uprootJustOne() {
        System.out.println("Which plant would you like to remove? (Enter plant's numeric position on garden list)");
        int position = getInt();

        if (position < 0) {
            System.out.println("Please enter a non-negative integer");
            uprootJustOne();
        } else if (position < garden.getSize()) {
            Plant plant = garden.getPlant(position);
            System.out.println("\nAre you sure you want to permanently remove " + plant.getPlantName() + "?");
            System.out.println("\tY -> Get rid of it!");
            System.out.println("\tN -> My mistake! I want to keep it!");
            String response = input.nextLine();

            if ("Y".equalsIgnoreCase(response)) {
                garden.removePlant(position);
                System.out.println("Your garden size: " + garden.getSize());
                seeMyGarden();
            } else {
                seeMyGarden();
            }
        } else {
            System.out.println("You don't have this plant in your garden!");
            seeMyGarden();
        }
    }

    //EFFECTS: shows user store display menu
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
        }
    }

    //EFFECTS: shows user what seeds are available for purchase
    private void displaySeeds(ArrayList<Plant> plants) {
        for (Plant plant : plants) {
            System.out.println(plant.getPlantName() + ", WaterCount: " + plant.getWaterCount()
                    + ", FeedCount: " + plant.getFertilizerCount() + ", Price: " + plant.getPrice());
        }
        System.out.println("\nWould you like to buy something? Your current balance is: " + wallet.getBalance());
        System.out.println("\tY -> Yes!");
        System.out.println("\tN -> Get me out of here!");

        switch (input.nextLine().toUpperCase()) {
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
            default:
                displaySeeds(plants);
        }
    }

    //EFFECTS: adds plant to user's garden, deducts plant's amount from wallet
    private void buySeed(String response, ArrayList<Plant> plants) {
        for (Plant plant : plants) {
            if (plant.getPlantName().equalsIgnoreCase(response)) {
                if (wallet.getBalance() >= plant.getPrice()) {

                    Plant newPlant = plant.cloneObject();
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

    //EFFECTS: shows user's collection of ripe plants that are ready for sale
    private void seeMyInventory() {
        if (inventory.getSize() == 0) {
            System.out.println("It's empty! Go sow some seeds!");
            displayMenu();
        }

        int position = 0;
        for (Plant plant : inventory.getInventory()) {
            System.out.println(position + ". " + plant.getPlantName() + " value: " + plant.getProfitValue());
            position++;
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

    //EFFECTS: sells user's plant, and increases their wallet balance based on plant's price
    private void sellPlants() {
        System.out.println("Which plant would you like to sell? (Enter plant's numeric position on garden list)");
        int position = getInt();

        if (position < 0) {
            System.out.println("Please enter a non-negative integer");
            sellPlants();
        } else if (position < inventory.getSize()) {
            Plant plant = inventory.getPlant(position);
            inventory.removePlant(position);
            wallet.increaseBalance(plant.getProfitValue());
            System.out.println("You just sold a " + plant.getPlantName() + "!");
            System.out.println("You've earned: " + plant.getProfitValue());
            System.out.println("Total balance: " + wallet.getBalance());
        } else {
            System.out.println("You don't have that to sell!");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: shows user's current wallet balance
    private void checkBalance() {
        System.out.println("Your balance is: " + wallet.getBalance());
        System.out.println();
        displayMenu();
    }

    //EFFECTS: shows user instructions on how to play game
    private void getInstructions() {
        System.out.println("~ GAME PLAY ~");
        System.out.println("o Buy seeds from the store and grow them!");
        System.out.println("o Each plant has its own water and fertilizer count. "
                + "Once both counts are 0, the plant is ready for harvest!");
        System.out.println("o Harvest your plants and sell them to buy more plants!");
        System.out.println("o Check your inventory to for your ripe plants!");
        System.out.println("o Overwatering and overfeeding will kill your plants!");
        System.out.println();
        displayMenu();
    }
}
