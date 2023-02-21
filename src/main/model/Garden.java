package model;

import java.util.ArrayList;

public class Garden {
    private ArrayList<Plant> garden;
    private Integer size;
    private String gardenName;

    //EFFECTS: construct a garden
    public Garden(String name) {
        garden = new ArrayList<>();
        size = 0;
        gardenName = name;
    }

    //MODIFIES: this
    //EFFECTS: adds a plant to garden
    public void addPlant(Plant p) {
        garden.add(p);
    }

    //REQUIRES: garden must have at least one plant in it
    //MODIFIES: this
    //EFFECTS: removes a plant from garden
    public void removePlant(Plant plant) {
        ArrayList<String> plantNames = new ArrayList<>();
        for (Plant p : garden) {
            plantNames.add(p.getPlantName());
        }

        for (int i = 0; i < plantNames.size(); i++) {
            if (plant.getPlantName().equals(plantNames.get(i))) {
                garden.remove(i);
                break;
            }
        }

    }

    //REQUIRES: garden has at least one dead plant in it
    //MODIFIES: this
    //EFFECTS: removes all the "Dead!" plants from garden
    public void removeDeadPlants() {
        ArrayList<Plant> noDeadPlants = new ArrayList<>();
        for (Plant p : garden) {
            p.updateLifeStatus();
            if (!p.getLifeStatus().equals("Dead!")) {
                noDeadPlants.add(p);
            }
        }
        garden = noDeadPlants;

    }

    //getters
    public Integer getSize() {
        return garden.size();
    }

    public String getGardenName() {
        return gardenName;
    }


    //EFFECTS: return true if plant exits in the garden; else, false
    public Boolean getPlant(Plant plant) {
        for(Plant p: garden) {
            if(p.getPlantName().equals(plant.getPlantName())) {
                return true;
            }
        }
        return false;
    }

    public Integer getNumOfDeadPlants() {
        int count = 0;
        for(Plant plant: garden) {
            if (plant.getLifeStatus().equals("Dead!")) {
                count++;
            }
        }
        return count;
    }


}
