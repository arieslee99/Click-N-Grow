package model.SeedCatagloue;

import model.Plant;
import model.PlantType;

public class Rose extends Plant {
    public Rose(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Integer getProfitValue() {
        Integer PROFIT = 300;
        return getPrice() + PROFIT;
    }
}
