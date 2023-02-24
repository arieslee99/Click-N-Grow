package model;

import java.util.ArrayList;

public class Garden {
    private final ArrayList<Plant> garden;
    private String gardenName;

    //EFFECTS: construct a garden
    public Garden(String name) {
        garden = new ArrayList<>();
        gardenName = name;
    }

    //MODIFIES: this
    //EFFECTS: adds a plant to garden
    public void addPlant(Plant p) {
        garden.add(p);
    }

    //REQUIRES: garden must have at least one plant in it
    //MODIFIES: this
    //EFFECTS: removes a plant from garden and returns true if successful; else, false
//    public boolean removePlant(String plantName) {
//        return garden.removeIf(plant -> (plant.getPlantName().equalsIgnoreCase(plantName)));
//    }

    //REQUIRES: position < size of garden
    //EFFECTS: removes plant in that position of the list
    public void removePlant(int position) {
        garden.remove(position);
    }

    //MODIFIES: this
    //EFFECTS: removes all plants in garden
    public void emptyGarden() {
        garden.clear();
    }


    //REQUIRES: garden has at least one dead plant in it
    //MODIFIES: this
    //EFFECTS: removes all the "Dead!" plants from garden
    public void removeDeadPlants() {
        garden.removeIf(plant -> plant.getLifeStatus().equals("Dead!"));
    }

    //getters
    public Integer getSize() {
        return garden.size();
    }

    public String getGardenName() {
        return gardenName;
    }

    //MODIFIES: this
    //EFFECTS: sets garden name
    public void setGardenName(String name) {
        gardenName = name;
    }


    //REQUIRES: position < garden size
    //EFFECTS: returns plant at the specified position in garden list
    public Plant getPlant(int position) {
        return garden.get(position);
    }

    //EFFECTS: return true if plant exits in the garden; else, false
    public Boolean searchForPlant(String plantName) {
        for (Plant p: garden) {
            if (p.getPlantName().equalsIgnoreCase(plantName)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return number of dead plants
    public Integer getNumOfDeadPlants() {
        int count = 0;
        for (Plant plant: garden) {
            if (plant.getLifeStatus().equals("Dead!")) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Plant> getGarden() {
        return garden;
    }


}
