package model;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;

public class Store {
    private final ArrayList<Plant> store;

    //EFFECTS: construct a store
    public Store() {
        store = new ArrayList<>();

        store.add(StorePlants.lavender);
        store.add(StorePlants.rose);
        store.add(StorePlants.forget_me_not);
        store.add(StorePlants.sunflower);
        store.add(StorePlants.cactus);
        store.add(StorePlants.carrot);
        store.add(StorePlants.potato);
        store.add(StorePlants.lettuce);
        store.add(StorePlants.eggplant);
        store.add(StorePlants.garlic);

    }

    //EFFECTS: returns a message in string indicating whether plant is present in store
    public String searchForPlant(Plant plant) {
        for (Plant p : store) {
            if (plant.getPlantName().equals(p.getPlantName())) {
                return "In stock :)";
            }
        }
        return "Not in stock :(";
    }


    //getters

    //EFFECTS: returns list of plant names in store; returns an empty list
    public ArrayList<String> getPlantNames() {
        ArrayList<String> plantNames = new ArrayList<>();
        for (Plant plant : store) {
            plantNames.add(plant.getPlantName());
        }
        return plantNames;
    }

    //EFFECTS: returns a list of only vegetables
    public ArrayList<Plant> getVegetables() {
        ArrayList<Plant> veggies = new ArrayList<>();

        for(Plant plant: store) {
            if (plant.getType() == VEGETABLE) {
                veggies.add(plant);
            }
        }
        return veggies;
    }

    //EFFECTS: returns a list of only flowers
    public ArrayList<Plant> getFlowers() {
        ArrayList<Plant> flowers = new ArrayList<>();
        for(Plant plant: store) {
            if (plant.getType() == (FLOWER)) {
                flowers.add(plant);
            }
        }
        return flowers;
    }

    public ArrayList<Plant> getStore() {
        return store;
    }
}

