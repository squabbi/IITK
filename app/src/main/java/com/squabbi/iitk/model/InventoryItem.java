package com.squabbi.iitk.model;

public class Mod {

    private String mName;
    private Item.Rarity mRarity;
    private Integer mImageResource;

    public Mod(String name, Item.Rarity rarity, Integer imageResource) {

        this.mName = name;
        this.mRarity = rarity;
        this.mImageResource = imageResource;
    }

    public String getName() {
        return mName;
    }

    public Item.Rarity getRarity() {
        return mRarity;
    }

    public Integer getImageResource() {
        return mImageResource;
    }
}
