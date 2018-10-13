package com.squabbi.iitk.model;

public class Powerup extends Item {

    public enum PowerupType {
        FRACKER,
        BEACON
    }

    private PowerupType mPowerupType;

    // Required empty constructor by Firebase
    public Powerup() {}

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
