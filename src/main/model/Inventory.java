package model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Plant> inventory;

    //EFFECTS: construct an inventory
    public Inventory() {
        inventory = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add ripe plant to inventory, and remove it from garden
    public void addPlant(Garden g, Plant p) {
        if (p.getLifeStatus().equals("Ripe!")) {
            inventory.add(p);
            g.removePlant(p);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove plant from inventory
    public void removePlant(Plant plant) {
        ArrayList<String> plantNames = new ArrayList<>();
        for(Plant p: inventory) {
            plantNames.add(p.getPlantName());
        }

        for (int i = 0; i < plantNames.size(); i++) {
            if (plant.getPlantName().equals(plantNames.get(i))) {
                inventory.remove(i);
                break;
            }
        }
    }

    //EFFECTS: search of plant in inventory; if it exists, return true, else false
    public String searchInventory(Plant plant) {
        for(Plant p: inventory) {
            if(p.getPlantName().equals(plant.getPlantName())) {
                return "Reap your rewards!";
            }
        }
        return "Your soil is fertile, go sow some seeds!";
    }

    //getter
    public ArrayList<Plant> getInventory() {
        return inventory;
    }


}
