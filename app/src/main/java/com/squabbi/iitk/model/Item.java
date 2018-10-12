package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {

    private ItemType mItemType;
    private Rarity mRarity;
    private Integer mLevel;

    // Empty constructor for Firestore
    Item() {}

    Item (ItemType itemType, Rarity rarity, Integer level) {

        this.mItemType = itemType;
        this.mRarity = rarity;
        this.mLevel = level;
    }

    public enum ItemType {
        WEAPON, MOD, KEY, KEY_LOCKER, CAPSULE
    }

    public enum Rarity {
        VERY_COMMON, COMMON, RARE, VERY_RARE
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        mItemType = itemType;
    }

    public Rarity getRarity() {
        return mRarity;
    }

    public void setRarity(Rarity rarity) {
        mRarity = rarity;
    }

    public Integer getLevel() {
        return mLevel;
    }

    public void setLevel(Integer level) {
        mLevel = level;
    }
}