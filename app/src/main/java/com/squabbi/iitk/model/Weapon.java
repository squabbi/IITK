package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Weapon extends Item {

    public static final String FIELD_WEAPON_TYPE = "weaponType";

    private WeaponType mWeaponType;

    public enum WeaponType {
        BURSTER, ULTRA_STRIKE, FLIP_CARD
    }

    Weapon (WeaponType weaponType, Rarity rarity, Integer level) {
        super(rarity, level);
        this.mWeaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return mWeaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        mWeaponType = weaponType;
    }
}
