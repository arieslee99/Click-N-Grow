package model.seeds;

import model.Plant;
import model.PlantType;

public class Potato extends Plant {
    public Potato(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Potato cloneObject() {
        return new Potato(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }
}
