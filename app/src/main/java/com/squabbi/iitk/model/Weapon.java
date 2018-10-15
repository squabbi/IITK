package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

/**
 * Weapon POJO representing the weapons found in Ingress.
 */

@IgnoreExtraProperties
public class Weapon extends Item {

    private WeaponType mWeaponType;

    /** Basic enum of the different weapon types */
    public enum WeaponType {
        BURSTER, ULTRA_STRIKE, FLIP_CARD
    }

    // Empty constructor required by Firebase
    public Weapon() {}

    /**
     * Constructor for creating weapons in Ingress.
     * @param weaponType Type of Weapon.
     * @param rarity Rarity of the Weapon.
     * @param level The level of the Weapon, use 0 for non-leveled Weapons.
     */
    public Weapon (WeaponType weaponType, Rarity rarity, Integer level) {
        super(ItemType.WEAPON, rarity, level);
        this.mWeaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return mWeaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        mWeaponType = weaponType;
    }
}
