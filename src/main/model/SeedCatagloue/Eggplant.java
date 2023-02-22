package model.SeedCatagloue;

import model.Plant;
import model.PlantType;

public class Eggplant extends Plant {
    public Eggplant(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Integer getProfitValue() {
        Integer PROFIT = 200;
        return getPrice() + PROFIT;
    }
}
