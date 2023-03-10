package persistenceTests;

import model.*;
import model.SavedItems;
import model.seeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    private Garden garden;
    private Inventory inventory;
    private Wallet wallet;
    private final Garlic garlic = new Garlic("Garlic", 2, 3, 100, VEGETABLE);
    private final Lavender lavender = new Lavender("Lavender", 5, 6, 500, FLOWER);
    private final Cactus cactus = new Cactus("Cactus", 0, 0, 100, FLOWER);
    private final ForgetMeNot forgetMeNot =
            new ForgetMeNot("Forget Me Not", 3, 6, 500, FLOWER);
    private final Sunflower sunflower =
            new Sunflower("Sunflower", 8, 9, 300, FLOWER);
    private final Rose rose = new Rose("Rose", 6, 6, 600, FLOWER);
    private final Carrot carrot =
            new Carrot("Carrot", 5, 7, 200, VEGETABLE);
    private final Potato potato = new Potato("Potato", 3, 5, 100, VEGETABLE);
    private final Lettuce lettuce = new Lettuce("Lettuce", 8, 3, 400, VEGETABLE);
    private final Eggplant eggplant = new Eggplant("Eggplant", 0, 0, 300, VEGETABLE);


    @BeforeEach
    public void setup() {
        garden = new Garden("David's garden");
        inventory = new Inventory();
        wallet = new Wallet();
        wallet.setBalance(100);
    }

    @Test
    public void testReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/InvalidFile.json");
        try {
            SavedItems savedItems = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderBaseCaseFile() {
        try {
            SavedItems savedItems = new SavedItems(garden, inventory, wallet);
            JsonWriter writer = new JsonWriter("./data/testReaderBaseCaseSavedItems.json");
            writer.openFile();
            writer.write(savedItems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderBaseCaseSavedItems.json");
            savedItems = reader.read();
            assertEquals("David's garden", savedItems.getGarden().getGardenName());
            assertEquals(0, savedItems.getGarden().getSize());
            assertEquals(0, savedItems.getInventory().getSize());
            assertEquals(100, savedItems.getWallet().getBalance());

        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    public void testReaderGeneralSavedItems() {
        garden.addPlant(lavender);
        garden.addPlant(garlic);
        inventory.justAddPlant(cactus);
        wallet.setBalance(300);

        try {
            SavedItems savedItems = new SavedItems(garden, inventory, wallet);
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralSavedItems.json");
            writer.openFile();
            writer.write(savedItems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralSavedItems.json");
            SavedItems saveItems1 = reader.read();
            assertEquals("David's garden", saveItems1.getGarden().getGardenName());
            assertEquals(2, saveItems1.getGarden().getSize());
            assertEquals(1, saveItems1.getInventory().getSize());
            assertEquals(300, saveItems1.getWallet().getBalance());

            assertEquals(lavender, saveItems1.getGarden().getPlant(0));
            assertEquals(garlic, saveItems1.getGarden().getPlant(1));
            assertEquals(cactus, saveItems1.getInventory().getPlant(0));

        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    public void testReaderAllOfTheSeedCatalogue() {
        addPlants();
        try {
            SavedItems savedItems = new SavedItems(garden, inventory, wallet);
            JsonWriter writer = new JsonWriter("./data/testReaderAllOfTheSeedCatalogue.json");
            writer.openFile();
            writer.write(savedItems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderAllOfTheSeedCatalogue.json");
            SavedItems saveItems1 = reader.read();
            assertEquals("David's garden", saveItems1.getGarden().getGardenName());
            assertEquals(6, saveItems1.getGarden().getSize());
            assertEquals(2, saveItems1.getInventory().getSize());

            assertEquals(forgetMeNot, saveItems1.getGarden().getPlant(0));
            assertEquals(carrot, saveItems1.getGarden().getPlant(1));
            assertEquals(lettuce, saveItems1.getGarden().getPlant(2));
            assertEquals(sunflower, saveItems1.getGarden().getPlant(3));
            assertEquals(rose, saveItems1.getGarden().getPlant(4));
            assertEquals(potato, saveItems1.getGarden().getPlant(5));

            assertEquals(cactus, saveItems1.getInventory().getPlant(0));
            assertEquals(eggplant, saveItems1.getInventory().getPlant(1));
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }


    @Test
    public void testConstructActualVeggiePlant() {
        JsonReader reader = new JsonReader(".data/testConstructActualVeggiePlant");
        Plant plant = reader.makeVeggie("Carrot", 5, 7,
                200, "Growing!", VEGETABLE);
        assertEquals("Carrot", plant.getPlantName());
        Plant plant1 = reader.makeVeggie("Garlic", 2, 3, 100,
                "Growing!", VEGETABLE);
        assertEquals("Garlic", plant1.getPlantName());
        Plant plant2 = reader.makeVeggie("Eggplant", 0, 0,
                300, "Growing!", VEGETABLE);
        assertEquals("Eggplant", plant2.getPlantName());
        Plant plant3 = reader.makeVeggie("Potato", 3, 5, 100,
                "Growing!", VEGETABLE);
        assertEquals("Potato", plant3.getPlantName());
        Plant plant4 = reader.makeVeggie("Lettuce", 8, 3, 400,
                "Growing!", VEGETABLE);
        assertEquals("Lettuce", plant4.getPlantName());
        Plant plant11 = reader.makeVeggie("Corn", 1, 1,
                100, "Growing!", VEGETABLE);
        assertNull(plant11);
    }

    @Test
    public void testActualFlowerPlant() {
        JsonReader reader = new JsonReader(".data/testConstructActualFlowerPlant");
        Plant plant5 = reader.makeFlower("Cactus", 0, 0, 100,
                "Growing!", FLOWER);
        assertEquals("Cactus", plant5.getPlantName());
        Plant plant6 = reader.makeFlower("Forget Me Not", 3, 6,
                500, "Growing!",FLOWER);
        assertEquals("Forget Me Not", plant6.getPlantName());
        Plant plant7 = reader.makeFlower("Lavender", 5, 6, 500,
                "Growing!", FLOWER);
        assertEquals("Lavender", plant7.getPlantName());
        Plant plant8 = reader.makeFlower("Rose", 6, 6, 600,
                "Growing!", FLOWER);
        assertEquals("Rose", plant8.getPlantName());
        Plant plant9 = reader.makeFlower("Sunflower", 8, 9, 300,
                "Growing!", FLOWER);
        assertEquals("Sunflower", plant9.getPlantName());
        Plant plant10 = reader.makeFlower("Eucalyptus", 3, 2,
                400, "Growing!", FLOWER);
        assertNull(plant10);
    }


    //MODIFIES: garden
    //EFFECTS: add lots of plants to garden
    private void addPlants() {
        inventory.justAddPlant(cactus);
        inventory.justAddPlant(eggplant);

        garden.addPlant(forgetMeNot);
        garden.addPlant(carrot);
        garden.addPlant(lettuce);
        garden.addPlant(sunflower);
        garden.addPlant(rose);
        garden.addPlant(potato);
    }


}

