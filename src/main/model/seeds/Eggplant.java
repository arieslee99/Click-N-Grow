package model.seeds;

import model.Plant;
import model.PlantType;

public class Eggplant extends Plant {
    public Eggplant(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Eggplant cloneObject() {
        return new Eggplant(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }
}