package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//Represents the garden application
public class GardenApp {

    private final Store store = new Store();
    private Inventory inventory;
    private Garden garden;
    private Wallet wallet;
    private final Scanner input = new Scanner(System.in);

    private static final String JSON_STORE = "./data/account.json";
    private SavedItems savedItems;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructs savedItems
    public GardenApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: greets user
    public void runGarden() {
        System.out.println("Welcome to your virtual garden!");
        System.out.println();
        loginPage();
    }

    //EFFECTS: displays the menu options when user is in home page
    private void loginPage() {
        System.out.println("\n How would you like to login?");
        System.out.println("\t L -> Login (load saved game)");
        System.out.println("\t C -> Create new account");
        System.out.println("\t Q -> Quit");
        processLoginCommand();
    }

    //EFFECTS: asks user if they want to create a new game or load saved progress
    private void processLoginCommand() {
        String response = input.nextLine();
        if (response.equalsIgnoreCase("L")) {
            confirmLoadedProgress();
        } else if (response.equalsIgnoreCase("C")) {
            initializeGarden();
        } else if (response.equalsIgnoreCase("Q")) {
            System.out.println("Come back soon!");
            input.close();
        } else {
            System.out.println("Please enter one of presented letters");
            loginPage();
        }
    }

    //EFFECTS: loads previously saved progress and gives user printed confirmation
    private void confirmLoadedProgress() {
        loadProgress();
        System.out.println(garden.getGardenName() + "'s garden is loaded!");
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS: loads previously saved progress (garden, inventory, wallet) from file
    public void loadProgress() {
        try {
            savedItems = jsonReader.read();
            garden = savedItems.getGarden();
            inventory = savedItems.getInventory();
            wallet = savedItems.getWallet();

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: initializes garden by asking for name
    private void initializeGarden() {
        System.out.println("What is your garden's name?");
        String name = input.nextLine();
        instantiateGarden(name);
        System.out.println("Welcome to " + name + "'s" + " garden!");
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS: instantiates a new garden, inventory and wallet
    public void instantiateGarden(String gardenName) {
        inventory = new Inventory();
        wallet = new Wallet();
        garden = new Garden(gardenName);
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

        processDisplayCommand();
    }

    //EFFECTS: processes user's command to navigate through entire game
    private void processDisplayCommand() {
        String action = input.nextLine();
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
                quitApp();
            default:
                System.out.println("Please enter one of the presented letters");
                processDisplayCommand();
        }
    }

    //EFFECTS: asks user if they would like to save progress before quitting
    private void quitApp() {
        System.out.println("\nWould you like to save your progress");
        System.out.println("\t Y -> Yes!");
        System.out.println("\t N -> No!");

        String response = input.nextLine();

        if (response.equalsIgnoreCase("Y")) {
            saveProgressConfirmation();
        } else if (response.equalsIgnoreCase("N")) {
            System.out.println("Come back soon!");
            System.exit(0);
        } else {
            System.out.println("Please enter one of the given letters");
            quitApp();
        }
    }

    //EFFECTS: saves progress and confirms with user; exits program
    private void saveProgressConfirmation() {
        saveProgress();
        System.out.println("Your progress at " + garden.getGardenName() + "'s" + " is saved!");
        System.out.println("Come back soon!");
        System.exit(0);
    }

    //EFFECTS: saves user's garden, inventory and wallet to file
    public void saveProgress() {
        try {
            savedItems = new SavedItems(garden, inventory, wallet);
            jsonWriter.openFile();
            jsonWriter.write(savedItems);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: ");
        }
    }

    //EFFECTS: shows user's garden
    protected void seeMyGarden() {
        if (garden.getSize() == 0) {
            System.out.println("Your garden is empty, yet your soil is fertile.");
        } else {
            int position = 0;
            System.out.println("** Your Garden **");
            for (Plant plant : garden.getGarden()) {
                System.out.println(position + ". " + plant.getPlantName() + ", WaterCount: " + plant.getWaterCount()
                        + ", FeedCount: " + plant.getFertilizerCount() + ", Status: " + plant.getUpdatedLifeStatus());
                position++;
            }
        }
        gardenMenu();
    }

    //EFFECTS: shows user the display menu for their garden
    private void gardenMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tW -> Water plants");
        System.out.println("\tF -> Feed plants");
        System.out.println("\tH -> Harvest plants");
        System.out.println("\tU -> Uproot plants");
        System.out.println("\tS -> Go to store");
        System.out.println("\tB -> Back to homepage");

        processGardenCommand();
    }

    //MODIFIES: this
    //EFFECTS: processes user's command to navigate through garden
    private void processGardenCommand() {
        switch (input.nextLine().toUpperCase()) {
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
                System.out.println("Please enter one of the presented letters");
                processGardenCommand();
        }
    }

    //MODIFIES: this
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
    protected void waterPlants() {
        System.out.println("Which plant would you like to water? (Enter plant's numeric position on garden list)");
        String feedOrWater = "water";
        waterAndFeed(getInt(), feedOrWater);
    }

    //EFFECTS: asks which plant user wants to feed
    protected void feedPlants() {
        System.out.println("Which plant would you like to feed? (Enter plant's numeric position on garden list)");
        String feedOrWater = "feed";
        waterAndFeed(getInt(), feedOrWater);
    }

    //MODIFIES: this
    //EFFECTS: waters or feeds plant
    protected void waterAndFeed(int response, String feedOrWater) {
        if (0 <= response && response < garden.getSize()) {
            Plant plant = garden.getPlant(response);

            if (feedOrWater.equals("water")) {
                plant.waterPlant();
                System.out.println("Your " + plant.getPlantName() + " is watered!");
                System.out.println("Its current water count is: " + plant.getWaterCount());
                System.out.println();
            } else {
                plant.feedPlant();
                System.out.println("Your " + plant.getPlantName() + " is fed!");
                System.out.println("Its current feed count is: " + plant.getFertilizerCount());
                System.out.println();
            }
        } else {
            System.out.println("You don't have this plant to water!");
            System.out.println();
        }
        seeMyGarden();
    }

    //MODIFIES: this
    //EFFECTS: adds ripe plants to inventory, removes the same plants from garden
    protected void harvest() {
        if (garden.getSize() != 0) {
            System.out.println("You just harvested " + inventory.addAllPlant(garden) + " plant(s)!");
            System.out.println("Your inventory count: " + inventory.getSize());
            System.out.println("Your garden size: " + garden.getSize());
            displayMenu();
        } else {
            System.out.println("Your garden has nothing to harvest :(");
            displayMenu();
        }
    }

    //EFFECTS: asks user which plant(s) they want to remove from garden
    protected void uproot() {
        if (garden.getSize() == 0) {
            seeMyGarden();
        }
        System.out.println("\n Which plant would you like to remove?");
        System.out.println("\t A -> Take them all away!");
        System.out.println("\t D -> Just the dead ones");
        System.out.println("\t O -> Just one plant");
        System.out.println("\t B -> I changed my mind... get me outta here!");

        processUprootCommand();
    }

    //MODIFIES: this
    //EFFECTS: processes command from uproot display menu
    private void processUprootCommand() {
        String response = input.nextLine();

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
                processUprootCommand();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes just one plant that the user specifies
    protected void uprootJustOne() {
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
    protected void visitStore() {
        System.out.println("Welcome to the store!");
        System.out.println("\nWhich seed catalogue would you like to browse?");
        System.out.println("\tA -> All seeds");
        System.out.println("\tV -> Veggies");
        System.out.println("\tF -> Flowers");
        System.out.println("\tB -> Back to homepage");

        storeMenu();
    }

    //MODIFIES: this
    //EFFECTS: shows the display menu for the store
    private void storeMenu() {
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
                System.out.println("Please enter one of the presented letters");
                storeMenu();
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

        processSellingSeedsCommand(plants);
    }

    //EFFECTS: asks which plant user wants to buy
    private void processSellingSeedsCommand(ArrayList<Plant> plants) {
        switch (input.nextLine().toUpperCase()) {
            case ("Y"):
                System.out.println("Which seed would you like to buy? (Enter plant's name)");
                String response = input.nextLine();
                checkEligibility(response, plants);
                break;
            case ("N"):
                displayMenu();
            default:
                System.out.println("Please enter one of the presented letters");
                processSellingSeedsCommand(plants);
        }
    }

    //EFFECTS:checks if store has plant
    public void checkEligibility(String response, ArrayList<Plant> plants) {
        if (store.searchForPlant(response)) {
            Plant plant = store.getPlant(response);
            if (checkForMoney(plant)) {
                buySeed(plant);
                System.out.println("You added a(n) " + plant.getPlantName() + " to your garden!");
                System.out.println("Your current balance is: " + wallet.getBalance());
                displayMenu();
            } else {
                System.out.println("You don't have enough money!");
                displayMenu();
            }
        }
        System.out.println("Not in stock :(");
        visitStore();
    }

    //EFFECTS: returns true if wallet balance has enough to buy plant; else, false
    public boolean checkForMoney(Plant plant) {
        return plant.getPrice() <= wallet.getBalance();
    }

    //MODIFIES: this
    //EFFECTS: adds plant to garden and decreases wallet balance by plant's price
    public void buySeed(Plant plant) {
        Plant newPlant = plant.cloneObject();
        garden.addPlant(newPlant);
        wallet.decreaseBalance(plant.getPrice());
    }

    //EFFECTS: shows user's collection of ripe plants that are ready for sale
    protected void seeMyInventory() {
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
        processInventoryCommand();
    }

    //MODIFIES: this
    //EFFECTS: processes user's decision to sell or not sell their plants
    private void processInventoryCommand() {
        String action = input.nextLine();
        if ("Y".equalsIgnoreCase(action)) {
            sellPlants();
        } else if ("N".equalsIgnoreCase(action)) {
            displayMenu();
        } else {
            System.out.println("Please enter one of the presented letters");
            processInventoryCommand();
        }
    }

    //MODIFIES: this
    //EFFECTS: sells user's plant, and increases their wallet balance based on plant's price
    private void sellPlants() {
        System.out.println("Which plant would you like to sell? (Enter plant's numeric position on garden list)");
        int position = getInt();

        if (position < 0) {
            System.out.println("Please enter a non-negative integer");
            sellPlants();
        } else if (position < inventory.getSize()) {
            Plant plant = inventory.getPlant(position);
            processSale(position, plant);
            System.out.println("You just sold a " + plant.getPlantName() + "!");
            System.out.println("You've earned: " + plant.getProfitValue());
            System.out.println("Total balance: " + wallet.getBalance());
        } else {
            System.out.println("You don't have that to sell!");
        }
        System.out.println();
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS: removes plant from inventory, and increases wallet balance by plant's profit value
    public void processSale(int position, Plant plant) {
        inventory.removePlant(position);
        wallet.increaseBalance(plant.getProfitValue());
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

    //getters
    public String getGardenName() {
        return garden.getGardenName();
    }

    public Garden getGarden() {
        return garden;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Store getStoreFront() {
        return store;
    }


}
