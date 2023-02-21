package model;

import model.SeedCatagloue.*;

import java.util.ArrayList;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;

public class Store {
    private final ArrayList<Plant> store;

    //EFFECTS: construct a store
    public Store() {
        store = new ArrayList<>();

        store.add(new Lavender("Lavender", 5, 6, 500, FLOWER));
        store.add(new Rose("Rose", 6, 6, 600, FLOWER));
        store.add(new Forget_Me_Not("Forget Me Not", 3, 6, 500, FLOWER));
        store.add(new Sunflower("Sunflower", 8, 9, 300, FLOWER));
        store.add(new Cactus("Cactus", 1, 2, 100, FLOWER));
        store.add(new Carrot("Carrot", 5, 7, 200, VEGETABLE));
        store.add(new Potato("Potato", 3, 5, 100, VEGETABLE));
        store.add(new Lettuce("Lettuce", 8, 3, 400, VEGETABLE));
        store.add(new Eggplant("Eggplant", 4, 5, 300, VEGETABLE));
        store.add(new Garlic("Garlic", 2, 3, 100, VEGETABLE));

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

