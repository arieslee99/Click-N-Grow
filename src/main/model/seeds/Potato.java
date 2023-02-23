package model.seeds;

import model.Plant;
import model.PlantType;

public class Potato extends Plant {

    //EFFECTS: constructs a potato
    public Potato(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current potato
    @Override
    public Potato cloneObject() {
        return new Potato(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //EFFECTS: returns value of potato after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }
}
