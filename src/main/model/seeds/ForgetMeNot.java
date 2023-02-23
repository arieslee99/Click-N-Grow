package model.seeds;

import model.Plant;
import model.PlantType;

public class ForgetMeNot extends Plant {
    public ForgetMeNot(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public ForgetMeNot cloneObject() {
        return new ForgetMeNot(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 250;
        return getPrice() + profit;
    }
}
