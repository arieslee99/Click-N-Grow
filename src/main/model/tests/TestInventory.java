package model.tests;

import model.Garden;
import model.Inventory;
import model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static org.junit.jupiter.api.Assertions.*;

public class TestInventory {

    private Inventory inventory;
    private Garden garden;
    private final Plant lavender = (new Plant("Lavender", 0, 0, 500, FLOWER));
    private final Plant rose = (new Plant("Rose", 0, 0, 600, FLOWER));
    private final Plant sunflower = (new Plant("Sunflower", 8, 9, 300, FLOWER));

    @BeforeEach
    public void setup() {
        inventory = new Inventory();
        garden = new Garden("Aries' garden");
        garden.addPlant(lavender);
        garden.addPlant(rose);

    }

    @Test
    public void TestAddPlant() {
        inventory.addPlant(garden, lavender);
        boolean status = inventory.searchInventory(lavender);
        assertTrue(status);
        assertEquals(1, garden.getSize());
        assertFalse(garden.getPlant(lavender));
        assertTrue(garden.getPlant(rose));

    }

    @Test
    public void TestRemoveOnePlant() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(2, inventory.getSize());
        inventory.removePlant(lavender);
        assertEquals(1, inventory.getSize());

        ArrayList<Plant> plants = inventory.getInventory();
        assertTrue(plants.contains(rose));
        assertFalse(plants.contains(lavender));
    }

    @Test
    public void TestRemoveMultiplePlants() {
       inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(2, inventory.getSize());
        inventory.removePlant(lavender);
        inventory.removePlant(rose);


        assertEquals(0, inventory.getSize());
        ArrayList<Plant> plants = inventory.getInventory();
        assertFalse(plants.contains(rose));
        assertFalse(plants.contains(lavender));
    }

    @Test
    public void TestSearchInventory() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        boolean success = inventory.searchInventory(lavender);
        boolean success1 = inventory.searchInventory(rose);
        assertTrue(success);
        assertTrue(success1);

        ArrayList<Plant> plants = inventory.getInventory();
        assertTrue(plants.contains(rose));
        assertTrue(plants.contains(lavender));
        assertFalse(plants.contains(sunflower));

    }
}
