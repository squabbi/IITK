package com.squabbi.iitk.model;

public class Weapon extends Item {
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
}
