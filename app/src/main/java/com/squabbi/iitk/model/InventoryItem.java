package com.squabbi.iitk.model;

/**
 * POJO for holding and exposing Ingress item's respective resources to be used for display
 * purposes.
 */

public class InventoryItem {

    private Item.DetailItemType mDetailItemType;
    private Integer mNameResource;
    private Item.Rarity mRarity;
    private Integer mLevel;
    private Integer mImageResource;

    /**
     * Creates an InventoryItem to visually represent an Ingress item.
     * @param detailItemType Specific item this represents.
     * @param name String resource ID of the item's name.
     * @param rarity Rarity of the item.
     * @param level Level of the item. 0 for non-leveled items.
     * @param imageResource Drawable resource ID which represents item.
     */
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
