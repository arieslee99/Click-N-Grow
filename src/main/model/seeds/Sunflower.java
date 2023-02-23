package model.seeds;

import model.Plant;
import model.PlantType;

public class Sunflower extends Plant {
    public Sunflower(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Sunflower cloneObject() {
        return new Sunflower(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }
}
