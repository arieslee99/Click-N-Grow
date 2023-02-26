package model.seeds;

import model.Plant;
import model.PlantType;

public class Garlic extends Plant {

    //EFFECTS: constructs a garlic
    public Garlic(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current garlic
    @Override
    public Garlic cloneObject() {
        return new Garlic(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of garlic after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }

}
