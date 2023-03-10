package persistenceTests;

import model.*;
import model.seeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.*;

import static model.PlantType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    private Garden garden;
    private Inventory inventory;
    private Wallet wallet;

    @BeforeEach
    public void setup() {
        garden = new Garden("david");
        inventory = new Inventory();
        wallet = new Wallet();
        wallet.setBalance(100);
    }

    @Test
    public void testWriterFileNotFound() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openFile();
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    public void testWriterFileFound() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterFileFound.json");
            writer.openFile();
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException not expected");
        }
    }

    @Test
    public void testWriterWriteBaseCaseSavedItems() {

        try {
            SavedItems savedItems = new SavedItems(garden, inventory, wallet);
            JsonWriter writer = new JsonWriter("./data/testWriterWriteBaseCaseSavedItems.json");
            writer.openFile();
            writer.write(savedItems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWriteBaseCaseSavedItems.json");
            savedItems = reader.read();
            assertEquals("david", savedItems.getGarden().getGardenName());
            assertEquals(0, savedItems.getGarden().getSize());
            assertEquals(0, savedItems.getInventory().getSize());
            assertEquals(100, savedItems.getWallet().getBalance());
        } catch (IOException e) {
            fail("FileNotFoundException not expected");
        }
    }

    @Test
    public void testWriterGeneralSavedItems() {
        garden.addPlant(new Cactus("Cactus", 1, 2, 100, FLOWER));
        inventory.justAddPlant(new Potato("Potato", 0, 0, 100, VEGETABLE));
        wallet.setBalance(200);

        try {
            SavedItems savedItems = new SavedItems(garden, inventory, wallet);
            JsonWriter writer = new JsonWriter("./data/testWriterWriteGeneralSavedItems.json");
            writer.openFile();
            writer.write(savedItems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWriteGeneralSavedItems.json");
            savedItems = reader.read();
            assertEquals(1, savedItems.getGarden().getSize());
            assertEquals(1, savedItems.getInventory().getSize());
            assertEquals(200, savedItems.getWallet().getBalance());

        } catch (IOException e) {
            fail("FileNotFoundException not expected");
        }
    }


}
