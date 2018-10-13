package com.squabbi.iitk.model;

public class FlipCard extends Weapon {

    private FlipType mFlipType;

    public FlipCard(FlipType flipType) {
        super(WeaponType.FLIP_CARD, Rarity.VERY_RARE, 0);
        this.mFlipType = flipType;
    }

    public enum FlipType {
        ADA_REFACTOR, JARVIS_VIRUS
    }

    public FlipType getFlipType() {
        return mFlipType;
    }

    public void setFlipType(FlipType flipType) {
        mFlipType = flipType;
    }
}
