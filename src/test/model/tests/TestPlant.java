package model.tests;

import model.Garden;
import model.Plant;
import model.seeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlant {

    private Plant garlic;
    private Plant carrot;
    private Plant potato;
    private Plant cactus;
    private Plant lettuce;
    private Plant lettuce2;

    @BeforeEach
    public void setup() {
        garlic = new Garlic("Garlic", 3, 4, 100, VEGETABLE);
        carrot = new Carrot("Carrot", 1, 1, 200, VEGETABLE);
        potato = new Potato("Potato", 2, 0, 300, VEGETABLE);
        cactus = new Cactus("Cactus", 1, 2, 100, FLOWER);
        lettuce = new Lettuce("Lettuce", 0, 0, 400, VEGETABLE);
        lettuce2 = new Lettuce("Lettuce", 0, -1, 400, VEGETABLE);

    }

    @Test
    public void testConstructor() {
        assertEquals("Garlic", garlic.getPlantName());
        assertEquals(3, garlic.getWaterCount());
        assertEquals(4, garlic.getFertilizerCount());
        assertEquals("Growing!", garlic.getLifeStatus());
        assertEquals(100, garlic.getPrice());

        assertEquals(0, lettuce.getWaterCount());
        assertEquals(0, lettuce.getFertilizerCount());
        assertEquals("Ripe!", lettuce.getLifeStatus());

        assertEquals(-1, lettuce2.getFertilizerCount());
        assertEquals(0, lettuce2.getWaterCount());
        assertEquals("Dead!", lettuce2.getLifeStatus());
    }

    @Test
    public void testWaterPlantMultipleTimesAndLive() {
        garlic.waterPlant();
        garlic.waterPlant();
        assertEquals(1, garlic.getWaterCount());
        garlic.updateLifeStatus();
        assertEquals("Growing!", garlic.getLifeStatus());
    }

    @Test
    public void testWaterPlantMultipleTimesAndDie() {
        garlic.waterPlant();
        garlic.waterPlant();
        garlic.waterPlant();
        garlic.waterPlant();
        assertEquals(-1, garlic.getWaterCount());
        garlic.updateLifeStatus();
        assertEquals("Dead!", garlic.getLifeStatus());

    }

    @Test
    public void testFeedPlantMultipleTimesAndLive() {
        garlic.feedPlant();
        garlic.feedPlant();
        assertEquals(2, garlic.getFertilizerCount());
        garlic.updateLifeStatus();
        assertEquals("Growing!", garlic.getLifeStatus());
    }

    @Test
    public void testFeedPlantMultipleTimesAndDie() {
        carrot.feedPlant();
        carrot.feedPlant();
        assertEquals(-1, carrot.getFertilizerCount());
        carrot.updateLifeStatus();
        assertEquals("Dead!", carrot.getLifeStatus());
    }

    @Test
    public void testUpdateLifeStatusIsRipe() {
        carrot.feedPlant();
        carrot.waterPlant();
        assertEquals(0, carrot.getWaterCount());
        assertEquals(0, carrot.getFertilizerCount());
        carrot.updateLifeStatus();
        assertEquals("Ripe!", carrot.getLifeStatus());

    }

    @Test
    public void testUpdateLifeStatusAlmostRipe() {
        potato.waterPlant();
        assertEquals(1, potato.getWaterCount());
        potato.updateLifeStatus();
        assertEquals("Growing!", potato.getLifeStatus());
    }

    @Test
    public void testUpdateLifeStatusIsGrowing() {
        carrot.feedPlant();
        assertEquals(0, carrot.getFertilizerCount());
        carrot.updateLifeStatus();
        assertEquals("Growing!", carrot.getLifeStatus());

        assertEquals(4, garlic.getFertilizerCount());
        assertEquals(3, garlic.getWaterCount());
        garlic.updateLifeStatus();
        assertEquals("Growing!", garlic.getLifeStatus());
    }

    @Test
    public void testGetPlantProfit() {
        assertEquals(350, carrot.getProfitValue());
        assertEquals(200, garlic.getProfitValue());
    }

    @Test
    public void testGetPlantType() {
        assertEquals(VEGETABLE, carrot.getType());
        assertEquals(FLOWER, cactus.getType());
    }

    @Test
    public void testOverrideEquals() {
        Garlic garlic2  = new Garlic("Garlic2", 3, 4, 100, VEGETABLE);
        Garlic garlic3  = new Garlic("Garlic", -2, -1, 100, VEGETABLE);
        Carrot carrot2 = new Carrot("Carrot", 2, 1, 200, VEGETABLE);
        Potato potato2 = new Potato("Potato", 2, 1, 300, VEGETABLE);
        Cactus cactus2 = new Cactus("Cactus", 1, 2, 99, FLOWER);
        Cactus cactus3 = new Cactus("Cactus", 1, 2, 100, VEGETABLE);
        Cactus cactus4 = new Cactus("Cactus", 0, 0, 100, FLOWER);
        Cactus cactus5 = new Cactus("Cactus", -3, 0, 100, FLOWER);
        Garden garden = new Garden("Daries");

        assertNotEquals(garden, garlic);
        assertNotEquals(carrot, garlic);
        assertNotEquals(garlic2, garlic);
        assertNotEquals(carrot2, carrot);
        assertNotEquals(potato2, potato);
        assertNotEquals(cactus2, cactus);
        assertNotEquals(cactus3, cactus);
        assertNotEquals(null, garlic);
        assertNotEquals(garlic, null);
        assertNotEquals(cactus4, cactus); //ripe vs. growing
        assertNotEquals(garlic3, garlic); //dead vs. growing
        assertNotEquals(garlic3, cactus5); //dead vs. dead but diff names

    }

}