package model;

import java.util.ArrayList;

public class Store {
    private ArrayList<PlantSupply> store;

    //EFFECTS: construct a store
    public Store() {
        store = new ArrayList<>();
        store.add(PlantSupply.LAVENDER);
        store.add(PlantSupply.FORGET_ME_NOT);
        store.add(PlantSupply.ROSE);
        store.add(PlantSupply.SUNFLOWER);
        store.add(PlantSupply.CACTUS);
        store.add(PlantSupply.LETTUCE);
        store.add(PlantSupply.EGGPLANT);
        store.add(PlantSupply.GARLIC);
        store.add(PlantSupply.POTATO);
        store.add(PlantSupply.TOMATO);
    }

    //EFFECTS: search for plant in store; if present, return true; else, false
    public String searchForPlant(Plant plant) {
        for(PlantSupply p: store) {
            if (plant.getPlantName().equals(p.toString().toLowerCase())) {
                return "In stock :) ";
            }
        }
        return "Not in stock :( ";
    }

    //getter
    public ArrayList<PlantSupply> getStore() {
        return store;
    }
}
