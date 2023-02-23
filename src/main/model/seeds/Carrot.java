package model.seeds;

import model.Plant;
import model.PlantType;

import static model.PlantType.FLOWER;

public class Carrot extends Plant {

    public Carrot(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    public Carrot cloneObject() {
        return new Carrot(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    @Override
    public Integer getProfitValue() {
        int profit = 150;
        return getPrice() + profit;
    }
}
