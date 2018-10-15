package com.squabbi.iitk.model;

/**
 * Extension class for creating Ultra strike Weapon items. Extends {@link Weapon}
 */

public class UltraStrike extends Weapon {

    /**
     * Public constructor to create Ultra strikes using the super from Weapon with some
     * pre-determined values.
     * @param level The level of the Ultra strike item.
     */
    public UltraStrike(int level) {
        super(WeaponType.ULTRA_STRIKE, Rarity.VERY_COMMON, level);
    }
}
