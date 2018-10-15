package com.squabbi.iitk.model;

/**
 * Power Cube class, a POJO that represents a Power Cube in Ingress.
 * Extends the super-class {@link Item}
 */

public class PowerCube extends Item {

    private boolean mIsLawson;

    // Required empty constructor
    public PowerCube() {}

    /**
     * Constructor for creating Power Cubes, it has pre-filled super arguments.
     * @param level The level of the Power Cube.
     */
    public PowerCube(int level) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, level);
    }

    /**
     * Constructor for creating Lawson Power Cubes.
     * @param isLawson Is the Power Cube a Lawson?
     */
    public PowerCube(boolean isLawson) {
        super(ItemType.POWER_CUBE, Rarity.VERY_COMMON, 0);
        this.mIsLawson = isLawson;
    }

    public boolean isLawson() {
        return mIsLawson;
    }

    public void setLawson(boolean lawson) {
        mIsLawson = lawson;
    }
}
