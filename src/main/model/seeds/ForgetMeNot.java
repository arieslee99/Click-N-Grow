package model.seeds;

import model.Plant;
import model.PlantType;

public class ForgetMeNot extends Plant {

    //EFFECTS: constructs a forget-me-not
    public ForgetMeNot(String name, int waterCount, int fertilizerCount, int price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current forget-me-not
    @Override
    public ForgetMeNot cloneObject() {
        return new ForgetMeNot(this.getPlantName(), this.getWaterCount(), this.getFertilizerCount(), this.getPrice(),
                this.getType());
    }

    //MODIFIES: this
    //EFFECTS: returns value of forget-me-not after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        int profit = 250;
        return getPrice() + profit;
    }
}
