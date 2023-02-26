package model.seeds;

import model.Plant;
import model.PlantType;

public class Carrot extends Plant {

    //EFFECTS: constructs a carrot
    public Carrot(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current carrot
    public Carrot cloneObject() {
        return new Carrot(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of carrot after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 150;
        return getPrice() + profit;
    }
}
