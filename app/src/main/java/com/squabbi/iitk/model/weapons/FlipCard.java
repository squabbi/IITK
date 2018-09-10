package com.squabbi.iitk.model.weapons;

public class FlipCard extends Weapon {
    private FlipType mFlipType;

    FlipCard(WeaponType weaponType, Integer level, FlipType flipType) {
        super(weaponType, level);
        this.mFlipType = flipType;
    }

    public enum FlipType {
        ADA_REFACTOR, JARVIS_VIRUS
    }

    public FlipType getFlipType() {
        return mFlipType;
    }
}
