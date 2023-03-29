package model;

import org.json.JSONArray;

import java.util.ArrayList;

//Represents list of riped (harvested) plants
public class Inventory {
    private ArrayList<Plant> inventory;

    //EFFECTS: construct an inventory
    public Inventory() {
        inventory = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds plant to inventory; removes plant from garden
    public Integer addAllPlant(Garden g) {
        int ripeCount = 0;
        for (Plant plant: g.getGarden()) {
            if (plant.getUpdatedLifeStatus().equals("Ripe!")) {
                inventory.add(plant);
                ripeCount++;
            }
        }
        g.getGarden().removeIf(plant -> plant.getUpdatedLifeStatus().equals("Ripe!"));

        return ripeCount;
    }

    //MODIFIES: this
    //EFFECTS: just adds the plant to inventory without removing anything from garden
    public void justAddPlant(Plant plant) {
        inventory.add(plant);
    }

    //REQUIRES: position < inventory size
    //EFFECTS: returns plant at the specified position in inventory list
    public Plant getPlant(int position) {
        return inventory.get(position);
    }

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

    //EFFECTS: translates list of plants into inventory into json array
    public JSONArray translateToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Plant plant: inventory) {
            jsonArray.put(plant.translateToJson());
        }
        return jsonArray;
    }

    //getter
    public ArrayList<Plant> getInventory() {
        return inventory;
    }

    public Integer getSize() {
        return inventory.size();
    }

}
