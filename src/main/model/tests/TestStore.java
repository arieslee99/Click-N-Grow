package model.tests;

import model.Plant;
import model.SeedCatagloue.*;
import model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.*;

public class TestStore {

    private Store store;
    private final Corn corn = new Corn("Corn", 6,7,500, VEGETABLE);
    private final Lavender lavender = new Lavender("Lavender", 5, 6, 500, FLOWER);
    private final Forget_Me_Not forget_me_not =
            new Forget_Me_Not ("Forget Me Not", 3, 6, 500, FLOWER);
    private final Sunflower sunflower =
            new Sunflower("Sunflower", 8, 9, 300, FLOWER);
    private final Rose rose = new Rose("Rose", 6, 6, 600, FLOWER);
    private final Cactus cactus = new Cactus("Cactus", 1, 2, 100, FLOWER);
    private final Carrot carrot =
            new Carrot("Carrot", 5, 7, 200, VEGETABLE);
    private final Potato potato = new Potato("Potato", 3, 5, 100, VEGETABLE);
    private final Lettuce lettuce = new Lettuce("Lettuce", 8, 3, 400, VEGETABLE);
    private final Eggplant eggplant = new Eggplant("Eggplant", 4, 5, 300, VEGETABLE);
    private final Garlic garlic = new Garlic("Garlic", 2, 3, 100, VEGETABLE);

    @BeforeEach
    public void setup() {
        store = new Store();
    }

    @Test
    public void TestSearchForPlantAndInStock() {
        boolean inStock = store.searchForPlant("Lavender");
        assertTrue(inStock);
    }

    @Test
    public void TestSearchForPlantAndNotInStock() {
        boolean inStock = store.searchForPlant("Corn");
        assertFalse(inStock);
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

        assertTrue(veggies.contains(carrot));
        assertTrue(veggies.contains(potato));
        assertTrue(veggies.contains(lettuce));
        assertTrue(veggies.contains(eggplant));
        assertTrue(veggies.contains(garlic));
        assertFalse(veggies.contains(forget_me_not));
        assertFalse(veggies.contains(cactus));
    }

    @Test
    public void TestGetFlowers() {
        ArrayList<Plant> flowers = store.getFlowers();
        assertEquals(5, flowers.size());

        assertEquals(lavender, flowers.get(0));
        assertTrue(flowers.contains(forget_me_not));
        assertTrue(flowers.contains(cactus));
        assertTrue(flowers.contains(rose));
        assertTrue(flowers.contains(sunflower));
        assertTrue(flowers.contains(lavender));

        assertFalse(flowers.contains(garlic));
        assertFalse(flowers.contains(carrot));


    }

}
