package model;

import java.util.ArrayList;

public enum PlantSupply {
    LAVENDER,
    ROSE,
    FORGET_ME_NOT,
    SUNFLOWER,
    CACTUS,
    TOMATO,
    POTATO,
    LETTUCE,
    EGGPLANT,
    GARLIC;

    public void PlantSupply() {
        switch (this) {
            case LAVENDER:
                Plant lavender = new Plant("Lavender", 5, 6, 500);
            case ROSE:
                Plant rose = new Plant("Rose", 6, 6, 600);
            case FORGET_ME_NOT:
                Plant forget_me_not = new Plant("Forget Me Not", 3, 6, 500);
            case SUNFLOWER:
                Plant sunflower = new Plant("Sunflower", 8, 9, 300);
            case CACTUS:
                Plant cactus = new Plant("Cactus", 1, 2, 100);
            case TOMATO:
                Plant tomato = new Plant("Tomato", 5, 7, 200);
            case POTATO:
                Plant potato = new Plant("Potato", 3, 5, 100);
            case LETTUCE:
                Plant lettuce = new Plant("Lettuce", 8, 3, 400);
            case EGGPLANT:
                Plant eggplant = new Plant("Eggplant", 4, 5, 300);
            case GARLIC:
                Plant garlic = new Plant("Garlic", 2, 3, 100);

        }

    }
}
