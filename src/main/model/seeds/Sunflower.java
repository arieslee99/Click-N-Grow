package model.seeds;

import model.Plant;
import model.PlantType;

public class Sunflower extends Plant {

    //EFFECTS: constructs a sunflower
    public Sunflower(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current sunflower
    @Override
    public Sunflower cloneObject() {
        return new Sunflower(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of sunflower after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }
}
