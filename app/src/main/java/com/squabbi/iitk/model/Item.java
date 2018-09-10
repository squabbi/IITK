package com.squabbi.iitk.model;

public class Item {
    private Rarity mRarity;

    public enum Rarity {
        VERY_COMMON, COMMON, RARE, VERY_RARE
    }

    public Rarity getRarity() {
        return mRarity;
    }
}
