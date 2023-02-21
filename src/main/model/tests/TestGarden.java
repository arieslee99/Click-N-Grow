package model.tests;

import model.Garden;
import model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGarden {

    private Garden garden1;
    private Garden garden2;
    private Plant eggplant = new Plant("Eggplant", 4, 5, 300, VEGETABLE);
    private Plant potato = new Plant("Potato", 3, 5, 100, VEGETABLE);
    private Plant lavender = new Plant("Lavender", 5, 6, 500, FLOWER);
    private Plant cactus = new Plant("Cactus", -1, -2, 100, FLOWER);
    private Plant lettuce = new Plant("Lettuce", 8, 3, 400, VEGETABLE);
    private Plant forgetMeNot = new Plant("Forget Me Not", 3, -9, 500, FLOWER);




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
        garden1.addPlant(eggplant);
        assertEquals(1, garden1.getSize());
        garden1.addPlant(potato);
        assertEquals(2, garden1.getSize());
    }

    @Test
    public void TestRemoveOnePlant() {
        garden1.addPlant(lavender);
        garden1.addPlant(cactus);
        garden1.removePlant(cactus);
        assertEquals(1, garden1.getSize());

    }

    @Test
    public void TestRemoveMultiplePlants() {
        garden1.addPlant(cactus);
        garden1.addPlant(potato);
        garden1.addPlant(lettuce);
        assertEquals(3,garden1.getSize());
        garden1.removePlant(potato);
        garden1.removePlant(lettuce);
        assertEquals(1, garden1.getSize());
    }

    @Test
    public void TestRemoveDeadPlants() {
        garden1.addPlant(new Plant("Eggplant", -1, 0, 300, VEGETABLE));
        garden1.addPlant(forgetMeNot);
        garden1.addPlant(new Plant("Cactus", -1, -3, 100, FLOWER));
        garden1.addPlant(potato);
        assertEquals(4, garden1.getSize());
        garden1.removeDeadPlants();
        assertEquals(1, garden1.getSize());

    }

    @Test
    public void TestGetDeadPlantsNumber() {
        garden1.addPlant(new Plant("Rose", -1,-1,600, FLOWER));
        garden1.addPlant(new Plant("Garlic", -5,-6,100, VEGETABLE));
        garden1.addPlant(new Plant("Rose", 0,0,100, FLOWER));

        assertEquals(3, garden1.getSize());
        assertEquals(2, garden1.getNumOfDeadPlants());


    }


}
