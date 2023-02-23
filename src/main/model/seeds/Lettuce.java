package model.seeds;

import model.Plant;
import model.PlantType;

public class Lettuce extends Plant {

    //EFFECTS: constructs a lettuce
    public Lettuce(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current lettuce
    @Override
    public Lettuce cloneObject() {
        return new Lettuce(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //EFFECTS: returns value of lettuce after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 300;
        return getPrice() + profit;
    }
}
