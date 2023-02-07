package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGarden {

    private Garden garden1;
    private Garden garden2;

    @BeforeEach
    public void setup() {
        garden1 = new Garden("David's Garden");
        garden2 = new Garden("The Pod");
    }

    @Test
    public void TestConstructor() {
        assertEquals("David's Garden", garden1.getGardenName());
        assertEquals(0, garden1.getSize());
    }

    @Test
    public void TestAddPlantMultipleTimes() {
        garden1.addPlant(new Plant("Rosemary", 1,4));
        assertEquals(1, garden1.getSize());
        garden1.addPlant(new Plant("Potato", 10,11));
        assertEquals(2, garden1.getSize());
    }

    @Test
    public void TestRemoveOnePlant() {
        garden1.addPlant(new Plant("Green Onion", 4, 4));
        garden1.addPlant(new Plant("Pineapple", 20, 23));
        garden1.removePlant(new Plant("Green Onion", 4, 4));
        assertEquals(1, garden1.getSize());

    }

    @Test
    public void TestRemoveMultiplePlants() {
        garden1.addPlant(new Plant("Green Onion", 4, 4));
        garden1.addPlant(new Plant("Pineapple", 20, 23));
        garden1.addPlant(new Plant("Lavender", 21, 21));
        assertEquals(3,garden1.getSize());
        garden1.removePlant(new Plant("Green Onion", 4, 4));
        garden1.removePlant(new Plant("Pineapple", 20, 23));
        assertEquals(1, garden1.getSize());
    }

    @Test
    public void TestRemoveDeadPlants() {
        garden1.addPlant(new Plant("Strawberry", -1, 0));
        garden1.addPlant(new Plant("Starfruit", 3, -9));
        garden1.addPlant(new Plant("Cactus", -1, -3));
        garden1.addPlant(new Plant("Lime", 4, 2));
        assertEquals(4, garden1.getSize());
        garden1.removeDeadPlants();
        assertEquals(1, garden1.getSize());

    }


}
