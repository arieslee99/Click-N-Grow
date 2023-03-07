package model.tests;

import model.Garden;
import model.Inventory;
import model.Plant;
import model.seeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static org.junit.jupiter.api.Assertions.*;

public class TestInventory {

    private Inventory inventory;
    private Garden garden;
    private Garden garden2;
    private final Lavender lavender = new Lavender("Lavender", 0, 0, 500, FLOWER);
    private final Rose rose = new Rose("Rose", 0, 0, 600, FLOWER);
    private final Sunflower sunflower
            = new Sunflower("Sunflower", 8, 9, 300, FLOWER);
    private final Cactus cactus = new Cactus("Cactus", 9, 0, 100, FLOWER);

    @BeforeEach
    public void setup() {
        inventory = new Inventory();
        garden = new Garden("Aries' garden");
        garden2 = new Garden("David's garden");
        garden.addPlant(lavender);
        garden.addPlant(rose);

        garden2.addPlant(lavender);

    }

    @Test
    public void testAddPlant() {
        int ripeCount = inventory.addPlant(garden2);
        assertEquals(1, ripeCount);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertNull(inventory.searchInventory("rose"));
        assertEquals(1, inventory.getSize());
        assertEquals(0, garden2.getSize());
        assertFalse(garden2.searchForPlant("lavender"));

    }

    @Test
    public void testAddMultiplePlants() {
        int ripeCount = inventory.addPlant(garden);
        assertEquals(2, ripeCount);

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
        int ripeCount = inventory.addPlant(garden);
        assertEquals(2, ripeCount);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertEquals(rose, inventory.searchInventory("rose"));
        assertNull(inventory.searchInventory("sunflower"));
        assertEquals(2, inventory.getSize());
        assertFalse(garden.searchForPlant("lavender"));
        assertTrue(garden.searchForPlant("sunflower"));
        assertEquals(1, garden.getSize());

    }

    @Test
    public void testAddRipePlantButFailed() {
        int ripeCount = inventory.addPlant(garden);
        assertEquals(2, ripeCount);
        assertEquals(2, inventory.getSize());
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertNull(inventory.searchInventory("cactus"));
        assertEquals(0, garden.getSize());
    }

    @Test
    public void testRemoveOnePlant() {
        inventory.addPlant(garden);
        assertEquals(2, inventory.getSize());
        inventory.removePlant(0);
        assertEquals(1, inventory.getSize());
        assertEquals(rose, inventory.getPlant(0));
    }

    @Test
    public void testRemoveMultiplePlants() {
        inventory.addPlant(garden);
        assertEquals(2, inventory.getSize());
        inventory.removePlant(0);
        assertEquals(1, inventory.getSize());
        assertEquals(rose, inventory.getPlant(0));

        inventory.removePlant(0);
        assertEquals(0, inventory.getSize());
    }

    @Test
    public void testSearchInventory() {
        garden.addPlant(sunflower);
        inventory.addPlant(garden);
        assertEquals(lavender, inventory.searchInventory("lavender"));
        assertEquals(rose, inventory.searchInventory("rose"));
        assertNull(inventory.searchInventory("sunflower"));

        ArrayList<Plant> plants = inventory.getInventory();
        assertTrue(plants.contains(rose));
        assertTrue(plants.contains(lavender));
        assertFalse(plants.contains(sunflower));

    }

    @Test
    public void testGetPlant() {
        inventory.addPlant(garden);
        assertEquals(lavender, inventory.getPlant(0));
        assertEquals(rose, inventory.getPlant(1));
    }

    @Test
    public void testJustAddPlant() {
        assertEquals(0, inventory.getSize());
        inventory.justAddPlant(lavender);
        assertEquals(1, inventory.getSize());
        assertEquals(lavender, inventory.getPlant(0));
    }
}
