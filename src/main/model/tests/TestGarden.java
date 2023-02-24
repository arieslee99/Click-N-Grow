package model.tests;

import model.Garden;
import model.Plant;
import model.seeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.*;

public class TestGarden {

    private Garden garden;
    private final Eggplant eggplant = new Eggplant("Eggplant", 4, 5, 300, VEGETABLE);
    private final Potato potato = new Potato("Potato", 3, 5, 100, VEGETABLE);
    private final Lavender lavender = new Lavender("Lavender", 5, 6, 500, FLOWER);
    private final Cactus cactus = new Cactus("Cactus", -1, -2, 100, FLOWER);
    private final Lettuce lettuce = new Lettuce("Lettuce", 8, 3, 400, VEGETABLE);
    private final ForgetMeNot forgetMeNot
            = new ForgetMeNot("Forget Me Not", 3, -9, 500, FLOWER);


    @BeforeEach
    public void setup() {
        garden = new Garden("David's Garden");
    }

    @Test
    public void testConstructor() {
        assertEquals("David's Garden", garden.getGardenName());
        assertEquals(0, garden.getSize());
    }

    @Test
    public void testSetGardenName() {
        garden.setGardenName("Aries' Garden");
        assertEquals("Aries' Garden", garden.getGardenName());
    }

    @Test
    public void testAddPlantMultipleTimes() {
        garden.addPlant(eggplant);
        assertEquals(1, garden.getSize());
        garden.addPlant(potato);
        assertEquals(2, garden.getSize());
    }

    @Test
    public void testRemoveOnePlant() {
        garden.addPlant(lavender);
        garden.addPlant(cactus);
        assertEquals(2, garden.getSize());
        garden.removePlant(0);
        assertEquals(1, garden.getSize());
        assertEquals(cactus, garden.getPlant(0));
    }

    @Test
    public void testRemoveMultiplePlants() {
        garden.addPlant(lavender);
        garden.addPlant(cactus);
        assertEquals(2, garden.getSize());
        garden.removePlant(0);
        assertEquals(1, garden.getSize());
        garden.removePlant(0);
        assertEquals(0, garden.getSize());
    }

    @Test
    public void testRemoveDeadPlants() {
        garden.addPlant(new Eggplant("Eggplant", -1, 0, 300, VEGETABLE));
        garden.addPlant(forgetMeNot);
        garden.addPlant(new Cactus("Cactus", -1, -3, 100, FLOWER));
        garden.addPlant(potato);
        assertEquals(4, garden.getSize());
        garden.removeDeadPlants();
        assertEquals(1, garden.getSize());

    }

    @Test
    public void testGetDeadPlantsNumber() {
        garden.addPlant(new Rose("Rose", -1,-1,600, FLOWER));
        garden.addPlant(new Garlic("Garlic", -5,-6,100, VEGETABLE));
        garden.addPlant(new Rose("Rose", 0,0,100, FLOWER));

        assertEquals(3, garden.getSize());
        assertEquals(2, garden.getNumOfDeadPlants());
    }

    @Test
    public void testGetPlant() {
        garden.addPlant(lavender);
        garden.addPlant(lettuce);
        assertEquals(2, garden.getSize());
        assertEquals(lavender, garden.getPlant(0));
        assertEquals(lettuce, garden.getPlant(1));
    }

    @Test
    public void testGetGarden() {
        ArrayList<Plant> emptyPlants = garden.getGarden();
        assertEquals(0, emptyPlants.size());

        garden.addPlant(lavender);
        garden.addPlant(lettuce);
        ArrayList<Plant> plants = garden.getGarden();
        assertEquals(lavender, plants.get(0));
        assertEquals(lettuce, plants.get(1));
    }

    @Test
    public void testEmptyGarden() {
        garden.addPlant(lavender);
        garden.addPlant(lettuce);
        assertEquals(2, garden.getSize());
        garden.emptyGarden();
        assertEquals(0, garden.getSize());
    }
}
