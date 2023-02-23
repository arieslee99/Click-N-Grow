package model.seeds;

import model.Plant;
import model.PlantType;

public class Forget_Me_Not extends Plant {
    public Forget_Me_Not(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Forget_Me_Not cloneObject() {
        return new Forget_Me_Not(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 250;
        return getPrice() + profit;
    }
}
