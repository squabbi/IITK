package com.squabbi.iitk.model;

public class FlipCard extends Weapon {
    private FlipType mFlipType;

    FlipCard(WeaponType weaponType, Rarity rarity, FlipType flipType) {
        super(weaponType, rarity, 0);
        this.mFlipType = flipType;
    }

    public enum FlipType {
        ADA_REFACTOR, JARVIS_VIRUS
    }

    public FlipType getFlipType() {
        return mFlipType;
    }
}
