package com.squabbi.iitk.model;

public class Item {
    private Rarity mRarity;
    private Integer mLevel;

    Item (Rarity rarity, Integer level) {
        this.mRarity = rarity;
        this.mLevel = level;
    }

    public enum Rarity {
        VERY_COMMON, COMMON, RARE, VERY_RARE
    }

    public Rarity getRarity() {
        return mRarity;
    }

    public Integer getLevel() {
        return mLevel;
    }
}
