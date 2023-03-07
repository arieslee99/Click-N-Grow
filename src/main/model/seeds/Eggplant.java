package model.seeds;

import model.Plant;
import model.PlantType;

public class Eggplant extends Plant {

    //EFFECTS: constructs an eggplant
    public Eggplant(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    //EFFECTS: clones eggplant
    public Eggplant cloneObject() {
        return new Eggplant(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of plant after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }
}
