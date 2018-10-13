package com.squabbi.iitk.model;

public class PowerCube extends Item {

    private boolean isLawson;

    // Required empty constructor
    public PowerCube() {}

    public PowerCube(int level) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, level);
    }

    public PowerCube(int level, boolean isLawson) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, level);
        this.isLawson = isLawson;
    }

    public boolean isLawson() {
        return isLawson;
    }

    public void setLawson(boolean lawson) {
        isLawson = lawson;
    }
}
