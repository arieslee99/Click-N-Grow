package model.tests;

import model.Plant;
import model.PlantType;
import model.SeedCatagloue.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlant {

    private Plant garlic;
    private Plant carrot;
    private Plant potato;

    @BeforeEach
    public void setup() {
        garlic = new Garlic("Garlic", 3, 4, 100, PlantType.VEGETABLE);
        carrot = new Carrot("Tomato", 1, 1, 200, PlantType.VEGETABLE);
        potato = new Potato("Potato", 2, 0, 300, PlantType.VEGETABLE );
    }

    @Test
    public void testConstructor() {
        assertEquals("Garlic", garlic.getPlantName());
        assertEquals(3, garlic.getWaterCount());
        assertEquals(4, garlic.getFertilizerCount());
        assertEquals("Growing!", garlic.getLifeStatus());
        assertEquals(100, garlic.getPrice());
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
    }

}