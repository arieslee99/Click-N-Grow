package persistence;

import model.*;
import model.seeds.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static model.PlantType.FLOWER;
import static model.PlantType.VEGETABLE;


//Reads JSON representation (string) into garden
public class JsonReader {
    private String jsonFile;

    //EFFECTS: constructs a reader
    public JsonReader(String json) {
        jsonFile = json;
    }

    //EFFECTS: reads savedItems from json
    public SavedItems read() throws IOException {
        String jsonData = readFile(jsonFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return returnAsSavedItem(jsonObject);
    }

    //JsonSerializationDemo
    //EFFECTS: reads json and returns of string of it
    public String readFile(String jsonFile) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(jsonFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: returns the SavedItem from the json object
    public SavedItems returnAsSavedItem(JSONObject jsonObject) {
        return new SavedItems(parseGarden(jsonObject), parseInventory(jsonObject), parseWallet(jsonObject));
    }

    //EFFECTS: goes through garden in json object and parses the plants in it
    public Garden parseGarden(JSONObject jsonObject) {
        JSONObject garden = jsonObject.getJSONObject("garden");
        String gardenName = garden.getString("name");
        JSONArray plants = garden.getJSONArray("plants");
        Garden actualGarden = new Garden(gardenName);

        for (Object json: plants) {
            JSONObject jsonPlant = (JSONObject) json;
            Plant actualPlant = getActualPlant(jsonPlant);
            actualGarden.addPlant(actualPlant);
        }
        return actualGarden;
    }

    //EFFECTS: goes through inventory in json object and parses the plants in it
    public Inventory parseInventory(JSONObject jsonObject) {
        JSONArray inventory = jsonObject.getJSONArray("inventory");
        Inventory actualInventory = new Inventory();

        for (Object json: inventory) {
            JSONObject jsonPlant = (JSONObject) json;
            Plant actualPlant = getActualPlant(jsonPlant);
            actualInventory.justAddPlant(actualPlant);
        }
        return actualInventory;
    }

    //MODIFIES: wallet
    //EFFECTS: setting the actual wallet to have the json object's balance
    public Wallet parseWallet(JSONObject jsonObject) {
        int balance = jsonObject.getInt("wallet");
        Wallet wallet = new Wallet();
        wallet.setBalance(balance);
        return wallet;
    }

    //EFFECTS: retrieving plant data from json representation and constructing
    //         the plant
    private Plant getActualPlant(JSONObject jsonPlant) {
        String plantName = jsonPlant.getString("name");
        int waterCount = jsonPlant.getInt("water count");
        int fertilizerCount = jsonPlant.getInt("fertilizer count");
        int price = jsonPlant.getInt("price");
        String lifeStatus = jsonPlant.getString("life status");
        String plantType = jsonPlant.getString("plant type");

        PlantType actualPlantType;
        if (plantType.equals("Vegetable")) {
            actualPlantType = VEGETABLE;
            return constructActualVeggiePlant(plantName, waterCount, fertilizerCount,
                    price, lifeStatus, actualPlantType);
        } else {
            actualPlantType = FLOWER;
            return constructActualFlowerPlant(plantName, waterCount, fertilizerCount,
                    price, lifeStatus, actualPlantType);
        }
    }

    //EFFECT: construct actual vegetable plant from json representation
    private Plant constructActualVeggiePlant(String plantName, int waterCount, int fertilizerCount,
                                       int price, String lifeStatus, PlantType actualPlantType) {
        switch (plantName) {
            case ("Carrot"):
                Carrot carrot = new Carrot(plantName, waterCount, fertilizerCount, price, actualPlantType);
                carrot.setLifeStatus(lifeStatus);
                return carrot;
            case ("Eggplant"):
                Eggplant eggplant = new Eggplant(plantName, waterCount, fertilizerCount, price, actualPlantType);
                eggplant.setLifeStatus(lifeStatus);
                return eggplant;
            case ("Garlic"):
                Garlic garlic = new Garlic(plantName, waterCount, fertilizerCount, price, actualPlantType);
                garlic.setLifeStatus(lifeStatus);
                return garlic;
            case ("Lettuce"):
                Lettuce lettuce = new Lettuce(plantName, waterCount, fertilizerCount, price, actualPlantType);
                lettuce.setLifeStatus(lifeStatus);
                return lettuce;
            default:
                Potato potato = new Potato(plantName, waterCount, fertilizerCount, price, actualPlantType);
                potato.setLifeStatus(lifeStatus);
                return potato;
        }
    }

    //EFFECTS: construct actual fruit plant from json representation
    private Plant constructActualFlowerPlant(String plantName, int waterCount, int fertilizerCount,
                                             int price, String lifeStatus, PlantType actualPlantType) {
        switch (plantName) {
            case ("Cactus"):
                Cactus cactus = new Cactus(plantName, waterCount, fertilizerCount, price, actualPlantType);
                cactus.setLifeStatus(lifeStatus);
                return cactus;
            case ("ForgetMeNot"):
                ForgetMeNot forgetMeNot =
                        new ForgetMeNot(plantName, waterCount, fertilizerCount, price, actualPlantType);
                forgetMeNot.setLifeStatus(lifeStatus);
                return forgetMeNot;
            case ("Lavender"):
                Lavender lavender = new Lavender(plantName, waterCount, fertilizerCount, price, actualPlantType);
                lavender.setLifeStatus(lifeStatus);
                return lavender;
            case ("Rose"):
                Rose rose = new Rose(plantName, waterCount, fertilizerCount, price, actualPlantType);
                rose.setLifeStatus(lifeStatus);
                return rose;
            default:
                Sunflower sunflower = new Sunflower(plantName, waterCount, fertilizerCount, price, actualPlantType);
                sunflower.setLifeStatus(lifeStatus);
                return sunflower;
        }
    }

}
