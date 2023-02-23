package model.tests;

import model.Garden;
import model.Inventory;
import model.Plant;
import model.SeedCatagloue.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static org.junit.jupiter.api.Assertions.*;

public class TestInventory {

    private Inventory inventory;
    private Garden garden;
    private final Lavender lavender = new Lavender("Lavender", 0, 0, 500, FLOWER);
    private final Rose rose = new Rose("Rose", 0, 0, 600, FLOWER);
    private final Sunflower sunflower
            = new Sunflower("Sunflower", 8, 9, 300, FLOWER);
    private final Cactus cactus = new Cactus("Cactus", 9, 0, 100, FLOWER);

    @BeforeEach
    public void setup() {
        inventory = new Inventory();
        garden = new Garden("Aries' garden");
        garden.addPlant(lavender);
        garden.addPlant(rose);

    }

    @Test
    public void testAddPlant() {
        inventory.addPlant(garden, lavender);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertNull(inventory.searchInventory("rose"));
        assertEquals(1, inventory.getSize());
        assertEquals(1, garden.getSize());
        assertFalse(garden.searchForPlant("lavender"));
        assertTrue(garden.searchForPlant("rose"));

    }

    @Test
    public void testAddMultiplePlants() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertEquals(rose, inventory.searchInventory("rose"));
        assertEquals(2, inventory.getSize());
        assertEquals(0, garden.getSize());
        assertFalse(garden.searchForPlant("lavender"));
        assertFalse(garden.searchForPlant("rose"));

    }

    @Test
    public void testAddPlantButFailed() {
        garden.addPlant(sunflower);
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, sunflower);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertNull(inventory.searchInventory("sunflower"));
        assertEquals(1, inventory.getSize());
        assertFalse(garden.searchForPlant("lavender"));
        assertTrue(garden.searchForPlant("sunflower"));
        assertEquals(2, garden.getSize());

    }

    @Test
    public void testAddRipePlantButFailed() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, cactus);
        assertEquals(1, inventory.getSize());
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertNull(inventory.searchInventory("cactus"));
        assertEquals(1, garden.getSize());
    }

    @Test
    public void testRemoveOnePlant() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(2, inventory.getSize());
        boolean success = inventory.removePlant("lavender");
        assertEquals(1, inventory.getSize());
        assertTrue(success);

        ArrayList<Plant> plants = inventory.getInventory();
        assertTrue(plants.contains(rose));
        assertFalse(plants.contains(lavender));
    }

    @Test
    public void testRemoveMultiplePlants() {
       inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(2, inventory.getSize());
        boolean success1 = inventory.removePlant("lavender");
        boolean success2 = inventory.removePlant("rose");

        assertTrue(success1);
        assertTrue(success2);

        assertEquals(0, inventory.getSize());

        ArrayList<Plant> plants = inventory.getInventory();
        assertFalse(plants.contains(rose));
        assertFalse(plants.contains(lavender));
    }

    @Test
    public void testSearchInventory() {
        inventory.addPlant(garden, lavender);
        inventory.addPlant(garden, rose);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertEquals(rose, inventory.searchInventory("rose"));
        assertNull(inventory.searchInventory("corn"));

        ArrayList<Plant> plants = inventory.getInventory();
        assertTrue(plants.contains(rose));
        assertTrue(plants.contains(lavender));
        assertFalse(plants.contains(sunflower));

    }
}
