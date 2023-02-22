package model.tests;

import model.Garden;
import model.SeedCatagloue.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private final Forget_Me_Not forgetMeNot
            = new Forget_Me_Not("Forget Me Not", 3, -9, 500, FLOWER);




    @BeforeEach
    public void setup() {
        garden = new Garden("David's Garden");
    }

    @Test
    public void TestConstructor() {
        assertEquals("David's Garden", garden.getGardenName());
        assertEquals(0, garden.getSize());
    }

    @Test
    public void TestAddPlantMultipleTimes() {
        garden.addPlant(eggplant);
        assertEquals(1, garden.getSize());
        garden.addPlant(potato);
        assertEquals(2, garden.getSize());
    }

    @Test
    public void TestRemoveOnePlant() {
        garden.addPlant(lavender);
        garden.addPlant(cactus);
        boolean success = garden.removePlant("Cactus");
        assertTrue(success);
        assertEquals(1, garden.getSize());
        assertTrue(garden.getPlant(lavender));
        assertFalse(garden.getPlant(cactus));

    }

    @Test
    public void TestRemoveMultiplePlants() {
        garden.addPlant(cactus);
        garden.addPlant(potato);
        garden.addPlant(lettuce);
        assertEquals(3, garden.getSize());
        boolean success1 = garden.removePlant("Potato");
        boolean success2 = garden.removePlant("Lettuce");
        assertTrue(success1);
        assertTrue(success2);

        assertEquals(1, garden.getSize());

        assertTrue(garden.getPlant(cactus));
        assertFalse(garden.getPlant(potato));
        assertFalse(garden.getPlant(lettuce));


    }

    @Test
    public void TestRemoveDeadPlants() {
        garden.addPlant(new Eggplant("Eggplant", -1, 0, 300, VEGETABLE));
        garden.addPlant(forgetMeNot);
        garden.addPlant(new Cactus("Cactus", -1, -3, 100, FLOWER));
        garden.addPlant(potato);
        assertEquals(4, garden.getSize());
        garden.removeDeadPlants();
        assertEquals(1, garden.getSize());

    }

    @Test
    public void TestGetDeadPlantsNumber() {
        garden.addPlant(new Rose("Rose", -1,-1,600, FLOWER));
        garden.addPlant(new Garlic("Garlic", -5,-6,100, VEGETABLE));
        garden.addPlant(new Rose("Rose", 0,0,100, FLOWER));

        assertEquals(3, garden.getSize());
        assertEquals(2, garden.getNumOfDeadPlants());


    }


}
