package com.squabbi.iitk.model;

import com.squabbi.iitk.model.Item;

public class Weapon extends Item {
    private Integer mLevel;
    private WeaponType mWeaponType;

    public enum WeaponType {
        BURSTER, ULTRA_STRIKE, FLIP_CARD
    }

    Weapon (WeaponType weaponType, Rarity rarity, Integer level) {
        super(rarity);
        this.mWeaponType = weaponType;
        this.mLevel = level;
    }

    public Integer getLevel() {
        return mLevel;
    }

    public WeaponType getWeaponType() {
        return mWeaponType;
    }
}
