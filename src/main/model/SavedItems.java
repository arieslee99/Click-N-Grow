package model;

import org.json.JSONArray;
import org.json.JSONObject;

public class SavedItems {

    private Garden garden;
    private Inventory inventory;
    private Wallet wallet;

    //EFFECTS: constructs a savedItems
    public SavedItems(Garden garden, Inventory inventory, Wallet wallet) {
        this.garden = garden;
        this.inventory = inventory;
        this.wallet = wallet;
    }

    //EFFECTS: translates savedItems to json object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("garden", garden.translateToJson());
        jsonObject.put("inventory", inventory.translateToJsonArray());
        jsonObject.put("wallet", wallet.translateMoneyToJson());
        return jsonObject;
    }

}
