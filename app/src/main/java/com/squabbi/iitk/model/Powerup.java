package com.squabbi.iitk.model;

/**
 * POJO that represents items which can be bought in the Ingress store. Also known as Powerups.
 */

public class Powerup extends Item {

    /** Basic enum of the different Powerup items available */
    public enum PowerupType {
        FRACKER,
        BEACON
    }

    private PowerupType mPowerupType;

    // Required empty constructor by Firebase
    public Powerup() {}

    /**
     * Constructor for creating Powerup items.
     * @param powerupType Type of Powerup.
     */
    public Powerup(PowerupType powerupType) {
        super(ItemType.POWERUP, Rarity.VERY_RARE, 0);
        this.mPowerupType = powerupType;
    }

    public PowerupType getPowerupType() {
        return mPowerupType;
    }

    public void setPowerupType(PowerupType powerupType) {
        mPowerupType = powerupType;
    }
}
