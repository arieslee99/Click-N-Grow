package model.seeds;

import model.Plant;
import model.PlantType;

public class Lettuce extends Plant {
    public Lettuce(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Lettuce cloneObject() {
        return new Lettuce(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 300;
        return getPrice() + profit;
    }
}
