package model;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;

public class StorePlants {

    protected static final Plant lavender =
            new Plant("Lavender", 5, 6, 500, FLOWER);
    protected static final Plant forget_me_not =
            new Plant ("Forget Me Not", 3, 6, 500, FLOWER);
    protected static final Plant sunflower =
            new Plant("Sunflower", 8, 9, 300, FLOWER);
    protected static final Plant rose = new Plant("Rose", 6, 6, 600, FLOWER);
    protected static final Plant cactus = new Plant("Cactus", 1, 2, 100, FLOWER);
    protected static final Plant carrot =
            new Plant("Carrot", 5, 7, 200, VEGETABLE);
    protected static final Plant potato = new Plant("Potato", 3, 5, 100, VEGETABLE);
    protected static final Plant lettuce =
            new Plant("Lettuce", 8, 3, 400, VEGETABLE);
    protected static final Plant eggplant =
            new Plant("Eggplant", 4, 5, 300, VEGETABLE);
    protected static final Plant garlic = new Plant("Garlic", 2, 3, 100, VEGETABLE);

}
