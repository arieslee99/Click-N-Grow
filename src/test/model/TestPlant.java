package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlant {

    private Plant garlic;
    private Plant tomato;
    private Plant rice;

    @BeforeEach
    public void setup() {
        garlic = new Plant("Garlic", 3, 4);
        tomato = new Plant("Tomato", 1, 1);
        rice = new Plant("Rice", 2, 0);

    }

    @Test
    public void testConstructor() {
        assertEquals("Garlic", garlic.getPlantName());
        assertEquals(3, garlic.getWaterCount());
        assertEquals(4, garlic.getFertilizerCount());
        assertEquals("Growing!", garlic.getLifeStatus());
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
        rice.waterPlant();
        assertEquals(1, rice.getWaterCount());
        rice.updateLifeStatus();
        assertEquals("Growing!", rice.getLifeStatus());
    }

    @Test
    public void testUpdateLifeStatusIsGrowing() {
        tomato.feedPlant();
        assertEquals(0, tomato.getFertilizerCount());
        tomato.updateLifeStatus();
        assertEquals("Growing!", tomato.getLifeStatus());
    }

}