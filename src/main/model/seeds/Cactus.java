package model.seeds;

import model.Plant;
import model.PlantType;

public class Cactus extends Plant {

    //EFFECTS: constructs a cactus
    public Cactus(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current cactus
    public Cactus cloneObject() {
        return new Cactus(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //EFFECTS: returns value of plant after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 100;
        return getPrice() + profit;
    }
}
