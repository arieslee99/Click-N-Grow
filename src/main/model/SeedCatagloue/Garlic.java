package model.SeedCatagloue;

import model.Plant;
import model.PlantType;

public class Garlic extends Plant {
    public Garlic(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Integer getProfitValue() {
        Integer PROFIT = 100;
        return getPrice() + PROFIT;
    }
}
