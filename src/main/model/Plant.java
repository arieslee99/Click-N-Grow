package model;

import java.util.Objects;

public abstract class Plant {
    private final String plantName;
    private String lifeStatus;
    private int waterCount;
    private int fertilizerCount;
    private final int price;
    private final PlantType type;

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

    //MODIFIES: this
    //EFFECTS: returns amount earned based on plant sold
    public abstract Integer getProfitValue();

    public PlantType getType() {
        return type;
    }

    //EFFECTS: overriding equals method
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        //checking to see if this. is an instance of the same class as other.
        if (this.getClass() != other.getClass()) {
            return false;
        }

        if (!this.plantName.equals(((Plant) other).plantName)){
            return false;
        }

        if (!this.lifeStatus.equals(((Plant) other).lifeStatus)) {
            return false;
        }

        if (this.fertilizerCount != ((Plant) other).fertilizerCount) {
            return false;
        }

        if (this.waterCount != ((Plant) other).waterCount) {
            return false;
        }

        if (this.price != ((Plant) other).price) {
            return false;
        }

        return this.type.equals(((Plant) other).type);
    }

}


