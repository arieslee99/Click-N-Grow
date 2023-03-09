package model.tests;

import model.Garden;
import model.Plant;
import model.PlantType;
import model.seeds.*;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @Test
    public void testSearchForPlant() {
        boolean fail1 = garden.searchForPlant("lavender");
        boolean fail2 = garden.searchForPlant("Lavender");
        assertFalse(fail1);
        assertFalse(fail2);

        garden.addPlant(lettuce);
        boolean success1 = garden.searchForPlant("Lettuce");
        boolean success2 = garden.searchForPlant("lettuce");
        assertTrue(success1);
        assertTrue(success2);

        boolean fail3 = garden.searchForPlant("cactus");
        assertFalse(fail3);




    }

    @Test
    public void testTranslateToJson() {
        garden.addPlant(lavender);
        garden.addPlant(eggplant);
        JSONObject jsonObject = garden.translateToJson();
        String jsonGardenName = jsonObject.getString("name");
        JSONArray jsonPlants = jsonObject.getJSONArray("plants");
        JSONObject plant = (JSONObject) jsonPlants.get(0);
        JSONObject plant1 = (JSONObject) jsonPlants.get(1);

        assertEquals("David's Garden", jsonGardenName);
        assertEquals("Lavender", plant.getString("name"));
        assertEquals(5, plant.getInt("water count"));
        assertEquals(6, plant.getInt("fertilizer count"));
        assertEquals(500, plant.getInt("price"));
        PlantType actualPlantType = translatePlantTypeStringToPlantType(plant.getString("plant type"));
        assertEquals(FLOWER, actualPlantType);

        assertEquals("Eggplant", plant1.getString("name"));
        assertEquals(4, plant1.getInt("water count"));
        assertEquals(5, plant1.getInt("fertilizer count"));
        assertEquals(300, plant1.getInt("price"));
        PlantType actualPlantType1 = translatePlantTypeStringToPlantType(plant1.getString("plant type"));
        assertEquals(VEGETABLE, actualPlantType1);
    }

    //EFFECTS: changes name of plant type to corresponding plant type
    private PlantType translatePlantTypeStringToPlantType(String name) {
        if (name.equalsIgnoreCase("Vegetable")) {
            return VEGETABLE;
        } else {
            return FLOWER;
        }
    }

}
