package model;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Plant> inventory;

    //EFFECTS: construct an inventory
    public Inventory() {
        inventory = new ArrayList<>();
    }

    public Integer addPlant(Garden g) {
        int ripeCount = 0;
        for (Plant plant: g.getGarden()) {
            if (plant.getLifeStatus().equals("Ripe!")) {
                inventory.add(plant);
                ripeCount++;
            }
        }
        g.getGarden().removeIf(plant -> plant.getLifeStatus().equals("Ripe!"));

        return ripeCount;
    }

//    public Integer addPlant(Garden g) {
//        int ripeCount = 0;
//        for (int i = 0; i < g.getSize(); i++) {
//            Plant plant = g.getPlant(i);
//            if (plant.getLifeStatus().equals("Ripe!")) {
//                inventory.add(plant);
//                g.removePlant(i);
//                ripeCount++;
//            }
//        }
//        return ripeCount;
//    }

    //MODIFIES: this
    //EFFECTS: add ripe plant to inventory, and remove it from garden
//    public void addPlant(Garden g, Plant p) {
//        if (g.searchForPlant(p.getPlantName())) {
//            if (p.getLifeStatus().equals("Ripe!")) {
//                inventory.add(p);
//                g.removePlant(p.getPlantName());
//            }
//        }
//    }

    //REQUIRES: position < inventory size
    //EFFECTS: returns plant at the specified position in inventory list
    public Plant getPlant(int position) {
        return inventory.get(position);
    }

    //REQUIRES: plant needs to be in inventory
    //MODIFIES: this
    //EFFECTS: remove plant from inventory and return true; else, return false
//    public boolean removePlant(String plantName) {
//        return inventory.removeIf(p -> (p.getPlantName().equalsIgnoreCase(plantName)));
//    }

    //MODIFIES: this
    //EFFECTS: removes plant at number position in inventory list
    public void removePlant(int position) {
        inventory.remove(position);
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
