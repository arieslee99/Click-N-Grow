package model.SeedCatagloue;

import model.Plant;
import model.PlantType;

public class Carrot extends Plant {

    public Carrot(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Integer getProfitValue() {
        Integer PROFIT = 150;
        return getPrice() + PROFIT;
    }
}
