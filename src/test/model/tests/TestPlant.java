package model.tests;

import model.Plant;
import model.PlantType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlant {

    private Plant garlic;
    private Plant tomato;
    private Plant potato;

    @BeforeEach
    public void setup() {
        garlic = new Plant("Garlic", 3, 4, 100, PlantType.VEGETABLE);
        tomato = new Plant("Tomato", 1, 1, 200, PlantType.VEGETABLE);
        potato = new Plant("Potato", 2, 0, 300, PlantType.VEGETABLE );
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
        tomato.feedPlant();
        tomato.feedPlant();
        assertEquals(-1, tomato.getFertilizerCount());
        tomato.updateLifeStatus();
        assertEquals("Dead!", tomato.getLifeStatus());
    }

    @Test
    public void testUpdateLifeStatusIsRipe() {
        tomato.feedPlant();
        tomato.waterPlant();
        assertEquals(0, tomato.getWaterCount());
        assertEquals(0, tomato.getFertilizerCount());
        tomato.updateLifeStatus();
        assertEquals("Ripe!", tomato.getLifeStatus());

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
        tomato.feedPlant();
        assertEquals(0, tomato.getFertilizerCount());
        tomato.updateLifeStatus();
        assertEquals("Growing!", tomato.getLifeStatus());
    }

}