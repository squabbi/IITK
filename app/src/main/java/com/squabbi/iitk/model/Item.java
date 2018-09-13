package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {

    public static final String FIELD_RARITY = "rarity";
    public static final String FIELD_LEVEL = "level";

    private Rarity mRarity;
    private Integer mLevel;

    Item() {}

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

    public void setLevel(Integer level) {
        mLevel = level;
    }

    public void setRarity(Rarity rarity) {
        mRarity = rarity;
    }
}