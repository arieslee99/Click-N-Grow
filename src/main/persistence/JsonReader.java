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

import static model.PlantType.*;


//Reads JSON representation (string) into garden
public class JsonReader {
    private String jsonFile;

    //EFFECTS: constructs a reader
    public JsonReader(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    //EFFECTS: reads savedItems from json
    public SavedItems read() throws IOException {
        String jsonData = readFile(jsonFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return returnAsSavedItem(jsonObject);
    }

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
        JSONObject wallet = jsonObject.getJSONObject("wallet");
        int balance = wallet.getInt("balance");
        Wallet actualWallet = new Wallet();
        actualWallet.setBalance(balance);
        return actualWallet;
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
        Plant actualPlant;
        if (plantType.equalsIgnoreCase("Vegetable")) {
            actualPlantType = VEGETABLE;
            actualPlant = makeVeggie(plantName, waterCount,
                    fertilizerCount, price, lifeStatus, actualPlantType);
        } else {
            actualPlantType = FLOWER;
            actualPlant = makeFlower(plantName, waterCount,
                    fertilizerCount, price, lifeStatus, actualPlantType);
        }
        return actualPlant;
    }

    //EFFECT: construct actual vegetable plant from json representation
    public Plant makeVeggie(String plantName, int wc, int fc, int price, String lifeStatus, PlantType actualPT) {
        switch (plantName) {
            case ("Carrot"):
                Carrot carrot = new Carrot(plantName, wc, fc, price, actualPT);
                carrot.setLifeStatus(lifeStatus);
                return carrot;
            case ("Eggplant"):
                Eggplant eggplant = new Eggplant(plantName, wc, fc, price, actualPT);
                eggplant.setLifeStatus(lifeStatus);
                return eggplant;
            case ("Garlic"):
                Garlic garlic = new Garlic(plantName, wc, fc, price, actualPT);
                garlic.setLifeStatus(lifeStatus);
                return garlic;
            case ("Lettuce"):
                Lettuce lettuce = new Lettuce(plantName, wc, fc, price, actualPT);
                lettuce.setLifeStatus(lifeStatus);
                return lettuce;
            case ("Potato"):
                Potato potato = new Potato(plantName, wc, fc, price, actualPT);
                potato.setLifeStatus(lifeStatus);
                return potato;
        }
        return null;
    }

    //EFFECTS: construct actual fruit plant from json representation
    public Plant makeFlower(String plantName, int wc, int fc, int price, String lifeStatus, PlantType actualPT) {
        switch (plantName) {
            case ("Cactus"):
                Cactus cactus = new Cactus(plantName, wc, fc, price, actualPT);
                cactus.setLifeStatus(lifeStatus);
                return cactus;
            case ("Forget Me Not"):
                ForgetMeNot forgetMeNot = new ForgetMeNot(plantName, wc, fc, price, actualPT);
                forgetMeNot.setLifeStatus(lifeStatus);
                return forgetMeNot;
            case ("Lavender"):
                Lavender lavender = new Lavender(plantName, wc, fc, price, actualPT);
                lavender.setLifeStatus(lifeStatus);
                return lavender;
            case ("Rose"):
                Rose rose = new Rose(plantName, wc, fc, price, actualPT);
                rose.setLifeStatus(lifeStatus);
                return rose;
            case ("Sunflower"):
                Sunflower sunflower = new Sunflower(plantName, wc, fc, price, actualPT);
                sunflower.setLifeStatus(lifeStatus);
                return sunflower;
        }
        return null;
    }
}
