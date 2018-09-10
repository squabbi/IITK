package com.squabbi.iitk.model;

public class Item {
    private Rarity mRarity;

    Item (Rarity rarity) {
        this.mRarity = rarity;
    }

    public enum Rarity {
        VERY_COMMON, COMMON, RARE, VERY_RARE
    }

    public Rarity getRarity() {
        return mRarity;
    }
}
