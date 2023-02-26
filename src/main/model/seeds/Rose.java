package model.seeds;

import model.Plant;
import model.PlantType;

public class Rose extends Plant {

    //EFFECTS: constructs a rose
    public Rose(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current rose
    @Override
    public Rose cloneObject() {
        return new Rose(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of rose after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 300;
        return getPrice() + profit;
    }
}
