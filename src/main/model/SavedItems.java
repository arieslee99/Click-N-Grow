package model;

import org.json.JSONObject;

//Data that will be saved and loaded every time app is running
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

    //EFFECTS: retrieves garden
    public Garden getGarden() {
        return garden;
    }

    //EFFECTS: retrieves inventory
    public Inventory getInventory() {
        return inventory;
    }

    //EFFECTS: retrieves wallet
    public Wallet getWallet() {
        return wallet;
    }

}
