package com.squabbi.iitk.model;

/**
 * Extension class for the Weapon Flip Card.
 */

public class FlipCard extends Weapon {

    private FlipType mFlipType;

    /** Constructor for the Flip Card with pre-filled super */
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
