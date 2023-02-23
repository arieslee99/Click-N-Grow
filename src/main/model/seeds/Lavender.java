package model.seeds;

import model.Plant;
import model.PlantType;


public class Lavender extends Plant {

    public Lavender(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Lavender cloneObject() {
        return new Lavender(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }


}
