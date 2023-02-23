package model.seeds;

import model.Plant;
import model.PlantType;

public class Corn extends Plant {
    public Corn(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        super(name, waterCount, fertilizerCount, price, type);
    }

    @Override
    public Plant cloneObject() {
        return null;
    }

    @Override
    public Integer getProfitValue() {
        return null;
    }

}
