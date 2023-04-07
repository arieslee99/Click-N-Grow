package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Represents a list of plants that are in garden
public class Garden {
    private String gardenName;
    private ArrayList<Plant> garden;

    //EFFECTS: construct a garden
    public Garden(String name) {
        garden = new ArrayList<>();
        gardenName = name;
        EventLog.getInstance().logEvent(new Event("You started a new garden called: " + name));
    }

    //MODIFIES: this
    //EFFECTS: adds a plant to garden
    public void addPlant(Plant p) {
        garden.add(p);
        EventLog.getInstance().logEvent(new Event("New plant added to garden: " + p.getPlantName()));
    }

    //REQUIRES: position < size of garden
    //EFFECTS: removes plant in that position of the list
    public void removePlant(int position) {
        EventLog.getInstance().logEvent(
                new Event("Plant removed from garden: " + garden.get(position).getPlantName()));
        garden.remove(position);
    }

    //MODIFIES: this
    //EFFECTS: removes all plants in garden
    public void emptyGarden() {
        garden.clear();
        EventLog.getInstance().logEvent(new Event("All plants removed from garden."));
    }


    //REQUIRES: garden has at least one dead plant in it
    //MODIFIES: this
    //EFFECTS: removes all the "Dead!" plants from garden
    public void removeDeadPlants() {
        garden.removeIf(plant -> plant.getUpdatedLifeStatus().equals("Dead!"));
        EventLog.getInstance().logEvent(new Event("All dead plants removed from garden."));
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

    //EFFECTS: translates list of plants in garden into json array
    public JSONArray translateToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Plant plant: garden) {
            jsonArray.put(plant.translateToJson());
        }
        return jsonArray;
    }

    //EFFECTS: translates the garden name to a json object
    public JSONObject translateToJson() {
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("name", gardenName);
        jsonObject.put("plants", translateToJsonArray());

        return jsonObject;
    }

    //EFFECTS: return number of dead plants
    public Integer getNumOfDeadPlants() {
        int count = 0;
        for (Plant plant: garden) {
            if (plant.getUpdatedLifeStatus().equals("Dead!")) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Plant> getGarden() {
        return garden;
    }

}
