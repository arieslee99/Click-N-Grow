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
        if (g.getPlant(p)) {
            if (p.getLifeStatus().equals("Ripe!")) {
                inventory.add(p);
                g.removePlant(p.getPlantName());
            }
        }
    }

    //REQUIRES: plant needs to be in inventory
    //MODIFIES: this
    //EFFECTS: remove plant from inventory and return true; else, return false
    public boolean removePlant(String plantName) {

        return inventory.removeIf(p -> (p.getPlantName().equalsIgnoreCase(plantName)));

//        ArrayList<String> plantNames = new ArrayList<>();
//        for(Plant p: inventory) {
//            plantNames.add(p.getPlantName());
//        }
//
//        for (int i = 0; i < plantNames.size(); i++) {
//           if (plant.getPlantName().equals(plantNames.get(i))) {
//               inventory.remove(i);
//
//           }
//       }
   }

    //EFFECTS: search if plant exists in inventory; if it exists, return it; else, return null
    public Plant searchInventory(String plantName) {
        for (Plant p : inventory) {
            if (p.getPlantName().equalsIgnoreCase(plantName)) {
                return p;
            }
        }
        return null;
    }

    //getter
    public ArrayList<Plant> getInventory() {
        return inventory;
    }

    public Integer getSize() {
        return inventory.size();
    }


}
