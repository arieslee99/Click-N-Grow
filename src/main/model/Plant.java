package model;

public class Plant {
    private String plantName;
    private String lifeStatus;
    private int waterCount;
    private int fertilizerCount;
    private int price;
    private PlantType type;

    //EFFECTS: construct a plant
    public Plant(String name, Integer waterCount, Integer fertilizerCount, Integer price, PlantType type) {
        this.plantName = name;
        this.waterCount = waterCount;
        this.fertilizerCount = fertilizerCount;
        this.lifeStatus = getLifeStatus();
        this.price = price;
        this.type = type;
    }

    //MODIFIES: this
    //EFFECTS: remove one water unit from plant's water count (wc)
    public void waterPlant() {
        waterCount--;
    }

    //MODIFIES: this
    //EFFECTS: remove one fertilizer unit from the plant's fertilizer count (fc)
    public void feedPlant() {
        fertilizerCount--;
    }

    //MODIFIES: this
    //EFFECTS: if plant's water count (wc) and fertilizer count (pc) are 0, set life status to "ripe";
    //         when pc or/and wc are under 0, set to "dead";
    //         when pc and/or wc is over 0, set to "growing"
    public void updateLifeStatus() {
        if (waterCount == 0 && fertilizerCount == 0) {
            lifeStatus = "Ripe!";
        } else if (waterCount < 0 || fertilizerCount < 0) {
            lifeStatus = "Dead!";
        } else {
            lifeStatus = "Growing!";
        }
    }

    //getters
    public String getPlantName() {
        return plantName;
    }

    public String getLifeStatus() {
        updateLifeStatus();
        return lifeStatus;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public Integer getFertilizerCount() {
        return fertilizerCount;
    }

    public Integer getPrice() {
        return price;
    }

    public PlantType getType() {
        return type;
    }

}


