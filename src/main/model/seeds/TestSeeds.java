package model.seeds;

import model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.PlantType.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestSeeds {
    Plant lavender;
    Plant rose;
    Plant forgetMeNot;
    Plant sunflower;
    Plant cactus;
    Plant carrot;
    Plant potato;
    Plant lettuce;
    Plant eggplant;
    Plant garlic;
    Plant corn;

    @BeforeEach
    public void setup() {
        lavender = new Lavender("Lavender", 5, 6, 500, FLOWER);
        rose = new Rose("Rose", 6, 6, 600, FLOWER);
        forgetMeNot = new ForgetMeNot("Forget Me Not", 3, 6, 500, FLOWER);
        sunflower = new Sunflower("Sunflower", 8, 9, 300, FLOWER);
        cactus = new Cactus("Cactus", 1, 2, 100, FLOWER);
        carrot = new Carrot("Carrot", 5, 7, 200, VEGETABLE);
        potato = new Potato("Potato", 3, 5, 100, VEGETABLE);
        lettuce = new Lettuce("Lettuce", 8, 3, 400, VEGETABLE);
        eggplant = new Eggplant("Eggplant", 4, 5, 300, VEGETABLE);
        garlic = new Garlic("Garlic", 2, 3, 100, VEGETABLE);
        corn = new Corn("Corn", 0, 0, 0, VEGETABLE);
    }

    @Test
    public void testCloneObjects() {
        Plant newCactus = cactus.cloneObject();
        assertEquals(newCactus, cactus);

        Plant newRose = rose.cloneObject();
        assertEquals(newRose, rose);

        Plant newForgetMeNot = forgetMeNot.cloneObject();
        assertEquals(newForgetMeNot, forgetMeNot);

        Plant newSunflower = sunflower.cloneObject();
        assertEquals(newSunflower, sunflower);

        Plant newLavender = lavender.cloneObject();
        assertEquals(newLavender, lavender);

        Plant newCarrot = carrot.cloneObject();
        assertEquals(newCarrot, carrot);

        Plant newPotato = potato.cloneObject();
        assertEquals(newPotato, potato);

        Plant newLettuce = lettuce.cloneObject();
        assertEquals(newLettuce, lettuce);

        Plant newEggplant = eggplant.cloneObject();
        assertEquals(newEggplant, eggplant);

        Plant newGarlic = garlic.cloneObject();
        assertEquals(newGarlic, garlic);

        Plant newCorn = corn.cloneObject();
        assertNull(newCorn);

    }

    @Test
    public void testGetProfitValue() {
        assertEquals(200, garlic.getProfitValue());
        assertEquals(700, lavender.getProfitValue());
        assertEquals(900, rose.getProfitValue());
        assertEquals(750, forgetMeNot.getProfitValue());
        assertEquals(500, sunflower.getProfitValue());
        assertEquals(200, cactus.getProfitValue());
        assertEquals(350, carrot.getProfitValue());
        assertEquals(200, potato.getProfitValue());
        assertEquals(700, lettuce.getProfitValue());
        assertEquals(500, eggplant.getProfitValue());
        assertNull(corn.getProfitValue());
    }
}
