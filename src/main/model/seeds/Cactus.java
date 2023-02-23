package model.seeds;

import model.Plant;
import model.PlantType;

import static model.PlantType.FLOWER;

public class Cactus extends Plant {
    public Cactus(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    public Cactus cloneObject() {
        return new Cactus(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }
}
