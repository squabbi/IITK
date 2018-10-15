package com.squabbi.iitk.model;

/**
 * Extension class to make it quicker in creating Xmp Burster weapons.
 */

public class XmpBurster extends Weapon {

    /**
     * Constructor that has pre-filled super arguments, just provide the level.
     * @param level The level of the Xmp burster.
     */
    public XmpBurster(int level) {
        super(WeaponType.BURSTER, Rarity.VERY_COMMON, level);
    }
}
