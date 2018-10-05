package com.squabbi.iitk.model;

import java.util.List;

public class Inventory {

    private String mName;
    private String mDescription;
    private List<Item> mInventoryContents;

    // Empty constructor required by Firebase
    public Inventory() {}

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<Item> getInventoryContents() {
        return mInventoryContents;
    }

    public void setInventoryContents(List<Item> inventoryContents) {
        mInventoryContents = inventoryContents;
    }
}
