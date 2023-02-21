package model.SeedCatagloue;

import model.Plant;
import model.PlantType;

public class Rose extends Plant {
    public Rose(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }
}
