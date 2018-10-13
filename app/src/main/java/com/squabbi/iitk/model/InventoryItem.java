package com.squabbi.iitk.model;

public class InventoryItem {

    private Item.DetailItemType mDetailItemType;
    private Integer mNameResource;
    private Item.Rarity mRarity;
    private Integer mLevel;
    private Integer mImageResource;

    public InventoryItem(Item.DetailItemType detailItemType, Integer name, Item.Rarity rarity, Integer level, Integer imageResource) {

        this.mDetailItemType = detailItemType;
        this.mNameResource = name;
        this.mRarity = rarity;
        this.mLevel = level;
        this.mImageResource = imageResource;
    }

    public Item.DetailItemType getDetailItemType() {
        return mDetailItemType;
    }

    public Integer getNameResource() {
        return mNameResource;
    }

    public Item.Rarity getRarity() {
        return mRarity;
    }

    public Integer getLevel() {
        return mLevel;
    }

    public Integer getImageResource() {
        return mImageResource;
    }
}
