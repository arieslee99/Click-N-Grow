package model.seeds;

import model.Plant;
import model.PlantType;

public class Garlic extends Plant {
    public Garlic(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Garlic cloneObject() {
        return new Garlic(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }

}
