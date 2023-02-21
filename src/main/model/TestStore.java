package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.*;

public class TestStore {

    private Store store;
    private final Plant corn = new Plant("Corn", 6,7,500, VEGETABLE);

    @BeforeEach
    public void setup() {
        store = new Store();
    }

    @Test
    public void TestSearchForPlantAndInStock() {
        String inStock = store.searchForPlant(StorePlants.lavender);
        assertEquals("In stock :)", inStock);
    }

    @Test
    public void TestSearchForPlantAndNotInStock() {
        String inStock = store.searchForPlant(corn);
        assertEquals("Not in stock :(", inStock);
    }

    @Test
    public void getStorePlantNames() {
        ArrayList<String> plantNames = store.getPlantNames();
        assertEquals(10, plantNames.size());
        assertTrue(plantNames.contains("Lavender"));
        assertTrue(plantNames.contains("Rose"));
        assertTrue(plantNames.contains("Forget Me Not"));
        assertTrue(plantNames.contains("Sunflower"));
        assertTrue(plantNames.contains("Cactus"));
        assertTrue(plantNames.contains("Carrot"));
        assertTrue(plantNames.contains("Potato"));
        assertTrue(plantNames.contains("Lettuce"));
        assertTrue(plantNames.contains("Eggplant"));
        assertTrue(plantNames.contains("Garlic"));
        assertFalse(plantNames.contains("Tomato"));
    }

    @Test
    public void TestGetStore() {
        ArrayList<Plant> plants = store.getStore();
        assertEquals(10, plants.size());
    }

    @Test
    public void TestGetVeggies() {
        ArrayList<Plant> veggies = store.getVegetables();
        assertEquals(5, veggies.size());

        assertTrue(veggies.contains(StorePlants.carrot));
        assertTrue(veggies.contains(StorePlants.potato));
        assertTrue(veggies.contains(StorePlants.lettuce));
        assertTrue(veggies.contains(StorePlants.eggplant));
        assertTrue(veggies.contains(StorePlants.garlic));
        assertFalse(veggies.contains(StorePlants.forget_me_not));
        assertFalse(veggies.contains(StorePlants.cactus));
    }

    @Test
    public void TestGetFlowers() {
        ArrayList<Plant> flowers = store.getFlowers();
        assertEquals(5, flowers.size());

        assertTrue(flowers.contains(StorePlants.forget_me_not));
        assertTrue(flowers.contains(StorePlants.cactus));
        assertTrue(flowers.contains(StorePlants.rose));
        assertTrue(flowers.contains(StorePlants.sunflower));
        assertTrue(flowers.contains(StorePlants.lavender));

        assertFalse(flowers.contains(StorePlants.garlic));
        assertFalse(flowers.contains(StorePlants.carrot));


    }

}
