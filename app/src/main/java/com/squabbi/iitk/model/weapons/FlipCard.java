package com.squabbi.iitk.model.weapons;

public class FlipCard extends Weapon {
    private FlipType mFlipType;

    FlipCard(Type type, Integer level) {
        super(type, level);
    }

    public enum FlipType {
        ADA_REFACTOR, JARVIS_VIRUS
    }

    public FlipType getFlipType() {
        return mFlipType;
    }
}
