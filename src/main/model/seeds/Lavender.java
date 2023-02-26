package model.seeds;

import model.Plant;
import model.PlantType;


public class Lavender extends Plant {

    //EFFECTS: constructs a lavender
    public Lavender(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current lavender
    @Override
    public Lavender cloneObject() {
        return new Lavender(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of lavender after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 200;
        return getPrice() + profit;
    }


}
