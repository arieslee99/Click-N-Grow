package model.seeds;

import model.Plant;
import model.PlantType;

public class Corn extends Plant {

    //EFFECTS: constructs a corn
    public Corn(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    //EFFECTS: clones the current corn
    @Override
    public Plant cloneObject() {
        return null;
    }

    //MODIFIES: this
    //EFFECTS: returns value of corn after its sold; adds profit margin to its price
    @Override
    public Integer getProfitValue() {
        return null;
    }

}
