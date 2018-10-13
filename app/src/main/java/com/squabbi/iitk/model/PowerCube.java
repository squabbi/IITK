package com.squabbi.iitk.model;

public class PowerCube extends Item {

    private boolean mIsLawson;

    // Required empty constructor
    public PowerCube() {}

    public PowerCube(int level) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, level);
    }

    public PowerCube(int level, boolean isLawson) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, level);
        this.mIsLawson = isLawson;
    }

    public boolean isLawson() {
        return mIsLawson;
    }

    public void setLawson(boolean lawson) {
        mIsLawson = lawson;
    }
}
